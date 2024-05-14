package usn.obj2100.model;

import java.io.Serializable;

public class KassertType
	implements Serializable
{
	private int id;
	private String begrunnelse;
	
	public KassertType() {}
	
	public KassertType(int id)
	{
		setId(id);
	}
	
	public KassertType(String begrunnelse)
	{
		setBegrunnelse(begrunnelse);
	}
	
	public KassertType
	(
		int id,
		String begrunnelse
	)
	{
		setId(id);
		setBegrunnelse(begrunnelse);
	}
	
	/* Utilities. */
	@Override
	public String toString()
	{
		return "KassertType{" +
			"id=" + id +
			", begrunnelse='" + begrunnelse + '\'' +
			'}';
	}
	
	/* Setters. */
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setBegrunnelse(String begrunnelse)
	{
		this.begrunnelse = begrunnelse;
	}
	
	/* Getters. */
	public int getId()
	{
		return id;
	}
	
	public String getBegrunnelse()
	{
		return begrunnelse;
	}
}
