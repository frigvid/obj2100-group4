package usn.obj2100.shared.model;

import usn.obj2100.shared.Type;

import java.io.Serializable;

public class KategoriType
	implements Serializable
{
	private int id;
	private String type;
	
	public KategoriType() {}
	
	public KategoriType(int id)
	{
		setId(id);
	}
	
	public KategoriType(String type)
	{
		setType(type);
	}
	
	public KategoriType
	(
		int id,
		String type
	)
	{
		setId(id);
		setType(type);
	}
	
	/* Utilities. */
	@Override
	public String toString()
	{
		return "KategoriType{" +
			"id=" + id +
			", type='" + type + '\'' +
			'}';
	}
	
	public Type typeOf()
	{
		return Type.KATEGORI_TYPE;
	}
	
	/* Setters. */
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	/* Getters. */
	public int getId()
	{
		return id;
	}
	
	public String getType()
	{
		return type;
	}
}
