package usn.obj2100.exam.database.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import usn.obj2100.exam.database.model.Inventar;
import usn.obj2100.exam.database.dao.InventarDAO;

public class InventarService
{
	private final InventarDAO inventarDAO;
	
	public InventarService(Connection connection)
	{
		this.inventarDAO = new InventarDAO(connection);
	}
	
	public Inventar getInventoryItem(int sku)
	{
		try
		{
			return inventarDAO.get(sku);
		}
		catch (RuntimeException error)
		{
			throw new RuntimeException("Fikk ikke tak i inventar med SKU: " + sku, error);
		}
	}
	
	public List<Inventar> getAllInventoryItems()
	{
		try
		{
			return inventarDAO.getAll();
		}
		catch (RuntimeException error)
		{
			throw new RuntimeException("Fikk ikke tak i alle inventar elementer.", error);
		}
	}
}
