package usn.obj2100.server;

import usn.obj2100.server.controller.*;
import usn.obj2100.shared.Command;
import usn.obj2100.shared.model.*;

import java.io.*;
import java.net.Socket;

/**
 * This class is a handler for the clients.
 * <p/>
 * It reads a command and an object from the client, and then processes the command on the object.
 * The result is then sent back to the client.
 *
 * @version 0.1
 * @created 2024-05-13
 */
public class ClientHandler
	extends Thread
{
	private final Socket socket;
	private final InventarController inventarController;
	private final PlasseringController plasseringController;
	private final KategoriController kategoriController;
	private final KategoriTypeController kategoriTypeController;
	private final KassertController kassertController;
	private final KassertTypeController kassertTypeController;
	
	/**
	 * Create a new client handler.
	 *
	 * @param socket The socket to the client.
	 */
	public ClientHandler(Socket socket)
	{
		System.out.println("Client connected.");
		
		this.socket = socket;
		this.inventarController = new InventarController();
		this.plasseringController = new PlasseringController();
		this.kategoriController = new KategoriController();
		this.kategoriTypeController = new KategoriTypeController();
		this.kassertController = new KassertController();
		this.kassertTypeController = new KassertTypeController();
	}
	
	/**
	 * Handle input/output from/to the client.
	 */
	@Override
	public void run()
	{
		try
		(
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
		)
		{
			Command command;
			Object object;
			
			/* TODO: This is not really the prettiest of solutions. For a solution involving many more object types
			 *			than this, it'll likely be rather unmaintainable. A better solution would be to extract this into
			 *			a generic method or class, that handles objects in relation to their type or class.
			 *			However, for the sake of ensuring we have something that works, this will suffice now.
			 */
			while ((command = (Command) objectInputStream.readObject()) != null)
			{
				object = objectInputStream.readObject();
				
				if (object instanceof Inventar inventar)
				{
					boolean state;
					Inventar retrievedInventar;
					
					switch (command)
					{
						case CREATE:
							retrievedInventar = inventarController.create(inventar);
							objectOutputStream.writeObject(retrievedInventar);
							break;
						case READ:
							retrievedInventar = inventarController.read(inventar);
							objectOutputStream.writeObject(retrievedInventar);
							break;
						case UPDATE:
							state = inventarController.update(inventar);
							objectOutputStream.writeObject(state);
							break;
						case DELETE:
							state = inventarController.delete(inventar);
							objectOutputStream.writeObject(state);
							break;
						default:
							objectOutputStream.writeObject("Feil aksjon!");
							break;
					}
				}
				else if (object instanceof Plassering plassering)
				{
					boolean state;
					Plassering retrievedPlassering;
					
					switch (command)
					{
						case CREATE:
							retrievedPlassering = plasseringController.create(plassering);
							objectOutputStream.writeObject(retrievedPlassering);
							break;
						case READ:
							retrievedPlassering = plasseringController.read(plassering);
							objectOutputStream.writeObject(retrievedPlassering);
							break;
						case UPDATE:
							state = plasseringController.update(plassering);
							objectOutputStream.writeObject(state);
							break;
						case DELETE:
							state = plasseringController.delete(plassering);
							objectOutputStream.writeObject(state);
							break;
						default:
							objectOutputStream.writeObject("Feil aksjon!");
							break;
					}
				}
				else if (object instanceof Kategori kategori)
				{
					boolean state;
					Kategori retrievedKategori;
					
					switch (command)
					{
						case CREATE:
							retrievedKategori = kategoriController.create(kategori);
							objectOutputStream.writeObject(retrievedKategori);
							break;
						case READ:
							retrievedKategori = kategoriController.read(kategori);
							objectOutputStream.writeObject(retrievedKategori);
							break;
						case UPDATE:
							state = kategoriController.update(kategori);
							objectOutputStream.writeObject(state);
							break;
						case DELETE:
							state = kategoriController.delete(kategori);
							objectOutputStream.writeObject(state);
							break;
						default:
							objectOutputStream.writeObject("Feil aksjon!");
							break;
					}
				}
				else if (object instanceof KategoriType kategoriType)
				{
					boolean state;
					KategoriType retrievedKategoriType;
					
					switch (command)
					{
						case CREATE:
							retrievedKategoriType = kategoriTypeController.create(kategoriType);
							objectOutputStream.writeObject(retrievedKategoriType);
							break;
						case READ:
							retrievedKategoriType = kategoriTypeController.read(kategoriType);
							objectOutputStream.writeObject(retrievedKategoriType);
							break;
						case UPDATE:
							state = kategoriTypeController.update(kategoriType);
							objectOutputStream.writeObject(state);
							break;
						case DELETE:
							state = kategoriTypeController.delete(kategoriType);
							objectOutputStream.writeObject(state);
							break;
						default:
							objectOutputStream.writeObject("Feil aksjon!");
							break;
					}
				}
				else if (object instanceof Kassert kassert)
				{
					boolean state;
					Kassert retrievedKassert;
					
					switch (command)
					{
						case CREATE:
							retrievedKassert = kassertController.create(kassert);
							objectOutputStream.writeObject(retrievedKassert);
							break;
						case READ:
							retrievedKassert = kassertController.read(kassert);
							objectOutputStream.writeObject(retrievedKassert);
							break;
						case UPDATE:
							state = kassertController.update(kassert);
							objectOutputStream.writeObject(state);
							break;
						case DELETE:
							state = kassertController.delete(kassert);
							objectOutputStream.writeObject(state);
							break;
						default:
							objectOutputStream.writeObject("Feil aksjon!");
							break;
					}
				}
				else if (object instanceof KassertType kassertType)
				{
					boolean state;
					KassertType retrievedKassertType;
					
					switch (command)
					{
						case CREATE:
							retrievedKassertType = kassertTypeController.create(kassertType);
							objectOutputStream.writeObject(retrievedKassertType);
							break;
						case READ:
							retrievedKassertType = kassertTypeController.read(kassertType);
							objectOutputStream.writeObject(retrievedKassertType);
							break;
						case UPDATE:
							state = kassertTypeController.update(kassertType);
							objectOutputStream.writeObject(state);
							break;
						case DELETE:
							state = kassertTypeController.delete(kassertType);
							objectOutputStream.writeObject(state);
							break;
						default:
							objectOutputStream.writeObject("Feil aksjon!");
							break;
					}
				}
			}
		}
		catch (IOException | ClassNotFoundException error)
		{
			System.out.println("Client disconnected");
		}
	}
}
