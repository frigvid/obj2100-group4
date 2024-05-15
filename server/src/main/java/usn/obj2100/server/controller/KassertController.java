package usn.obj2100.server.controller;

import usn.obj2100.server.service.KassertService;
import usn.obj2100.shared.model.Kassert;

import java.util.List;

public class KassertController
	implements IController<Kassert>
{
	private KassertService kassertService = new KassertService();
	
	@Override
	public List<Kassert> getAll()
	{
		return kassertService.getAll();
	}
	
	@Override
	public Kassert getById(int id)
	{
		return kassertService.get(id);
	}
	
	@Override
	public Kassert create(Kassert kassert)
	{
		return kassertService.create(kassert);
	}
	
	@Override
	public Kassert read(Kassert kassert)
	{
		return kassertService.get(kassert.getId());
	}
	
	@Override
	public boolean update(Kassert kassert)
	{
		return kassertService.update(kassert);
	}
	
	@Override
	public boolean delete(Kassert kassert)
	{
		return kassertService.delete(kassert);
	}
}
