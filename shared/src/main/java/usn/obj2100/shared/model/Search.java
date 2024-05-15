package usn.obj2100.shared.model;

public class Search {
	private String searchByBeskrivelse;
	private String searchByType;
	private String searchByKategori;
	private int searchByInnkjopsdato;
	private int[] searchByPris;
	private String searchByPlassering;
	private int[] searchByAntall;
	private int[] searchByLevetid;
	private int[] searchByForventetKassering;
	private boolean searchByIBruk;
	
	private boolean searchByIkkeIBruk;
	private String searchByTattUtAvBrukÅrsak;
	private int searchByTattUtAvBrukÅr;
	private String search;
	
	public Search() {}
	
	public Search(String search)
	{
		this.search = search;
	}
	
	public Search
	(
		String SearchByBeskrivelse,
		String searchByType,
		String searchByKategori,
		int searchByInnkjopsdato,
		int[] searchByPris,
		String searchByPlassering,
		int[] searchByAntall,
		int[] searchByLevetid,
		int[] searchByForventetKassering,
		boolean searchByIBruk,
		boolean searchByIkkeIBruk,
		String searchByTattUtAvBrukÅrsak,
		int searchByTattUtAvBrukÅr,
		String search
	)
	{
		this.searchByBeskrivelse = SearchByBeskrivelse;
		this.searchByType = searchByType;
		this.searchByKategori = searchByKategori;
		this.searchByInnkjopsdato = searchByInnkjopsdato;
		this.searchByPris = searchByPris;
		this.searchByPlassering = searchByPlassering;
		this.searchByAntall = searchByAntall;
		this.searchByLevetid = searchByLevetid;
		this.searchByForventetKassering = searchByForventetKassering;
		this.searchByIBruk = searchByIBruk;
		this.searchByIkkeIBruk = searchByIkkeIBruk;
		this.searchByTattUtAvBrukÅrsak = searchByTattUtAvBrukÅrsak;
		this.searchByTattUtAvBrukÅr = searchByTattUtAvBrukÅr;
		this.search = search;
	}
	
	/* Getters. */
	public String getSearchByBeskrivelse()
	{
		return searchByBeskrivelse;
	}
	
	public String getSearchByType()
	{
		return searchByType;
	}
	
	public String getSearchByKategori()
	{
		return searchByKategori;
	}
	
	public int getSearchByInnkjopsdato()
	{
		return searchByInnkjopsdato;
	}
	
	public int[] getSearchByPris()
	{
		return searchByPris;
	}
	
	public String getSearchByPlassering()
	{
		return searchByPlassering;
	}
	
	public int[] getSearchByAntall()
	{
		return searchByAntall;
	}
	
	public int[] getSearchByLevetid()
	{
		return searchByLevetid;
	}
	
	public int[] getSearchByForventetKassering()
	{
		return searchByForventetKassering;
	}
	
	public boolean isSearchByIBruk()
	{
		return searchByIBruk;
	}
	
	public boolean isSearchByIkkeIBruk()
	{
		return searchByIkkeIBruk;
	}
	
	public String getSearchByTattUtAvBrukÅrsak()
	{
		return searchByTattUtAvBrukÅrsak;
	}
	
	public int getSearchByTattUtAvBrukÅr()
	{
		return searchByTattUtAvBrukÅr;
	}
	
	public String getSearch()
	{
		return search;
	}
	
	/* Setters */
	
	public void setSearchByBeskrivelse(String searchByBeskrivelse)
	{
		this.searchByBeskrivelse = searchByBeskrivelse;
	}
	
	public void setSearchByType(String searchByType)
	{
		this.searchByType = searchByType;
	}
	
	public void setSearchByKategori(String searchByKategori)
	{
		this.searchByKategori = searchByKategori;
	}
	
	public void setSearchByInnkjopsdato(int searchByInnkjopsdato)
	{
		this.searchByInnkjopsdato = searchByInnkjopsdato;
	}
	
	public void setSearchByPris(int min, int max)
	{
		this.searchByPris = new int[]{min, max};
	}
	
	public void setSearchByPlassering(String searchByPlassering)
	{
		this.searchByPlassering = searchByPlassering;
	}
	
	public void setSearchByAntall(int min, int max)
	{
		this.searchByAntall = new int[]{min, max};
	}
	
	public void setSearchByLevetid(int min, int max)
	{
		this.searchByLevetid = new int[]{min, max};
	}
	
	public void setSearchByForventetKassering(int min, int max)
	{
		this.searchByForventetKassering = new int[]{min, max};
	}
	
	public void setSearchByIBruk(boolean searchByIBruk)
	{
		this.searchByIBruk = searchByIBruk;
	}
	
	public void setSearchByIkkeIBruk(boolean searchByIkkeIBruk)
	{
		this.searchByIkkeIBruk = searchByIkkeIBruk;
	}
	
	public void setSearchByTattUtAvBrukÅrsak(String searchByTattUtAvBrukÅrsak)
	{
		this.searchByTattUtAvBrukÅrsak = searchByTattUtAvBrukÅrsak;
	}
	
	public void setSearchByTattUtAvBrukÅr(int searchByTattUtAvBrukÅr)
	{
		this.searchByTattUtAvBrukÅr = searchByTattUtAvBrukÅr;
	}
	
	public void setSearch(String search)
	{
		this.search = search;
	}
}
