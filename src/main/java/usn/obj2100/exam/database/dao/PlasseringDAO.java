package usn.obj2100.exam.database.dao;

import usn.obj2100.exam.database.model.Plassering;

import java.sql.Connection;

public class PlasseringDAO
{
	private Connection connection;
	
	public PlasseringDAO(Connection connection)
	{
		this.connection = connection;
	}
	
	public Plassering getPlasseringById(int id)
	{
		// TODO: Implement this method. Needs to fetch a Plassering "object" from the database.
		return null;
	}
}
