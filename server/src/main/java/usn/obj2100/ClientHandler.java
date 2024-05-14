package usn.obj2100;

import usn.obj2100.controller.InventarController;
import usn.obj2100.model.Inventar;

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
	
	/**
	 * Create a new client handler.
	 *
	 * @param socket The socket to the client.
	 */
	public ClientHandler(Socket socket)
	{
		this.socket = socket;
		this.inventarController = new InventarController();
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
			
			while ((command = (Command) objectInputStream.readObject()) != null)
			{
				object = objectInputStream.readObject();
				
				if (object instanceof Inventar inventar)
				{
					boolean state;
					
					switch (command)
					{
						case CREATE:
							state = inventarController.create(inventar);
							objectOutputStream.writeObject(state);
							break;
						case READ:
							Inventar retrievedInventar = inventarController.read(inventar);
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
							objectOutputStream.writeObject("Invalid command");
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
