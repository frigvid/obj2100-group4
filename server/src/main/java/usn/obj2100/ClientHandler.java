package usn.obj2100;

import usn.obj2100.controller.InventarController;
import usn.obj2100.controller.PlasseringController;
import usn.obj2100.model.Inventar;
import usn.obj2100.model.Plassering;

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
	
	/**
	 * Create a new client handler.
	 *
	 * @param socket The socket to the client.
	 */
	public ClientHandler(Socket socket)
	{
		this.socket = socket;
		this.inventarController = new InventarController();
		this.plasseringController = new PlasseringController();
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
			}
		}
		catch (IOException | ClassNotFoundException error)
		{
			System.out.println("Client disconnected");
		}
	}
}
