package usn.obj2100.server.controller;

import usn.obj2100.server.service.PlasseringService;
import usn.obj2100.shared.model.Plassering;

import java.util.List;

/**
 * This class is responsible for handling the Plassering objects in the database.
 * <p/>
 * Essentially just a wrapper for the service right now, but having a controller
 * allows it to be expanded in the future if needed. E.g. by checking input
 * beforehand, etc.
 *
 * @created 2024-05-13
 */
public class PlasseringController
	implements IController<Plassering>
{
	private PlasseringService plasseringService = new PlasseringService();
	
	/**
	 * Get all Plassering objects from the database.
	 *
	 * @return A list of Plassering objects.
	 */
	@Override
	public List<Plassering> getAll()
	{
		return plasseringService.getAll();
	}
	
	/**
	 * Get a Plassering object from the database.
	 *
	 * @param id The ID of the Plassering object.
	 * @return The Plassering object.
	 */
	@Override
	public Plassering getById(int id)
	{
		return plasseringService.get(id);
	}
	
	/**
	 * Create a Plassering object in the database.
	 * <p/>
	 * Note that if you create an object with an ID, and it is not
	 * available, the database sets an available one for it. So if you
	 * need to get the newly created object's ID, you just save the
	 * response to an Object of Plassering or a generic Object, and get
	 * the ID from there.
	 *
	 * @param plassering The Plassering object to create.
	 * @return The created Plassering object.
	 */
	@Override
	public Plassering create(Plassering plassering)
	{
		return plasseringService.create(plassering);
	}
	
	/**
	 * Read a Plassering object from the database.
	 *
	 * @param plassering The Plassering object to read.
	 * @return The Plassering object.
	 */
	@Override
	public Plassering read(Plassering plassering)
	{
		return plasseringService.get(plassering.getId());
	}
	
	/**
	 * Update a Plassering object in the database.
	 *
	 * @param plassering The Plassering object to update.
	 * @return True if the update was successful, false otherwise.
	 */
	@Override
	public boolean update(Plassering plassering)
	{
		return plasseringService.update(plassering);
	}
	
	/**
	 * Delete a Plassering object from the database.
	 *
	 * @param plassering The Plassering object to delete.
	 * @return True if the deletion was successful, false otherwise.
	 */
	@Override
	public boolean delete(Plassering plassering)
	{
		return plasseringService.delete(plassering);
	}
}
