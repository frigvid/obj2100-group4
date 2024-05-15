package usn.obj2100.model.Inventar;

import java.util.Date;

public class TekniskUtstyrInventar extends Inventar{
	private String kategori;
	public TekniskUtstyrInventar( String beskrivelse, double innkjøpsPris, String innkjøpsDato, String plassering, String kategori ) {
		super(beskrivelse, innkjøpsPris, innkjøpsDato, plassering );
		this.kategori = kategori;
	}

	public TekniskUtstyrInventar( String beskrivelse, double innkjøpsPris, String innkjøpsDato, String kategori ) {
		super(beskrivelse, innkjøpsPris, innkjøpsDato);
		this.kategori = kategori;
	}

	public String getKategori() {
		return kategori;
	}

	public void setKategori( String kategori ) {
		this.kategori = kategori;
	}
}
