package usn.obj2100.server.handler;

import usn.obj2100.server.DatabaseConnectionManager;
import usn.obj2100.shared.model.InventarExtended;
import usn.obj2100.shared.model.Search;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is responsible for handling the search queries.
 *
 * @created 2024-05-13
 */
public class SearchHandler
{
	public SearchHandler() {}
	
	/**
	 * Behandle søket og returner en liste med utvidede inventarobjekter.
	 * Disse objektene inneholder mer informasjon enn de vanlige inventarobjektene,
	 * i form av det faktiske innholdet av fremmednøkltabellene.
	 * <p/>
	 * Denne konstruerer også en SQL søkestreng. Den er litt janky akkurat nå,
	 * men funker rimelig greit. Se også SearchTest.java testen for simulert
	 * klient søking.
	 *
	 * @param search Søkeobjektet som inneholder søkekriteriene.
	 * @return En liste med utvidede inventarobjekter.
	 */
	public List<InventarExtended> handleSearch(Search search)
	{
		/* Base query segment. */
		StringBuilder query = new StringBuilder(
			"SELECT i.sku, i.beskrivelse, i.innkjopsdato, i.innkjopspris, i.antall, i.forventetLevetid, k.kategori, kt.type as kategoriType, CONCAT_WS('/', p.bygg, p.floy, IFNULL(p.etasje, ''), IFNULL(p.rom, '')) as plassering, kst.begrunnelse FROM inventar i "
			+ "LEFT JOIN plassering p ON i.plassering = p.id "
			+ "LEFT JOIN kategori k ON i.kategori = k.id "
			+ "LEFT JOIN kategoriType kt ON k.type = kt.id "
			+ "LEFT JOIN kassert ks ON i.kassert = ks.id "
			+ "LEFT JOIN kassertType kst ON ks.begrunnelse = kst.id ");
		
		boolean isFiltered = false;
		
		/* Base search segment. */
		if (search.getSearch() != null && !search.getSearch().isEmpty())
		{
			query
				.append(isFiltered ? " AND" : " WHERE")
				.append(" (i.beskrivelse LIKE '%")
				.append(search.getSearch())
				.append("%' OR k.kategori LIKE '%")
				.append(search.getSearch())
				.append("%')");
			isFiltered = true;
		}
		
		/* Filter segment: Beskrivelse. */
		if (search.getSearchByBeskrivelse() != null)
		{
			query
				.append(isFiltered ? " AND" : " WHERE")
				.append(" i.beskrivelse LIKE '%")
				.append(search.getSearchByBeskrivelse())
				.append("%'");
			isFiltered = true;
		}
		
		/* Filter segment: Type. */
		if (search.getSearchByType() != null)
		{
			query
				.append(isFiltered ? " AND" : " WHERE")
				.append(" kt.type LIKE '%")
				.append(search.getSearchByType())
				.append("%'");
			isFiltered = true;
		}
		
		/* Filter segment: Kategori. */
		if (search.getSearchByKategori() != null)
		{
			query
				.append(isFiltered ? " AND" : " WHERE")
				.append(" k.kategori = '")
				.append(search.getSearchByKategori())
				.append("'");
			isFiltered = true;
		}

		// FIXME: For some reason it cannot compare a YYYY-MM-DD SQL DATE with a
		// 		 java.sql.Date.valueOf(YYYY-MM-DD) which are equivalently the same.
		/* Filter segment: Innkjøpsdato. */
		if (search.getSearchByInnkjopsdato() != null)
		{
			query
				.append(isFiltered ? " AND" : " WHERE")
				.append(" DATE(i.innkjopsdato) = '")
				.append(search.getSearchByInnkjopsdato())
				.append("'");
			isFiltered = true;
		}

		/* Filter segment: Innkjøpspris.
		 *
		 * TODO: Invenstigate why hard-coding values instead of getting them
		 *			works differently when it's the same datatype.
		 *
		 * This works fine:
		 * SELECT i.sku, i.beskrivelse, i.innkjopsdato, i.innkjopspris, i.antall, i.forventetLevetid, k.kategori, kt.type as kategoriType, CONCAT_WS('/', p.bygg, p.floy, IFNULL(p.etasje, ''), IFNULL(p.rom, '')) as plassering, kst.begrunnelse
		 * FROM inventar i
		 * 	LEFT JOIN plassering p ON i.plassering = p.id
		 * 	LEFT JOIN kategori k ON i.kategori = k.id
		 * 	LEFT JOIN kategoriType kt ON k.type = kt.id
		 * 	LEFT JOIN kassert ks ON i.kassert = ks.id
		 * 	LEFT JOIN kassertType kst ON ks.begrunnelse = kst.id
		 * WHERE (i.beskrivelse LIKE '%e%' OR k.kategori LIKE '%e%')
		 * 	AND i.innkjopspris BETWEEN 13 AND 83;
		 * */
		if (search.getSearchByPris() != null)
		{
			query
				.append(isFiltered ? " AND" : " WHERE")
				.append(" i.innkjopspris BETWEEN ")
				.append(search.getSearchByPris()[0])
				.append(" AND ")
				.append(search.getSearchByPris()[1]);
			isFiltered = true;
		}
		
		/* Filter segment: Plassering. */
		if (search.getSearchByPlassering() != null)
		{
			query
				.append(isFiltered ? " AND" : " WHERE")
				.append(" p.bygg = '")
				.append(search.getSearchByPlassering())
				.append("'");
			isFiltered = true;
		}
		
		/* Filter segment: Antall. */
		if (search.getSearchByAntall() != null)
		{
			query
				.append(isFiltered ? " AND" : " WHERE")
				.append(" i.antall BETWEEN ")
				.append(search.getSearchByAntall()[0])
				.append(" AND ")
				.append(search.getSearchByAntall()[1]);
			isFiltered = true;
		}
		
		/* Filter segment: Levetid. */
		if (search.getSearchByLevetid() != null)
		{
			query
				.append(isFiltered ? " AND" : " WHERE")
				.append(" i.forventetLevetid BETWEEN ")
				.append(search.getSearchByLevetid()[0])
				.append(" AND ")
				.append(search.getSearchByLevetid()[1]);
			isFiltered = true;
		}
		
		/* Filter segment: Forventet Kassering.
		 * FIXME: getSearchByForventetKassering assumes ints,
		 * 		 but should probably be Dates instead.
		 *
		 * Example solution:
		 * .append(Date.valueOf(String.valueOf(Date.valueOf(String.valueOf(search.getSearchByForventetKassering()[0])))))
		 *	.append("' AND '")
		 *	.append(Date.valueOf(String.valueOf(Date.valueOf(String.valueOf(search.getSearchByForventetKassering()[1])))))
		 */
		if (search.getSearchByForventetKassering() != null)
		{
			query
				.append(isFiltered ? " AND" : " WHERE")
				.append(" DATE(i.innkjopsdato, '+' || i.forventetLevetid || ' years') BETWEEN '")
				.append(Date.valueOf("2021-02-01"))
				.append("' AND '")
				.append(Date.valueOf("2024-02-01"))
				.append("'");
			isFiltered = true;
		}
		
		/* Filter segment: I Bruk. */
		if (search.isSearchByIBruk())
		{
			query
				.append(isFiltered ? " AND" : " WHERE")
				.append(" i.kassert = 0 OR i.kassert = null");
			isFiltered = true;
		}
		
		/* Filter segment: Ikke I Bruk. */
		if (search.isSearchByIkkeIBruk())
		{
			query
				.append(isFiltered ? " AND" : " WHERE")
				.append(" i.kassert = 1");
			isFiltered = true;
		}
		
		/* Filter segment: Kassert/Tatt Ut Av Bruk Årsak. */
		if (search.getSearchByTattUtAvBrukÅrsak() != null)
		{
			query
				.append(isFiltered ? " AND" : " WHERE")
				.append(" i.kassert = 1 AND kst.begrunnelse = '")
				.append(search.getSearchByTattUtAvBrukÅrsak())
				.append("'");
			isFiltered = true;
		}
		
		/* Filter segment: Tatt Ut Av Bruk År. */
		if (search.getSearchByTattUtAvBrukÅr() != 0)
		{
			query
				.append(isFiltered ? " AND" : " WHERE")
				.append(" i.kassert = 1 AND YEAR(ks.dato) = ")
				.append(search.getSearchByTattUtAvBrukÅr());
			isFiltered = true;
		}
		
		/* Legg til semikolon for å signifisere slutt av SQL query. */
		query.append(";");

		/* Om søket slutter med en "AND", fjern den. Ellers, fortsett.
		 */
		if (isFiltered && query.toString().trim().endsWith("AND "))
		{
			query.setLength(query.length() - 4);
		}
		
		/* SQL forespørsel er ferdig konstruert, returner InventarExtended objekt. */
		try
			(
				Connection connection = DatabaseConnectionManager.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query.toString());
			)
		{
			List<InventarExtended> result = new ArrayList<>();
			
			while (resultSet.next())
			{
				InventarExtended inventarS = new InventarExtended();
				
				inventarS.setSKU(resultSet.getInt("sku"));
				inventarS.setBeskrivelse(resultSet.getString("beskrivelse"));
				inventarS.setInnkjopsdato(resultSet.getTimestamp("innkjopsdato").toLocalDateTime());
				inventarS.setInnkjopspris(resultSet.getDouble("innkjopspris"));
				inventarS.setAntall(resultSet.getInt("antall"));
				inventarS.setForventetLevetid(resultSet.getInt("forventetLevetid"));
				inventarS.setKategori(resultSet.getString("kategori"));
				inventarS.setKategoriType(resultSet.getString("kategoriType"));
				inventarS.setPlassering(resultSet.getString("plassering"));
				inventarS.setKassert(resultSet.getString("begrunnelse"));
				
				result.add(inventarS);
			}
			
			return result;
		}
		catch (SQLException error)
		{
			error.printStackTrace(System.err);
			return Collections.emptyList();
		}
	}
}
