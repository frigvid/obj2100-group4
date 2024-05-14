package usn.obj2100;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server
{
	public static void main(String[] args)
	{
		DatabaseConnectionManager dcm = DatabaseConnectionManager.getInstance();
		dcm.getConnection();
		
		System.out.println(System.getProperty("user.dir"));
		
		/**
		 * The server listens for incoming connections from clients.
		 *
		 * @see Introduction to Java Programming and Data Structures, Comprehensive Version, 12th Edition, chapter 33.
		 */
		new Thread(() ->
		{
			int dynamicPort = Constants.PORT;
			
			while (true)
			{
				try (ServerSocket serverSocket = new ServerSocket(dynamicPort))
				{
					System.out.println("Serveren startet " + new Date() + " på port " + serverSocket.getLocalPort());
					
					while (true)
					{
						/* Await client connections. */
						Socket socket = serverSocket.accept();
						System.out.println("Waiting for client connection...");
						
						/* Start a new thread for each connection. */
						Thread thread = new ClientHandler(socket);
						thread.start();
					}
				}
				catch (java.net.BindException bindException)
				{
					System.out.println("Port " + dynamicPort + " er allerede i bruk. Prøver en annen port...");
					/* Increment port value and try, try and try again. */
					dynamicPort++;
				}
				catch (IOException error)
				{
					error.printStackTrace(System.err);
				}
			}
		}).start();
	}
}
