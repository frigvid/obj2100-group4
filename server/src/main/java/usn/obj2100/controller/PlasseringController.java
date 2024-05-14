package usn.obj2100.controller;

import usn.obj2100.model.Plassering;
import usn.obj2100.service.PlasseringService;

import java.util.List;

public class PlasseringController
	implements IController<Plassering>
{
	private PlasseringService plasseringService = new PlasseringService();
	
	@Override
	public List<Plassering> getAll()
	{
		return plasseringService.getAll();
	}
	
	@Override
	public Plassering getById(int id)
	{
		return plasseringService.get(id);
	}
	
	@Override
	public Plassering create(Plassering plassering)
	{
		return plasseringService.create(plassering);
	}
	
	@Override
	public Plassering read(Plassering plassering)
	{
		return plasseringService.get(plassering.getId());
	}
	
	@Override
	public boolean update(Plassering plassering)
	{
		return plasseringService.update(plassering);
	}
	
	@Override
	public boolean delete(Plassering plassering)
	{
		return plasseringService.delete(plassering);
	}
}
