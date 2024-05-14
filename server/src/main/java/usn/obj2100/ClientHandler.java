package usn.obj2100;

import usn.obj2100.controller.InventarController;
import usn.obj2100.model.Inventar;

import java.io.*;
import java.net.Socket;

public class ClientHandler
	extends Thread
{
	private final Socket socket;
	private final InventarController inventarController;
	
	public ClientHandler(Socket socket)
	{
		this.socket = socket;
		this.inventarController = new InventarController();
	}
	
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
							/* Since this works by ID, it is directly handling the object instead of casting to an object. */
							System.out.println("Read: " + object);
							inventarController.getById(Integer.parseInt(String.valueOf(object)));
							objectOutputStream.writeObject(object);
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
