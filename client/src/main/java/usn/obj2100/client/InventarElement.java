package usn.obj2100.client;

public class InventarElement {
	private String type;
	private String kategori;
	private String beskrivelse;
	private String innkjopsdato;
	private double innkjopspris;
	private String plassering;
	private int antall;
	private Integer levetid; // Nullbar for noen typer inventar
	private String datoTattUtAvBruk;
	private String aarsakTattUtAvBruk;

	public InventarElement(String type, String kategori, String beskrivelse, String innkjopsdato,
								  double innkjopspris, String plassering, int antall, Integer levetid) {
		this.type = type;
		this.kategori = kategori;
		this.beskrivelse = beskrivelse;
		this.innkjopsdato = innkjopsdato;
		this.innkjopspris = innkjopspris;
		this.plassering = plassering;
		this.antall = antall;
		this.levetid = levetid;
	}

	// Getters and setters
	public String getType() {
		return type;
	}

	public String getKategori() {
		return kategori;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public String getInnkjopsdato() {
		return innkjopsdato;
	}

	public double getInnkjopspris() {
		return innkjopspris;
	}

	public String getPlassering() {
		return plassering;
	}

	public int getAntall() {
		return antall;
	}

	public Integer getLevetid() {
		return levetid;
	}

	public String getDatoTattUtAvBruk() {
		return datoTattUtAvBruk;
	}

	public void setDatoTattUtAvBruk(String dato) {
		this.datoTattUtAvBruk = dato;
	}

	public String getAarsakTattUtAvBruk() {
		return aarsakTattUtAvBruk;
	}

	public void setAarsakTattUtAvBruk(String aarsak) {
		this.aarsakTattUtAvBruk = aarsak;
	}
}
