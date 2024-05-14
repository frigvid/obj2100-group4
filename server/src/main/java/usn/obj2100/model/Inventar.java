package usn.obj2100.model;

import java.time.LocalDateTime;

public class Inventar
{
	private int sku;
	private String beskrivelse;
	private LocalDateTime innkjopsdato;
	private double innkjopspris;
	private int antall;
	private int forventetLevetid;
	private int kategori;
	private int plassering;
	private int kassert;
	
	public Inventar() {}
	
	/**
	 * Constructor for Inventar elements.
	 *
	 * @param sku SKU/ID.
	 * @param beskrivelse Description of the item.
	 * @param innkjopsdato Date of purchase.
	 * @param innkjopspris Purchase price.
	 * @param antall Number of items.
	 * @param forventetLevetid Expected lifetime of the item.
	 * @param kategori Category.
	 * @param plassering Location.
	 * @param kassert Whether the item has been discarded.
	 */
	public Inventar
		(
			int sku,
			String beskrivelse,
			LocalDateTime innkjopsdato,
			double innkjopspris,
			int antall,
			int forventetLevetid,
			int kategori,
			int plassering,
			int kassert
		)
	{
		setSKU(sku);
		setBeskrivelse(beskrivelse);
		setInnkjopsdato(innkjopsdato);
		setInnkjopspris(innkjopspris);
		setAntall(antall);
		setForventetLevetid(forventetLevetid);
		setKategori(kategori);
		setPlassering(plassering);
		setKassert(kassert);
	}
	
	/**
	 * Overloaded constructor for Inventar elements.
	 * <p/>
	 * Skips the forventetLevetid parameter, as it is not required for
	 * the creation of "teknisk utstyr."
	 *
	 * @param sku SKU/ID.
	 * @param beskrivelse Description of the item.
	 * @param innkjopsdato Date of purchase.
	 * @param innkjopspris Purchase price.
	 * @param antall Number of items.
	 * @param kategori Category.
	 * @param plassering Location.
	 * @param kassert Whether the item has been discarded.
	 */
	public Inventar
		(
			int sku,
			String beskrivelse,
			LocalDateTime innkjopsdato,
			double innkjopspris,
			int antall,
			int kategori,
			int plassering,
			int kassert
		)
	{
		setSKU(sku);
		setBeskrivelse(beskrivelse);
		setInnkjopsdato(innkjopsdato);
		setInnkjopspris(innkjopspris);
		setAntall(antall);
		setKategori(kategori);
		setPlassering(plassering);
		setKassert(kassert);
	}
	
	/* Setters. */
	public void setSKU(int sku)
	{
		this.sku = sku;
	}
	
	public void setBeskrivelse(String beskrivelse)
	{
		this.beskrivelse = beskrivelse;
	}
	
	public void setInnkjopsdato(LocalDateTime innkjopsdato)
	{
		this.innkjopsdato = innkjopsdato;
	}
	
	public void setInnkjopspris(double innkjopspris)
	{
		this.innkjopspris = innkjopspris;
	}
	
	public void setAntall(int antall)
	{
		this.antall = antall;
	}
	
	public void setForventetLevetid(int forventetLevetid)
	{
		this.forventetLevetid = forventetLevetid;
	}
	
	public void setKategori(int kategori)
	{
		this.kategori = kategori;
	}
	
	// TODO: This should be a String. And the 4 parts should be split out from the string.
	public void setPlassering(int plassering)
	{
		this.plassering = plassering;
	}
	
	public void setKassert(int kassert)
	{
		this.kassert = kassert;
	}
	
	/* Getters. */
	public int getSKU()
	{
		return sku;
	}
	
	public String getBeskrivelse()
	{
		return beskrivelse;
	}
	
	public LocalDateTime getInnkjopsdato()
	{
		return innkjopsdato;
	}
	
	public double getInnkjopspris()
	{
		return innkjopspris;
	}
	
	public int getAntall()
	{
		return antall;
	}
	
	public int getForventetLevetid()
	{
		return forventetLevetid;
	}
	
	public int getKategori()
	{
		return kategori;
	}
	
	// TODO: This should be a String. And the 4 parts should be split out from the string.
	public int getPlassering()
	{
		return plassering;
	}
	
	public int getKassert()
	{
		return kassert;
	}
}
