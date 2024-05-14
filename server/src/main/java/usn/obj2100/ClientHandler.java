package usn.obj2100;

import usn.obj2100.model.Inventar;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class ClientHandler
	extends Thread
{
	private final Socket socket;
	
	public ClientHandler(Socket socket)
	{
		this.socket = socket;
	}
	
	@Override
	public void run()
	{
		try
		(
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())
		)
		{
			Command command;
			Object object;
			
			while ((command = (Command) objectInputStream.readObject()) != null)
			{
				object = objectInputStream.readObject();
				
				if (object instanceof Inventar)
				{
					Inventar inventar = (Inventar) object;
					
					switch (command)
					{
						case CREATE:
							System.out.println("Create: " + inventar);
							objectOutputStream.writeObject("Created: " + inventar);
							break;
						case READ:
							System.out.println("Read: " + inventar);
							objectOutputStream.writeObject("Read: " + inventar);
							break;
						case UPDATE:
							System.out.println("Update: " + inventar);
							objectOutputStream.writeObject("Updated: " + inventar);
							break;
						case DELETE:
							System.out.println("Delete: " + inventar);
							objectOutputStream.writeObject("Deleted: " + inventar);
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
			error.printStackTrace(System.err);
		}
	}
}
