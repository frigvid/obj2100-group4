package usn.obj2100.Inventar;

public class Kategori {
	private int kategoriId;
	private String kategoriNavn;

	public Kategori( String kategoriNavn ) {
		this.kategoriNavn = kategoriNavn;
	}

	public int getKategoriId() {
		return kategoriId;
	}

	public void setKategoriId( int kategoriId ) {
		this.kategoriId = kategoriId;
	}

	public String getKategoriNavn() {
		return kategoriNavn;
	}

	public void setKategoriNavn( String kategoriNavn ) {
		this.kategoriNavn = kategoriNavn;
	}
}
