package usn.obj2100.controller;

import usn.obj2100.model.Inventar;
import usn.obj2100.service.InventarService;

import java.util.List;

public class InventarController
	implements IController<Inventar>
{
	private InventarService inventarService = new InventarService();
	
	@Override
	public List<Inventar> getAll()
	{
		return inventarService.getAll();
	}
	
	@Override
	public Inventar getById(int id)
	{
		return inventarService.get(id);
	}
	
	@Override
	public boolean create(Inventar inventar)
	{
		return inventarService.create(inventar);
	}
	
	@Override
	public Inventar read(Inventar inventar)
	{
		return inventarService.get(inventar.getSKU());
	}
	
	@Override
	public boolean update(Inventar inventar)
	{
		return inventarService.update(inventar);
	}
	
	@Override
	public boolean delete(Inventar inventar)
	{
		return inventarService.delete(inventar);
	}
}
