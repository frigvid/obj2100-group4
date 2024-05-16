package usn.obj2100.shared.model;

import usn.obj2100.shared.Type;

import java.io.Serializable;

/**
 * This class represents a location in the inventory.
 *
 * @version 0.1
 * @created 2024-05-14
 */
public class Plassering
	implements Serializable
{
	private int id;
	private String bygg;
	private int floy;
	private int etasje;
	private int rom;
	
	public Plassering() {}
	
	public Plassering(int id)
	{
		setId(id);
	}
	
	/**
	 * Constructor for Plassering elements.
	 *
	 * @param bygg Building.
	 * @param floy Floor.
	 * @param etasje Story.
	 * @param rom Room.
	 */
	public Plassering
	(
		String bygg,
		int floy,
		int etasje,
		int rom
	)
	{
		setBygg(bygg);
		setFloy(floy);
		setEtasje(etasje);
		setRom(rom);
	}
	
	/**
	 * Constructor for Plassering elements.
	 *
	 * @param id ID.
	 * @param bygg Building.
	 * @param floy Floor.
	 * @param etasje Story.
	 * @param rom Room.
	 */
	public Plassering
	(
		int id,
		String bygg,
		int floy,
		int etasje,
		int rom
	)
	{
		setId(id);
		setBygg(bygg);
		setFloy(floy);
		setEtasje(etasje);
		setRom(rom);
	}
	
	/* Utilities. */
	/**
	 * Get the string representation of the Plassering element.
	 *
	 * @return String representation.
	 */
	@Override
	public String toString()
	{
		return "Plassering{" +
			"id=" + id +
			", bygg=" + bygg +
			", floy=" + floy +
			", etasje=" + etasje +
			", rom=" + rom +
			"}";
	}
	
	public Type typeOf()
	{
		return Type.PLASSERING;
	}
	
	/* Setters. */
	/**
	 * Set the ID of the Plassering element.
	 *
	 * @param id ID.
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * Set the building of the Plassering element.
	 *
	 * @param bygg Building.
	 */
	public void setBygg(String bygg)
	{
		this.bygg = bygg;
	}
	
	/**
	 * Set the floor of the Plassering element.
	 *
	 * @param floy Floor.
	 */
	public void setFloy(int floy)
	{
		this.floy = floy;
	}
	
	/**
	 * Set the story of the Plassering element.
	 *
	 * @param etasje Story.
	 */
	public void setEtasje(int etasje)
	{
		this.etasje = etasje;
	}
	
	/**
	 * Set the room of the Plassering element.
	 *
	 * @param rom Room.
	 */
	public void setRom(int rom)
	{
		this.rom = rom;
	}
	
	/* Getters. */
	/**
	 * Get the ID of the Plassering element.
	 *
	 * @return ID.
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Get the building of the Plassering element.
	 *
	 * @return Building.
	 */
	public String getBygg()
	{
		return bygg;
	}
	
	/**
	 * Get the floor of the Plassering element.
	 *
	 * @return Floor.
	 */
	public int getFloy()
	{
		return floy;
	}
	
	/**
	 * Get the story of the Plassering element.
	 *
	 * @return Story.
	 */
	public int getEtasje()
	{
		return etasje;
	}
	
	/**
	 * Get the room of the Plassering element.
	 *
	 * @return Room.
	 */
	public int getRom()
	{
		return rom;
	}
}
