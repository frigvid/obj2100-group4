package usn.obj2100.exam.database.model;

import java.util.Date;

public class Inventar
{
	private int sku;
	private String beskrivelse;
	private Date innkjopsdato;
	private double innkjopspris;
	private int antall;
	private int forventetLevetid;
	private int kategori;
	private int plassering;
	private int kassert;
	
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
			Date innkjopsdato,
			double innkjopspris,
			int antall,
			int forventetLevetid,
			int kategori,
			int plassering,
			int kassert
		)
	{
		setSku(sku);
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
			Date innkjopsdato,
			double innkjopspris,
			int antall,
			int kategori,
			int plassering,
			int kassert
		)
	{
		setSku(sku);
		setBeskrivelse(beskrivelse);
		setInnkjopsdato(innkjopsdato);
		setInnkjopspris(innkjopspris);
		setAntall(antall);
		setKategori(kategori);
		setPlassering(plassering);
		setKassert(kassert);
	}
	
	/* Setters. */
	private void setSku(int sku)
	{
		this.sku = sku;
	}
	
	private void setBeskrivelse(String beskrivelse)
	{
		this.beskrivelse = beskrivelse;
	}
	
	private void setInnkjopsdato(Date innkjopsdato)
	{
		this.innkjopsdato = innkjopsdato;
	}
	
	private void setInnkjopspris(double innkjopspris)
	{
		this.innkjopspris = innkjopspris;
	}
	
	private void setAntall(int antall)
	{
		this.antall = antall;
	}
	
	private void setForventetLevetid(int forventetLevetid)
	{
		this.forventetLevetid = forventetLevetid;
	}
	
	private void setKategori(int kategori)
	{
		this.kategori = kategori;
	}
	
	private void setPlassering(int plassering)
	{
		this.plassering = plassering;
	}
	
	public void setKassert(int kassert)
	{
		this.kassert = kassert;
	}
	
	/* Getters. */
	public int getSku()
	{
		return sku;
	}
	
	public String getBeskrivelse()
	{
		return beskrivelse;
	}
	
	public Date getInnkjopsdato()
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
	
	public int getPlassering()
	{
		return plassering;
	}
	
	public int getKassert()
	{
		return kassert;
	}
}
