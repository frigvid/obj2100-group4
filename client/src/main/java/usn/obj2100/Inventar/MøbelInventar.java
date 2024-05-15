package usn.obj2100.Inventar;

import usn.obj2100.model.Inventar;
import usn.obj2100.model.Kategori;

import java.util.Date;

public class MøbelInventar extends Inventar
{
	private Kategori kategori;
	private int forventetLevetid;

	public MøbelInventar( String beskrivelse, double innkjøpsPris, String innkjøpsDato, String plassering, Kategori kategori, int forventetLevetid ) {
		super(beskrivelse, innkjøpsPris, innkjøpsDato, plassering);
		this.kategori = kategori;
		this.forventetLevetid = forventetLevetid;
	}

	public MøbelInventar( String beskrivelse, double innkjøpsPris, String innkjøpsDato, Kategori kategori, int forventetLevetid  ) {
		super(beskrivelse, innkjøpsPris, innkjøpsDato);
		this.kategori = kategori;
		this.forventetLevetid = forventetLevetid;
	}

	public Kategori getKategori() {
		return kategori;
	}

	public void setKategori( Kategori kategori ) {
		this.kategori = kategori;
	}

	public int getForventetLevetid() {
		return forventetLevetid;
	}

	public void setForventetLevetid( int forventetLevetid ) {
		this.forventetLevetid = forventetLevetid;
	}
}
