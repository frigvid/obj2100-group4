package usn.obj2100.shared.model;

import usn.obj2100.shared.Type;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class represents an item in the inventory.
 * <p/>
 * This is a copy of Inventar, but unlike it, this
 * is designed to contain the actual data of the other
 * tables as well.
 * <p/>
 * Essentially, this one is used for search, as it makes
 * it easier.
 *
 * @version 0.6
 * @created 2024-05-15
 */
public class InventarExtended
	implements Serializable
{
	private int sku;
	private String beskrivelse;
	private LocalDateTime innkjopsdato;
	private double innkjopspris;
	private int antall;
	private int forventetLevetid;
	private String kategori;
	private String kategoriType;
	private String plassering;
	private String kassert;
	
	public InventarExtended() {}
	
	public InventarExtended(int sku)
	{
		setSKU(sku);
	}
	
	/**
	 * Constructor for Inventar elements.
	 *
	 * @param beskrivelse Description of the item.
	 * @param innkjopsdato Date of purchase.
	 * @param innkjopspris Purchase price.
	 * @param antall Number of items.
	 * @param forventetLevetid Expected lifetime of the item.
	 * @param kategori Category.
	 * @param plassering Location.
	 * @param kassert Whether the item has been discarded.
	 */
	public InventarExtended
	(
		int sku,
		String beskrivelse,
		LocalDateTime innkjopsdato,
		double innkjopspris,
		int antall,
		int forventetLevetid,
		String kategori,
		String kategoriType,
		String plassering,
		String kassert
	)
	{
		setSKU(sku);
		setBeskrivelse(beskrivelse);
		setInnkjopsdato(innkjopsdato);
		setInnkjopspris(innkjopspris);
		setAntall(antall);
		setForventetLevetid(forventetLevetid);
		setKategori(kategori);
		setKategoriType(kategoriType);
		setPlassering(plassering);
		setKassert(kassert);
	}
	
	/* Utilities. */
	/**
	 * toString method to stringify an Inventar object.
	 *
	 * @return A string representation of the object.
	 */
	@Override
	public String toString()
	{
		return "Inventar{" + "sku=" + sku +
			", beskrivelse=" + beskrivelse +
			", innkjopsdato=" + innkjopsdato +
			", innkjopspris=" + innkjopspris +
			", antall=" + antall +
			", forventetLevetid=" + forventetLevetid +
			", kategori=" + kategori +
			", plassering=" + plassering +
			", kassert=" + kassert +
			"}";
	}
	
	@Override
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		
		if (object == null || getClass() != object.getClass())
		{
			return false;
		}
		
		InventarExtended inventar = (InventarExtended) object;
		return sku == inventar.sku &&
			Double.compare(inventar.innkjopspris, innkjopspris) == 0 &&
			antall == inventar.antall &&
			forventetLevetid == inventar.forventetLevetid &&
			Objects.equals(beskrivelse, inventar.beskrivelse) &&
			Objects.equals(innkjopsdato, inventar.innkjopsdato) &&
			Objects.equals(kategori, inventar.kategori) &&
			Objects.equals(kategoriType, inventar.kategoriType) &&
			Objects.equals(plassering, inventar.plassering) &&
			Objects.equals(kassert, inventar.kassert);
	}
	
	/**
	 * Method to get the type of the object.
	 *
	 * @return The type of the object.
	 */
	public Type typeOf()
	{
		return Type.INVENTAR;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(beskrivelse, innkjopspris, innkjopsdato, plassering);
	}
	
	/* Setters. */
	/**
	 * Method to set the SKU/ID of the item.
	 *
	 * @param sku The SKU/ID of the item.
	 */
	public void setSKU(int sku)
	{
		this.sku = sku;
	}
	
	/**
	 * Method to set the description of the item.
	 *
	 * @param beskrivelse The description of the item.
	 */
	public void setBeskrivelse(String beskrivelse)
	{
		this.beskrivelse = beskrivelse;
	}
	
	/**
	 * Method to set the purchase date of the item.
	 *
	 * @param innkjopsdato The purchase date of the item.
	 */
	public void setInnkjopsdato(LocalDateTime innkjopsdato)
	{
		this.innkjopsdato = innkjopsdato;
	}
	
	/**
	 * Method to set the purchase price of the item.
	 *
	 * @param innkjopspris The purchase price of the item.
	 */
	public void setInnkjopspris(double innkjopspris)
	{
		this.innkjopspris = innkjopspris;
	}
	
	/**
	 * Method to set the number of items.
	 *
	 * @param antall The number of items.
	 */
	public void setAntall(int antall)
	{
		this.antall = antall;
	}
	
	/**
	 * Method to set the expected lifetime of the item.
	 *
	 * @param forventetLevetid The expected lifetime of the item.
	 */
	public void setForventetLevetid(int forventetLevetid)
	{
		this.forventetLevetid = forventetLevetid;
	}
	
	/**
	 * Method to set the category of the item.
	 *
	 * @param kategori The category of the item.
	 */
	public void setKategori(String kategori)
	{
		this.kategori = kategori;
	}
	
	/**
	 * Method to set the category type of the item.
	 *
	 * @param kategoriType The category type of the item.
	 */
	public void setKategoriType(String kategoriType)
	{
		this.kategoriType = kategoriType;
	}
	
	// TODO: This should be a String. And the 4 parts should be split out from the string.
	/**
	 * Method to set the location of the item.
	 *
	 * @param plassering The location of the item.
	 */
	public void setPlassering(String plassering)
	{
		this.plassering = plassering;
	}
	
	/**
	 * Method to set whether the item has been discarded.
	 *
	 * @param kassert Whether the item has been discarded.
	 */
	public void setKassert(String kassert)
	{
		this.kassert = kassert;
	}
	
	/* Getters. */
	/**
	 * Method to get the SKU/ID of the item.
	 *
	 * @return The SKU/ID of the item.
	 */
	public int getSKU()
	{
		return sku;
	}
	
	/**
	 * Method to get the description of the item.
	 *
	 * @return The description of the item.
	 */
	public String getBeskrivelse()
	{
		return beskrivelse;
	}
	
	/**
	 * Method to get the purchase date of the item.
	 *
	 * @return The purchase date of the item.
	 */
	public LocalDateTime getInnkjopsdato()
	{
		return innkjopsdato;
	}
	
	/**
	 * Method to get the purchase price of the item.
	 *
	 * @return The purchase price of the item.
	 */
	public double getInnkjopspris()
	{
		return innkjopspris;
	}
	
	/**
	 * Method to get the number of items.
	 *
	 * @return The number of items.
	 */
	public int getAntall()
	{
		return antall;
	}
	
	/**
	 * Method to get the expected lifetime of the item.
	 *
	 * @return The expected lifetime of the item.
	 */
	public int getForventetLevetid()
	{
		return forventetLevetid;
	}
	
	/**
	 * Method to get the category of the item.
	 *
	 * @return The category of the item.
	 */
	public String getKategori()
	{
		return kategori;
	}
	
	/**
	 * Method to get the category type of the item.
	 *
	 * @return The category type of the item.
	 */
	public String getKategoriType()
	{
		return kategoriType;
	}
	
	// TODO: This should be a String. And the 4 parts should be split out from the string.
	/**
	 * Method to get the location of the item.
	 *
	 * @return The location of the item.
	 */
	public String getPlassering()
	{
		return plassering;
	}
	
	/**
	 * Method to get whether the item has been discarded.
	 *
	 * @return Whether the item has been discarded.
	 */
	public String getKassert()
	{
		return kassert;
	}
}
