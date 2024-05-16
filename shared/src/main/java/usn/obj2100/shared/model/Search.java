package usn.obj2100.shared.model;

import usn.obj2100.shared.Type;

import java.io.Serializable;
import java.sql.Date;

/**
 * Søk klasse for å bygge søk.
 * <p/>
 * Man kan enten ta et enkelt søk, som bare består av en streng,
 * som slår opp i databasetabellene inventar.beskrivelse og kategori.kategori,
 * eller om man vil ta et avansert søk med filter.
 * <p/>
 * Søk må bygges opp med den underliggende Builder klassen, ettersom at
 * det ville vært hundrevis av variasjoner for avanserte søk, siden filtere
 * er valgfrie.
 * <p/>
 * Eksempel på bruk:
 * {@snippet id="SearchExample" lang="java" group="Search" :
 * 	Search query = new Search.Builder()
 * 		.search("Ost")
 * 		.searchByBeskrivelse("Ost")
 * 		.build();
 * 	// Generisk objekt.
 * 	Client client = new Client();
 * 	Object response = client.request(query);
 * 	System.out.println(response);
 *
 * 	// Castet type.
 * 	List<List<Object>> response2 = (List<List<Object>>) client.request(query);
 * 	System.out.println(response2);
 * }
 *
 * @created 2024-05-14
 * @since 0.5
 */
public class Search
	implements Serializable
{
	/* Filtre. */
	private String searchByBeskrivelse;
	private String searchByType;
	private String searchByKategori;
	private Date searchByInnkjopsdato;
	private double[] searchByPris;
	private String searchByPlassering;
	private int[] searchByAntall;
	private int[] searchByLevetid;
	private int[] searchByForventetKassering;
	private boolean searchByIBruk;
	private boolean searchByIkkeIBruk;
	private String searchByTattUtAvBrukÅrsak;
	private int searchByTattUtAvBrukÅr;
	
	/* Grunnleggende søkestreng. */
	private String search;
	
	/**
	 * Konstruktør for å bygge et søk.
	 * <p/>
	 * Denne er bare brukt gjennom den underliggende Builder klassen.
	 *
	 * @param builder Builder objektet.
	 */
	private Search(Builder builder)
	{
		this.searchByBeskrivelse = builder.searchByBeskrivelse;
		this.searchByType = builder.searchByType;
		this.searchByKategori = builder.searchByKategori;
		this.searchByInnkjopsdato = builder.searchByInnkjopsdato;
		this.searchByPris = builder.searchByPris;
		this.searchByPlassering = builder.searchByPlassering;
		this.searchByAntall = builder.searchByAntall;
		this.searchByLevetid = builder.searchByLevetid;
		this.searchByForventetKassering = builder.searchByForventetKassering;
		this.searchByIBruk = builder.searchByIBruk;
		this.searchByIkkeIBruk = builder.searchByIkkeIBruk;
		this.searchByTattUtAvBrukÅrsak = builder.searchByTattUtAvBrukÅrsak;
		this.searchByTattUtAvBrukÅr = builder.searchByTattUtAvBrukÅr;
		this.search = builder.search;
	}
	
	/* Utilities. */
	
	/**
	 * Returnerer typen av søk.
	 *
	 * @return Type.SEARCH.
	 */
	public Type typeOf()
	{
		return Type.SEARCH;
	}
	
	/* Setters */
	
	/**
	 * Setter beskrivelse filterverdien.
	 *
	 * @param searchByBeskrivelse Filtersøkeverdien.
	 */
	public void setSearchByBeskrivelse(String searchByBeskrivelse)
	{
		this.searchByBeskrivelse = searchByBeskrivelse;
	}
	
	/**
	 * Setter type filterverdien.
	 *
	 * @param searchByType Filtersøkeverdien.
	 */
	public void setSearchByType(String searchByType)
	{
		this.searchByType = searchByType;
	}
	
	/**
	 * Setter kategori filterverdien.
	 *
	 * @param searchByKategori Filtersøkeverdien.
	 */
	public void setSearchByKategori(String searchByKategori)
	{
		this.searchByKategori = searchByKategori;
	}
	
	/**
	 * Setter innkjøpsdato filterverdien.
	 *
	 * @param searchByInnkjopsdato Filtersøkeverdien.
	 */
	public void setSearchByInnkjopsdato(Date searchByInnkjopsdato)
	{
		this.searchByInnkjopsdato = searchByInnkjopsdato;
	}
	
	/**
	 * Setter pris filterverdien.
	 *
	 * @param min Filtersøkets minimumsverdien.
	 * @param max Filtersøkets maksimumsverdien.
	 */
	public void setSearchByPris(double min, double max)
	{
		this.searchByPris = new double[]{min, max};
	}
	
	/**
	 * Setter plassering filterverdien.
	 *
	 * @param searchByPlassering Filtersøkeverdien.
	 */
	public void setSearchByPlassering(String searchByPlassering)
	{
		this.searchByPlassering = searchByPlassering;
	}
	
	/**
	 * Setter antall filterverdien.
	 *
	 * @param min Filtersøkets minimumsverdien.
	 * @param max Filtersøkets maksimumsverdien.
	 */
	public void setSearchByAntall(int min, int max)
	{
		this.searchByAntall = new int[]{min, max};
	}
	
	/**
	 * Setter levetid filterverdien.
	 *
	 * @param min Filtersøkets minimumsverdien.
	 * @param max Filtersøkets maksimumsverdien.
	 */
	public void setSearchByLevetid(int min, int max)
	{
		this.searchByLevetid = new int[]{min, max};
	}
	
	/**
	 * Setter forventet kassering filterverdien.
	 *
	 * @param min Filtersøkets minimumsverdien.
	 * @param max Filtersøkets maksimumsverdien.
	 */
	public void setSearchByForventetKassering(int min, int max)
	{
		this.searchByForventetKassering = new int[]{min, max};
	}
	
	/**
	 * Setter om gjenstanden er i bruk filterverdien.
	 *
	 * @param searchByIBruk Filtersøkeverdien. True/False.
	 */
	public void setSearchByIBruk(boolean searchByIBruk)
	{
		this.searchByIBruk = searchByIBruk;
	}
	
	/**
	 * Setter om gjenstanden ikke er i bruk filterverdien.
	 *
	 * @param searchByIkkeIBruk Filtersøkeverdien. True/False.
	 */
	public void setSearchByIkkeIBruk(boolean searchByIkkeIBruk)
	{
		this.searchByIkkeIBruk = searchByIkkeIBruk;
	}
	
	/**
	 * Setter kassert årsak filterverdien.
	 *
	 * @param searchByTattUtAvBrukÅrsak Filtersøkeverdien.
	 */
	public void setSearchByTattUtAvBrukÅrsak(String searchByTattUtAvBrukÅrsak)
	{
		this.searchByTattUtAvBrukÅrsak = searchByTattUtAvBrukÅrsak;
	}
	
	/**
	 * Setter kassert år filterverdien.
	 *
	 * @param searchByTattUtAvBrukÅr Årstall, f.eks. 2024.
	 */
	public void setSearchByTattUtAvBrukÅr(int searchByTattUtAvBrukÅr)
	{
		this.searchByTattUtAvBrukÅr = searchByTattUtAvBrukÅr;
	}
	
	/**
	 * Setter grunnsøkestrengen..
	 *
	 * @param search Søket.
	 */
	public void setSearch(String search)
	{
		this.search = search;
	}
	
	/* Getters. */
	
	/**
	 * Returnerer beskrivelse filterverdien.
	 *
	 * @return Filtersøkeverdien.
	 */
	public String getSearchByBeskrivelse()
	{
		return searchByBeskrivelse;
	}
	
	/**
	 * Returnerer type filterverdien.
	 *
	 * @return Filtersøkeverdien.
	 */
	public String getSearchByType()
	{
		return searchByType;
	}
	
	/**
	 * Returnerer kategori filterverdien.
	 *
	 * @return Filtersøkeverdien.
	 */
	public String getSearchByKategori()
	{
		return searchByKategori;
	}
	
	/**
	 * Returnerer innkjøpsdato filterverdien.
	 *
	 * @return Filtersøkeverdien.
	 */
	public Date getSearchByInnkjopsdato()
	{
		return searchByInnkjopsdato;
	}
	
	/**
	 * Returnerer pris filterverdien.
	 *
	 * @return Filtersøkeverdien.
	 */
	public double[] getSearchByPris()
	{
		return searchByPris;
	}
	
	/**
	 * Returnerer plassering filterverdien.
	 *
	 * @return Filtersøkeverdien.
	 */
	public String getSearchByPlassering()
	{
		return searchByPlassering;
	}
	
	/**
	 * Returnerer antall filterverdien.
	 *
	 * @return Filtersøkeverdien.
	 */
	public int[] getSearchByAntall()
	{
		return searchByAntall;
	}
	
	/**
	 * Returnerer levetid filterverdien.
	 *
	 * @return Filtersøkeverdien.
	 */
	public int[] getSearchByLevetid()
	{
		return searchByLevetid;
	}
	
	/**
	 * Returnerer forventet kassering filterverdien.
	 *
	 * @return Filtersøkeverdien.
	 */
	public int[] getSearchByForventetKassering()
	{
		return searchByForventetKassering;
	}
	
	/**
	 * Returnerer om gjenstanden er i bruk filterverdien.
	 *
	 * @return Filtersøkeverdien.
	 */
	public boolean isSearchByIBruk()
	{
		return searchByIBruk;
	}
	
	/**
	 * Returnerer om gjenstanden ikke er i bruk filterverdien.
	 *
	 * @return Filtersøkeverdien.
	 */
	public boolean isSearchByIkkeIBruk()
	{
		return searchByIkkeIBruk;
	}
	
	/**
	 * Returnerer kassert årsak filterverdien.
	 *
	 * @return Filtersøkeverdien.
	 */
	public String getSearchByTattUtAvBrukÅrsak()
	{
		return searchByTattUtAvBrukÅrsak;
	}
	
	/**
	 * Returnerer kassert år filterverdien.
	 *
	 * @return Filtersøkeverdien.
	 */
	public int getSearchByTattUtAvBrukÅr()
	{
		return searchByTattUtAvBrukÅr;
	}
	
	/**
	 * Returnerer grunnsøkestrengen.
	 *
	 * @return Søket.
	 */
	public String getSearch()
	{
		return search;
	}
	
	/**
	 * Builder klasse for å bygge et søk.
	 * <p/>
	 * Denne er tett relatert til Search klassen, og det er mer
	 * gunstig å innkludere den i samme fil. Dersom den skulle
	 * ekstraheres, ville det øke kompleksiteten til søk uten
	 * noen betydelig gevinst.
	 *
	 * @created 2024-05-15
	 */
	public static class Builder
	{
		private String searchByBeskrivelse;
		private String searchByType;
		private String searchByKategori;
		private Date searchByInnkjopsdato;
		private double[] searchByPris;
		private String searchByPlassering;
		private int[] searchByAntall;
		private int[] searchByLevetid;
		private int[] searchByForventetKassering;
		private boolean searchByIBruk;
		private boolean searchByIkkeIBruk;
		private String searchByTattUtAvBrukÅrsak;
		private int searchByTattUtAvBrukÅr;
		private String search;
		
		public Builder() {}
		
		/**
		 * Grunnsøket.
		 * <p/>
		 * Slår opp i databasetabellene:
		 * - inventar.beskrivelse.
		 * - kategori.kategori.
		 *
		 * @param search Søket.
		 * @return Builder objektet.
		 */
		public Builder search(String search)
		{
			this.search = search;
			return this;
		}
		
		/**
		 * Søk med filter for beskrivelse.
		 * <p/>
		 * Slår opp i databasetabellen inventar.beskrivelse.
		 *
		 * @param searchByBeskrivelse Filtersøkeverdien.
		 * @return Builder objektet.
		 */
		public Builder searchByBeskrivelse(String searchByBeskrivelse)
		{
			this.searchByBeskrivelse = searchByBeskrivelse;
			return this;
		}
		
		/**
		 * Søk med filter for type.
		 * <p/>
		 * Slår opp i databasetabellen kategoriType.type.
		 *
		 * @param searchByType Filtersøkeverdien.
		 * @return Builder objektet.
		 */
		public Builder searchByType(String searchByType)
		{
			this.searchByType = searchByType;
			return this;
		}
		
		/**
		 * Søk med filter for kategori.
		 * <p/>
		 * Slår opp i databasetabellen kategori.kategori.
		 *
		 * @param searchByKategori Filtersøkeverdien.
		 * @return Builder objektet.
		 */
		public Builder searchByKategori(String searchByKategori)
		{
			this.searchByKategori = searchByKategori;
			return this;
		}
		
		/**
		 * Søk med filter for innkjøpsdato.
		 * <p/>
		 * Slår opp i databasetabellen inventar.innkjopsdato.
		 *
		 * @param searchByInnkjopsdato Filtersøkeverdien.
		 * @return Builder objektet.
		 */
		public Builder searchByInnkjopsdato(Date searchByInnkjopsdato)
		{
			this.searchByInnkjopsdato = searchByInnkjopsdato;
			return this;
		}
		
		/**
		 * Søk med filter for pris.
		 * <p/>
		 * Slår opp i databasetabellen inventar.pris.
		 *
		 * @param min Filtersøkets minimumsverdien.
		 * @param max Filtersøkets maksimumsverdien.
		 * @return Builder objektet.
		 */
		public Builder searchByPris(double min, double max)
		{
			this.searchByPris = new double[]{min, max};
			return this;
		}
		
		/**
		 * Søk med filter for plassering.
		 * <p/>
		 * Slår opp i databasetabellen:
		 * - plassering.bygg.
		 * - plassering.floy.
		 * - plassering.etasje.
		 * - plassering.rom.
		 *
		 * @param searchByPlassering Filtersøkeverdien. Konstruert som "bygg/floy/etasje/rom".
		 * @return Builder objektet.
		 */
		public Builder searchByPlassering(String searchByPlassering)
		{
			this.searchByPlassering = searchByPlassering;
			return this;
		}
		
		/**
		 * Søk med filter for antall.
		 * <p/>
		 * Slår opp i databasetabellen inventar.antall.
		 *
		 * @param min Filtersøkets minimumsverdien.
		 * @param max Filtersøkets maksimumsverdien.
		 * @return Builder objektet.
		 */
		public Builder searchByAntall(int min, int max)
		{
			this.searchByAntall = new int[]{min, max};
			return this;
		}
		
		// TODO: Denne burde begrenses til levetiden som beskrives i eksamensoppgaven
		//			for anbefalt intervall av år.
		/**
		 * Søk med filter for levetid.
		 * <p/>
		 * Slår opp i databasetabellen inventar.levetid.
		 *
		 * @param min Filtersøkets minimumsverdien.
		 * @param max Filtersøkets maksimumsverdien.
		 * @return Builder objektet.
		 */
		public Builder searchByLevetid(int min, int max)
		{
			this.searchByLevetid = new int[]{min, max};
			return this;
		}
		
		// TODO: Denne må regnes ut fra dato tatt i bruk og forventet levetid,
		//			men man skal bare angi dette som en intervall av år.
		/**
		 * Søk med filter for forventet kassering.
		 * <p/>
		 * Beregnes ut fra innkjøpsdato og forventet levetid.
		 * Så innkjøpsdato året + forventet levetid, gir fremtidige
		 * dato.
		 *
		 * @param min Filtersøkets minimumsverdien.
		 * @param max Filtersøkets maksimumsverdien.
		 * @return Builder objektet.
		 */
		public Builder searchByForventetKassering(int min, int max)
		{
			this.searchByForventetKassering = new int[]{min, max};
			return this;
		}
		
		/**
		 * Søk med filter for om gjenstanden er i bruk.
		 * <p/>
		 * Slår opp i databasetabellen inventar.kassert.
		 *
		 * @param searchByIBruk Filtersøkeverdien. True/False.
		 * @return Builder objektet.
		 */
		public Builder searchByIBruk(boolean searchByIBruk)
		{
			// TODO: Dersom inventar.kassert er "0" eller "null",
			// 		er gjenstanden i bruk.
			this.searchByIBruk = searchByIBruk;
			return this;
		}
		
		/**
		 * Søk med filter for om gjenstanden ikke er i bruk.
		 * <p/>
		 * Slår opp i databasetabellen inventar.kassert.
		 *
		 * @param searchByIkkeIBruk Filtersøkeverdien. True/False.
		 * @return Builder objektet.
		 */
		public Builder searchByIkkeIBruk(boolean searchByIkkeIBruk)
		{
			// TODO: Dersom inventar.kassert ikke er "0" eller "null",
			// 		er gjenstanden i ikke bruk.
			this.searchByIkkeIBruk = searchByIkkeIBruk;
			return this;
		}
		
		/**
		 * Søk med filter for kassert årsak.
		 * <p/>
		 * Slår opp i databasetabellen inventar.kassertType.
		 *
		 * @param searchByTattUtAvBrukÅrsak Filtersøkeverdien. F.eks. "Solgt".
		 * @return Builder objektet.
		 */
		public Builder searchByTattUtAvBrukÅrsak(String searchByTattUtAvBrukÅrsak)
		{
			this.searchByTattUtAvBrukÅrsak = searchByTattUtAvBrukÅrsak;
			return this;
		}
		
		/**
		 * Søk med filter for kassert år.
		 * <p/>
		 * Slår opp i databasetabellen inventar.kassertAr.
		 *
		 * @param searchByTattUtAvBrukÅr Årstall, f.eks. 2024.
		 * @return Builder objektet.
		 */
		public Builder searchByTattUtAvBrukÅr(int searchByTattUtAvBrukÅr)
		{
			this.searchByTattUtAvBrukÅr = searchByTattUtAvBrukÅr;
			return this;
		}
		
		/**
		 * Bygg søket.
		 *
		 * @return Search objektet.
		 */
		public Search build()
		{
			return new Search(this);
		}
	}
}
