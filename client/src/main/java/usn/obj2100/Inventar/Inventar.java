package usn.obj2100.Inventar;

import java.util.Date;
import java.util.Objects;

public class Inventar {
	private int inventarId;
	private String beskrivelse;
	private double innkjøpsPris;
	private String innkjøpsDato;
	private String plassering;
	private String Type;
	private Kategori kategori;


	public Inventar(String beskrivelse, double innkjøpsPris, String innkjøpsDato, String plassering) {
		this.beskrivelse = beskrivelse;
		this.innkjøpsPris = innkjøpsPris;
		this.innkjøpsDato = innkjøpsDato;
		this.plassering = plassering;
	}

	public Inventar( String beskrivelse, Kategori kategori, double innkjøpsPris, String innkjøpsDato) {

		this.beskrivelse = beskrivelse;
		this.innkjøpsPris = innkjøpsPris;
		this.innkjøpsDato = innkjøpsDato;
		this.kategori = kategori;
	}

	public Inventar( String beskrivelse, double innkjøpsPris, String innkjøpsDato) {
		this.beskrivelse = beskrivelse;
		this.innkjøpsPris = innkjøpsPris;
		this.innkjøpsDato = innkjøpsDato;
	}



	@Override
	public int hashCode() {
		return Objects.hash(beskrivelse, innkjøpsPris, innkjøpsDato, plassering);
	}

	public int getInventarId() {
		return inventarId;
	}

	public void setInventarId( int inventarId ) {
		this.inventarId = inventarId;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse( String beskrivelse ) {
		this.beskrivelse = beskrivelse;
	}

	public double getInnkjøpsPris() {
		return innkjøpsPris;
	}

	public void setInnkjøpsPris( double innkjøpsPris ) {
		this.innkjøpsPris = innkjøpsPris;
	}

	public String getInnkjøpsDato() {
		return innkjøpsDato;
	}

	public void setInnkjøpsDato( String innkjøpsDato ) {
		this.innkjøpsDato = innkjøpsDato;
	}

	public String getPlassering() {
		return plassering;
	}

	public void setPlassering( String plassering ) {
		this.plassering = plassering;
	}

	public String getType() {
		return Type;
	}

	public Kategori getKategori() {
		return kategori;
	}

	public String getDetaljer() {
		return beskrivelse + " " + innkjøpsPris + " " + innkjøpsDato + " " + plassering;
	}

}
