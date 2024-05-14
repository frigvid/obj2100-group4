package usn.obj2100;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

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
			DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
			DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream())
		)
		{
			while (true)
			{
				try
				{
					/* Read from client. */
					String message = inputFromClient.readUTF();
					System.out.println("Received from client: " + message);
					
					/* Write to client. */
					outputToClient.writeUTF("Echo: " + message);
				}
				catch (EOFException error)
				{
					System.out.println("Client disconnected.");
					break;
				}
			}
		}
		catch (IOException error)
		{
			error.printStackTrace(System.err);
		}
	}
}
