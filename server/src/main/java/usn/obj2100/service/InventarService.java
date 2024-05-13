package usn.obj2100.service;

import java.sql.Connection;
import java.util.List;
import usn.obj2100.model.Inventar;
import usn.obj2100.dao.InventarDAO;

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
