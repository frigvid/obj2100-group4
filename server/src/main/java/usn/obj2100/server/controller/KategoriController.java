package usn.obj2100.server.controller;

import usn.obj2100.server.service.KategoriService;
import usn.obj2100.shared.model.Kategori;

import java.util.List;

/**
 * This class is responsible for handling the Kategori objects in the database.
 * <p/>
 * Essentially just a wrapper for the service right now, but having a controller
 * allows it to be expanded in the future if needed. E.g. by checking input
 * beforehand, etc.
 *
 * @created 2024-05-13
 */
public class KategoriController
	implements IController<Kategori>
{
	private KategoriService kategoriService = new KategoriService();
	
	/**
	 * Get all Kategori objects from the database.
	 *
	 * @return A list of Kategori objects.
	 */
	@Override
	public List<Kategori> getAll()
	{
		return kategoriService.getAll();
	}
	
	/**
	 * Get a Kategori object from the database.
	 *
	 * @param id The ID of the Kategori object.
	 * @return The Kategori object.
	 */
	@Override
	public Kategori getById(int id)
	{
		return kategoriService.get(id);
	}
	
	/**
	 * Create a Kategori object in the database.
	 * <p/>
	 * Note that if you create an object with an ID, and it is not
	 * available, the database sets an available one for it. So if you
	 * need to get the newly created object's ID, you just save the
	 * response to an Object of Plassering or a generic Object, and get
	 * the ID from there.
	 *
	 * @param kategori The Kategori object to create.
	 * @return The created Kategori object.
	 */
	@Override
	public Kategori create(Kategori kategori)
	{
		return kategoriService.create(kategori);
	}
	
	/**
	 * Read a Kategori object from the database.
	 *
	 * @param kategori The Kategori object to read.
	 * @return The Kategori object.
	 */
	@Override
	public Kategori read(Kategori kategori)
	{
		return kategoriService.get(kategori.getId());
	}
	
	/**
	 * Update a Kategori object in the database.
	 *
	 * @param kategori The Kategori object to update.
	 * @return True if the update was successful, false otherwise.
	 */
	@Override
	public boolean update(Kategori kategori)
	{
		return kategoriService.update(kategori);
	}
	
	/**
	 * Delete a Kategori object from the database.
	 *
	 * @param kategori The Kategori object to delete.
	 * @return True if the delete was successful, false otherwise.
	 */
	@Override
	public boolean delete(Kategori kategori)
	{
		return kategoriService.delete(kategori);
	}
}
