package usn.obj2100.server.controller;

import usn.obj2100.shared.model.KategoriType;
import usn.obj2100.server.service.KategoriTypeService;

import java.util.List;

/**
 * This class is responsible for handling the KategoriType objects in the database.
 * <p/>
 * Essentially just a wrapper for the service right now, but having a controller
 * allows it to be expanded in the future if needed. E.g. by checking input
 * beforehand, etc.
 *
 * @created 2024-05-13
 */
public class KategoriTypeController
	implements IController<KategoriType>
{
	private KategoriTypeService kategoriTypeService = new KategoriTypeService();
	
	/**
	 * Get all KategoriType objects from the database.
	 *
	 * @return A list of KategoriType objects.
	 */
	@Override
	public List<KategoriType> getAll()
	{
		return kategoriTypeService.getAll();
	}
	
	/**
	 * Get a KategoriType object from the database.
	 *
	 * @param id The ID of the KategoriType object.
	 * @return The KategoriType object.
	 */
	@Override
	public KategoriType getById(int id)
	{
		return kategoriTypeService.get(id);
	}
	
	/**
	 * Create a KategoriType object in the database.
	 * <p/>
	 * Note that if you create an object with an ID, and it is not
	 * available, the database sets an available one for it. So if you
	 * need to get the newly created object's ID, you just save the
	 * response to an Object of Plassering or a generic Object, and get
	 * the ID from there.
	 *
	 * @param kategoriType The KategoriType object to create.
	 * @return The created KategoriType object.
	 */
	@Override
	public KategoriType create(KategoriType kategoriType)
	{
		return kategoriTypeService.create(kategoriType);
	}
	
	/**
	 * Read a KategoriType object from the database.
	 *
	 * @param kategoriType The KategoriType object to read.
	 * @return The KategoriType object.
	 */
	@Override
	public KategoriType read(KategoriType kategoriType)
	{
		return kategoriTypeService.get(kategoriType.getId());
	}
	
	/**
	 * Update a KategoriType object in the database.
	 *
	 * @param kategoriType The KategoriType object to update.
	 * @return True if the update was successful, false otherwise.
	 */
	@Override
	public boolean update(KategoriType kategoriType)
	{
		return kategoriTypeService.update(kategoriType);
	}
	
	/**
	 * Delete a KategoriType object from the database.
	 *
	 * @param kategoriType The KategoriType object to delete.
	 * @return True if the deletion was successful, false otherwise.
	 */
	@Override
	public boolean delete(KategoriType kategoriType)
	{
		return kategoriTypeService.delete(kategoriType);
	}
}
