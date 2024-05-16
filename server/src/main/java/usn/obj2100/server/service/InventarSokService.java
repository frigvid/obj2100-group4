package usn.obj2100.server.service;

import usn.obj2100.server.DatabaseConnectionManager;
import usn.obj2100.shared.model.Inventar;
import usn.obj2100.shared.model.Search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InventarSokService {
	public List<Inventar> searchInventory(Search searchCriteria) {
		List<Inventar> results = new ArrayList<>();
		List<Object> parameters = new ArrayList<>();
		StringBuilder query = new StringBuilder("SELECT * FROM inventar WHERE 1=1");

		if (searchCriteria.getSearch() != null && !searchCriteria.getSearch().isEmpty()) {
			query.append(" AND beskrivelse LIKE ?");
			parameters.add("%" + searchCriteria.getSearch() + "%");
		}


		// Adding conditions based on non-null and non-default search criteria
		if (searchCriteria.getSearchByBeskrivelse() != null && !searchCriteria.getSearchByBeskrivelse().isEmpty()) {
			query.append(" AND beskrivelse LIKE ?");
			parameters.add("%" + searchCriteria.getSearchByBeskrivelse() + "%");
		}

		if (searchCriteria.getSearchByType() != null && !searchCriteria.getSearchByType().isEmpty()) {
			query.append(" AND type = ?");
			parameters.add(searchCriteria.getSearchByType());
		}

		if (searchCriteria.getSearchByKategori() != null && !searchCriteria.getSearchByKategori().isEmpty()) {
			query.append(" AND kategori = ?");
			parameters.add(searchCriteria.getSearchByKategori());
		}

		// Example for date and numeric range queries
		//TODO Fix date datatype!
		/*if (searchCriteria.getSearchByInnkjopsdato() != 0) {
			query.append(" AND innkjopsdato >= ?");
			parameters.add(new Date(searchCriteria.getSearchByInnkjopsdato()));  // Adjust based on actual data type
		}*/

		// Handling ranges like price
		if (searchCriteria.getSearchByPris() != null && searchCriteria.getSearchByPris().length == 2) {
			query.append(" AND innkjopspris BETWEEN ? AND ?");
			parameters.add(searchCriteria.getSearchByPris()[0]);
			parameters.add(searchCriteria.getSearchByPris()[1]);
		}

		// Repeat similar blocks for other fields...

		try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
			  PreparedStatement stmt = conn.prepareStatement(query.toString())) {

			for (int i = 0; i < parameters.size(); i++) {
				stmt.setObject(i + 1, parameters.get(i));
			}

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					results.add(mapRowToInventory(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results;
	}

	private Inventar mapRowToInventory(ResultSet rs) throws SQLException {
		// Map fields from the ResultSet to the Inventory object
		Inventar inventory = new Inventar();
		inventory.setSKU(rs.getInt("sku"));
		inventory.setBeskrivelse(rs.getString("beskrivelse"));
		inventory.setAntall(rs.getInt("antall"));
		inventory.setInnkjopsdato((LocalDateTime) rs.getTimestamp("innkjopsdato").toLocalDateTime());
		inventory.setInnkjopspris(rs.getDouble("innkjopspris"));
		inventory.setForventetLevetid(rs.getInt("forventetlevetid"));
		inventory.setKategori(rs.getInt("kategori"));
		inventory.setPlassering(rs.getInt("plassering"));
		inventory.setKassert(rs.getInt("kassert"));
		// Example: inventory.setId(rs.getInt("id"));
		// Repeat for all necessary fields
		return inventory;
	}

}
