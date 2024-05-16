package usn.obj2100.server.controller;

import usn.obj2100.server.service.KassertService;
import usn.obj2100.shared.model.Kassert;

import java.util.List;

/**
 * This class is responsible for handling the Kassert objects in the database.
 * <p/>
 * Essentially just a wrapper for the service right now, but having a controller
 * allows it to be expanded in the future if needed. E.g. by checking input
 * beforehand, etc.
 *
 * @created 2024-05-13
 */
public class KassertController
	implements IController<Kassert>
{
	private KassertService kassertService = new KassertService();
	
	/**
	 * Get all Kassert objects from the database.
	 *
	 * @return A list of Kassert objects.
	 */
	@Override
	public List<Kassert> getAll()
	{
		return kassertService.getAll();
	}
	
	/**
	 * Get a Kassert object from the database.
	 *
	 * @param id The ID of the Kassert object.
	 * @return The Kassert object.
	 */
	@Override
	public Kassert getById(int id)
	{
		return kassertService.get(id);
	}
	
	/**
	 * Create a Kassert object in the database.
	 * <p/>
	 * Note that if you create an object with an ID, and it is not
	 * available, the database sets an available one for it. So if you
	 * need to get the newly created object's ID, you just save the
	 * response to an Object of Plassering or a generic Object, and get
	 * the ID from there.
	 *
	 * @param kassert The Kassert object to create.
	 * @return The created Kassert object.
	 */
	@Override
	public Kassert create(Kassert kassert)
	{
		return kassertService.create(kassert);
	}
	
	/**
	 * Read a Kassert object from the database.
	 *
	 * @param kassert The Kassert object to read.
	 * @return The Kassert object.
	 */
	@Override
	public Kassert read(Kassert kassert)
	{
		return kassertService.get(kassert.getId());
	}
	
	/**
	 * Update a Kassert object in the database.
	 *
	 * @param kassert The Kassert object to update.
	 * @return True if the update was successful, false otherwise.
	 */
	@Override
	public boolean update(Kassert kassert)
	{
		return kassertService.update(kassert);
	}
	
	/**
	 * Delete a Kassert object from the database.
	 *
	 * @param kassert The Kassert object to delete.
	 * @return True if the deletion was successful, false otherwise.
	 */
	@Override
	public boolean delete(Kassert kassert)
	{
		return kassertService.delete(kassert);
	}
}
