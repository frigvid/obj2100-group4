package usn.obj2100;

import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

// TODO: Implement encryption and decryption of data. It's business sensitive data.
public class Server
{
	private ServerSocket serverSocket;
	private Thread serverThread;
	private int dynamicPort;
	
	public Server()
	{
		this.dynamicPort = Constants.PORT;
	}
	
	public void start()
	{
		serverThread = new Thread(() ->
		{
			while (true)
			{
				try
				{
					serverSocket = new ServerSocket(dynamicPort, 50, InetAddress.getLocalHost());
					System.out.println("Serveren startet " + new Date() + " på port " + serverSocket.getLocalPort());
					
					while (true)
					{
						/* Await client connections. */
						System.out.println("Venter på klienter...");
						Socket socket = serverSocket.accept();
						
						/* Start a new thread for each connection. */
						Thread thread = new ClientHandler(socket);
						thread.start();
					}
				}
				catch (BindException bindException)
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
		});
		
		serverThread.start();
	}
	
	public void stop()
	{
		if (serverSocket != null && !serverSocket.isClosed())
		{
			try
			{
				serverSocket.close();
			}
			catch (IOException error)
			{
				error.printStackTrace(System.err);
			}
		}
	}
}
