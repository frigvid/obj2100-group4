package usn.obj2100.server.controller;

import usn.obj2100.server.service.KategoriService;
import usn.obj2100.shared.model.Kategori;

import java.util.List;

public class KategoriController
	implements IController<Kategori>
{
	private KategoriService kategoriService = new KategoriService();
	
	@Override
	public List<Kategori> getAll()
	{
		return kategoriService.getAll();
	}
	
	@Override
	public Kategori getById(int id)
	{
		return kategoriService.get(id);
	}
	
	@Override
	public Kategori create(Kategori kategori)
	{
		return kategoriService.create(kategori);
	}
	
	@Override
	public Kategori read(Kategori kategori)
	{
		return kategoriService.get(kategori.getId());
	}
	
	@Override
	public boolean update(Kategori kategori)
	{
		return kategoriService.update(kategori);
	}
	
	@Override
	public boolean delete(Kategori kategori)
	{
		return kategoriService.delete(kategori);
	}
}
