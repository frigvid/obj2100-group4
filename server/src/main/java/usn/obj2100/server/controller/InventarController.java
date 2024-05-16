package usn.obj2100.server.controller;

import usn.obj2100.server.service.InventarService;
import usn.obj2100.shared.model.Inventar;

import java.util.List;

/**
 * Controller for the Inventar model.
 * <p/>
 * Essentially just a wrapper for the service right now, but having a controller
 * allows it to be expanded in the future if needed. E.g. by checking input
 * beforehand, etc.
 *
 * @created 2024-05-13
 * @version 0.1
 * @see InventarService for usage of this controller.
 */
public class InventarController
	implements IController<Inventar>
{
	private InventarService inventarService = new InventarService();
	
	/**
	 * Get all inventar objects.
	 *
	 * @return A list of all inventar objects.
	 */
	@Override
	public List<Inventar> getAll()
	{
		return inventarService.getAll();
	}
	
	/**
	 * Get an inventar object by its ID.
	 *
	 * @param id The ID of the inventar object.
	 * @return The inventar object.
	 */
	@Override
	public Inventar getById(int id)
	{
		return inventarService.get(id);
	}
	
	/**
	 * Create a new inventar object.
	 * <p/>
	 * Note that if you create an object with an ID, and it is not
	 * available, the database sets an available one for it. So if you
	 * need to get the newly created object's ID, you just save the
	 * response to an Object of Plassering or a generic Object, and get
	 * the ID from there.
	 *
	 * @param inventar The inventar object to create.
	 * @return The created inventar object.
	 */
	@Override
	public Inventar create(Inventar inventar)
	{
		return inventarService.create(inventar);
	}
	
	/**
	 * Get (read) an inventar object.
	 *
	 * @param inventar The inventar object to read.
	 * @return The inventar object.
	 */
	@Override
	public Inventar read(Inventar inventar)
	{
		return inventarService.get(inventar.getSKU());
	}
	
	/**
	 * Update an inventar object.
	 *
	 * @param inventar The inventar object to update.
	 * @return True if the inventar object was updated, false otherwise.
	 */
	@Override
	public boolean update(Inventar inventar)
	{
		return inventarService.update(inventar);
	}
	
	/**
	 * Delete an inventar object.
	 *
	 * @param inventar The inventar object to delete.
	 * @return True if the inventar object was deleted, false otherwise.
	 */
	@Override
	public boolean delete(Inventar inventar)
	{
		return inventarService.delete(inventar);
	}

	/**
	 * Delete an inventar object by SKU.
	 *
	 * @param sku The inventar object to delete.
	 * @return True if the inventar object was deleted, false otherwise.
	 */
	public boolean deleteBySku(int inventar)
	{
		return inventarService.deleteBySku(inventar);
	}
}
