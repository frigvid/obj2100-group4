package usn.obj2100.Search;

import java.util.Date;

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


	public Search( String search ) {
		this.search = search;
	}

	public Search(String SearchByBeskrivelse, String searchByType, String searchByKategori, int searchByInnkjopsdato, int[] searchByPris, String searchByPlassering, int[] searchByAntall, int[] searchByLevetid, int[] searchByForventetKassering, boolean searchByIBruk, boolean searchByIkkeIBruk, String searchByTattUtAvBrukÅrsak, int searchByTattUtAvBrukÅr, String search ){
		this.searchByBeskrivelse = searchByBeskrivelse;
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

	public Search(){

	}

	public String getSearchByBeskrivelse() {
		return searchByBeskrivelse;
	}

	public void setSearchByBeskrivelse( String searchByBeskrivelse ) {
		this.searchByBeskrivelse = searchByBeskrivelse;
	}

	public String getSearchByType() {
		return searchByType;
	}

	public void setSearchByType( String searchByType ) {
		this.searchByType = searchByType;
	}

	public String getSearchByKategori() {
		return searchByKategori;
	}

	public void setSearchByKategori( String searchByKategori ) {
		this.searchByKategori = searchByKategori;
	}

	public int getSearchByInnkjopsdato() {
		return searchByInnkjopsdato;
	}

	public void setSearchByInnkjopsdato( int searchByInnkjopsdato ) {
		this.searchByInnkjopsdato = searchByInnkjopsdato;
	}

	public int[] getSearchByPris() {
		return searchByPris;
	}

	public void setSearchByPris( int min, int max ) {
		this.searchByPris = new int[]{min, max};
	}

	public String getSearchByPlassering() {
		return searchByPlassering;
	}

	public void setSearchByPlassering( String searchByPlassering ) {
		this.searchByPlassering = searchByPlassering;
	}

	public int[] getSearchByAntall() {
		return searchByAntall;
	}

	public void setSearchByAntall( int min, int max ) {
		this.searchByAntall = new int[]{min, max};
	}

	public int[] getSearchByLevetid() {
		return searchByLevetid;
	}

	public void setSearchByLevetid( int min, int max ) {
		this.searchByLevetid = new int[]{min, max};
	}

	public int[] getSearchByForventetKassering() {
		return searchByForventetKassering;
	}

	public void setSearchByForventetKassering( int min, int max ) {
		this.searchByForventetKassering = new int[]{min, max};
	}

	public boolean isSearchByIBruk() {
		return searchByIBruk;
	}

	public void setSearchByIBruk( boolean searchByIBruk ) {
		this.searchByIBruk = searchByIBruk;
	}

	public boolean isSearchByIkkeIBruk() {
		return searchByIkkeIBruk;
	}

	public void setSearchByIkkeIBruk( boolean searchByIkkeIBruk ) {
		this.searchByIkkeIBruk = searchByIkkeIBruk;
	}

	public String getSearchByTattUtAvBrukÅrsak() {
		return searchByTattUtAvBrukÅrsak;
	}

	public void setSearchByTattUtAvBrukÅrsak( String searchByTattUtAvBrukÅrsak ) {
		this.searchByTattUtAvBrukÅrsak = searchByTattUtAvBrukÅrsak;
	}

	public int getSearchByTattUtAvBrukÅr() {
		return searchByTattUtAvBrukÅr;
	}

	public void setSearchByTattUtAvBrukÅr( int searchByTattUtAvBrukÅr ) {
		this.searchByTattUtAvBrukÅr = searchByTattUtAvBrukÅr;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch( String search ) {
		this.search = search;
	}
}
