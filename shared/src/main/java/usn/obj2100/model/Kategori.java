package usn.obj2100.model;

import java.io.Serializable;

public class Kategori
	implements Serializable
{
	private int id;
	private int type;
	private String kategori;
	
	public Kategori() {}
	
	public Kategori
		(
			int id
		)
	{
		setId(id);
	}
	
	// default
	public Kategori
		(
			int type,
			String kategori
		)
	{
		setType(type);
		setKategori(kategori);
	}
	
	public Kategori
		(
			int id,
			int type,
			String kategori
		)
	{
		setId(id);
		setType(type);
		setKategori(kategori);
	}
	
	/* Utilities. */
	
	/* Setters. */
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setType(int type)
	{
		this.type = type;
	}
	
	public void setKategori(String kategori)
	{
		this.kategori = kategori;
	}
	
	/* Getters. */
	public int getId()
	{
		return id;
	}
	
	public int getType()
	{
		return type;
	}
	
	public String getKategori()
	{
		return kategori;
	}
}
