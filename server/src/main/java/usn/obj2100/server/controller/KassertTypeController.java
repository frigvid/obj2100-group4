package usn.obj2100.server.controller;

import usn.obj2100.server.service.KassertTypeService;
import usn.obj2100.shared.model.KassertType;

import java.util.List;

/**
 * This class is responsible for handling the KassertType objects in the database.
 * <p/>
 * Essentially just a wrapper for the service right now, but having a controller
 * allows it to be expanded in the future if needed. E.g. by checking input
 * beforehand, etc.
 *
 * @created 2024-05-13
 */
public class KassertTypeController
	implements IController<KassertType>
{
	public KassertTypeService kassertTypeService = new KassertTypeService();
	
	/**
	 * Get all KassertType objects from the database.
	 *
	 * @return A list of KassertType objects.
	 */
	@Override
	public List<KassertType> getAll()
	{
		return kassertTypeService.getAll();
	}
	
	/**
	 * Get a KassertType object from the database.
	 *
	 * @param id The ID of the KassertType object.
	 * @return The KassertType object.
	 */
	@Override
	public KassertType getById(int id)
	{
		return kassertTypeService.get(id);
	}
	
	/**
	 * Create a KassertType object in the database.
	 * <p/>
	 * Note that if you create an object with an ID, and it is not
	 * available, the database sets an available one for it. So if you
	 * need to get the newly created object's ID, you just save the
	 * response to an Object of Plassering or a generic Object, and get
	 * the ID from there.
	 *
	 * @param kassertType The KassertType object to create.
	 * @return The created KassertType object.
	 */
	@Override
	public KassertType create(KassertType kassertType)
	{
		return kassertTypeService.create(kassertType);
	}
	
	/**
	 * Read a KassertType object from the database.
	 *
	 * @param kassertType The KassertType object to read.
	 * @return The KassertType object.
	 */
	@Override
	public KassertType read(KassertType kassertType)
	{
		return kassertTypeService.get(kassertType.getId());
	}
	
	/**
	 * Update a KassertType object in the database.
	 *
	 * @param kassertType The KassertType object to update.
	 * @return True if the update was successful, false otherwise.
	 */
	@Override
	public boolean update(KassertType kassertType)
	{
		return kassertTypeService.update(kassertType);
	}
	
	/**
	 * Delete a KassertType object from the database.
	 *
	 * @param kassertType The KassertType object to delete.
	 * @return True if the deletion was successful, false otherwise.
	 */
	@Override
	public boolean delete(KassertType kassertType)
	{
		return kassertTypeService.delete(kassertType);
	}
}
