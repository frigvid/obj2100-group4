package usn.obj2100.controller;

import usn.obj2100.model.Inventar;
import usn.obj2100.service.InventarService;

import java.util.List;

public class InventarController
{
	private InventarService inventarService = new InventarService();
	
	public List<Inventar> getAllInventar()
	{
		return inventarService.getAll();
	}
	
	public Inventar getInventarById(int id)
	{
		return inventarService.get(id);
	}
	
	public void createInventar(Inventar inventar)
	{
		inventarService.create(inventar);
	}
	
	public void updateInventar(Inventar inventar)
	{
		inventarService.update(inventar);
	}
	
	public void deleteInventar(Inventar inventar)
	{
		inventarService.delete(inventar);
	}
}
