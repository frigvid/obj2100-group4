package usn.obj2100.Inventar;

import usn.obj2100.model.Inventar;

import java.util.Date;

public class UtsmykningInventar extends Inventar
{
	private Kategori kategori;

	public UtsmykningInventar( String beskrivelse, double innkjøpsPris, String innkjøpsDato, String plassering, Kategori kategori ) {
		super(beskrivelse, innkjøpsPris, innkjøpsDato, plassering);
		this.kategori = kategori;
	}

	public UtsmykningInventar( String beskrivelse, double innkjøpsPris, String innkjøpsDato, Kategori kategori  ) {
		super(beskrivelse, innkjøpsPris, innkjøpsDato);
		this.kategori = kategori;
	}

	public Kategori getKategori() {
		return kategori;
	}

	public void setKategori( Kategori kategori ) {
		this.kategori = kategori;
	}
}
