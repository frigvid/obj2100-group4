package usn.obj2100.controller;

import usn.obj2100.model.KategoriType;
import usn.obj2100.service.KategoriTypeService;

import java.util.List;

public class KategoriTypeController
	implements IController<KategoriType>
{
	private KategoriTypeService kategoriTypeService = new KategoriTypeService();
	
	@Override
	public List<KategoriType> getAll()
	{
		return kategoriTypeService.getAll();
	}
	
	@Override
	public KategoriType getById(int id)
	{
		return kategoriTypeService.get(id);
	}
	
	@Override
	public KategoriType create(KategoriType kategoriType)
	{
		return kategoriTypeService.create(kategoriType);
	}
	
	@Override
	public KategoriType read(KategoriType kategoriType)
	{
		return kategoriTypeService.get(kategoriType.getId());
	}
	
	@Override
	public boolean update(KategoriType kategoriType)
	{
		return kategoriTypeService.update(kategoriType);
	}
	
	@Override
	public boolean delete(KategoriType kategoriType)
	{
		return kategoriTypeService.delete(kategoriType);
	}
}
