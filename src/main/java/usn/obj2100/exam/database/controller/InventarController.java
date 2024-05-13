package usn.obj2100.exam.database.controller;

import usn.obj2100.exam.database.service.InventarService;

import java.sql.DriverManager;

public class InventarController
{
	private final InventarService inventarService;
	
	public InventoryController() throws SQLException
	{
		Connection connection = DriverManager.getConnection()
		this.inventarService = new InventarService(DatabaseConnectionManager.getConnection());
	}
}
