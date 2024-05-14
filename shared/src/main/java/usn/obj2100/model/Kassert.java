package usn.obj2100.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Kassert
	implements Serializable
{
	private int id;
	private Date dato;
	private Timestamp tid;
	private int begrunnelse;
	
	public Kassert() {}
	
	public Kassert(int id)
	{
		setId(id);
	}
	
	public Kassert
	(
		Date dato,
		Timestamp tid,
		int begrunnelse
	)
	{
		setDato(dato);
		setTid(tid);
		setBegrunnelse(begrunnelse);
	}
	
	public Kassert
	(
		int id,
		Date dato,
		Timestamp tid,
		int begrunnelse
	)
	{
		setId(id);
		setDato(dato);
		setTid(tid);
		setBegrunnelse(begrunnelse);
	}
	
	/* Utilities. */
	/* Setters. */
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setDato(Date dato)
	{
		this.dato = dato;
	}
	
	public void setTid(Timestamp tid)
	{
		this.tid = tid;
	}
	
	public void setBegrunnelse(int begrunnelse)
	{
		this.begrunnelse = begrunnelse;
	}
	
	/* Getters. */
	public int getId()
	{
		return id;
	}
	
	public Date getDato()
	{
		return dato;
	}
	
	public Timestamp getTid()
	{
		return tid;
	}
	
	public int getBegrunnelse()
	{
		return begrunnelse;
	}
}
