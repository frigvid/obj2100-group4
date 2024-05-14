package usn.obj2100.controller;

import usn.obj2100.model.Inventar;
import usn.obj2100.service.InventarService;

import java.util.List;

/**
 * Controller for the Inventar model.
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
	 *
	 * @param inventar The inventar object to create.
	 * @return True if the inventar object was created, false otherwise.
	 */
	@Override
	public boolean create(Inventar inventar)
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
}
