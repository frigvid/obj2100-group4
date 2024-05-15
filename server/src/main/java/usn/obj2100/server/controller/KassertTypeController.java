package usn.obj2100.server.controller;

import usn.obj2100.server.service.KassertTypeService;
import usn.obj2100.shared.model.KassertType;

import java.util.List;

public class KassertTypeController
	implements IController<KassertType>
{
	public KassertTypeService kassertTypeService = new KassertTypeService();
	
	@Override
	public List<KassertType> getAll()
	{
		return kassertTypeService.getAll();
	}
	
	@Override
	public KassertType getById(int id)
	{
		return kassertTypeService.get(id);
	}
	
	@Override
	public KassertType create(KassertType kassertType)
	{
		return kassertTypeService.create(kassertType);
	}
	
	@Override
	public KassertType read(KassertType kassertType)
	{
		return kassertTypeService.get(kassertType.getId());
	}
	
	@Override
	public boolean update(KassertType kassertType)
	{
		return kassertTypeService.update(kassertType);
	}
	
	@Override
	public boolean delete(KassertType kassertType)
	{
		return kassertTypeService.delete(kassertType);
	}
}
