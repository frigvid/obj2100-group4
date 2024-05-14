package usn.obj2100;

import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

// TODO: Implement encryption and decryption of data. It's business sensitive data.

/**
 *	This class represents the server, and is responsible for handling client connections.
 *	<p/>
 *	References used for developing this class:
 *	<ul>
 *	    <li><b>Introduction to Java Programming and Data Structures, Comprehensive Version, 12th Edition</b>, chapter 33 - Networking.</li>
 *	</ul>
 *	<b>Pattern:</b> Singleton.
 *	<br/>
 *	<b>Role:</b> Server.
 *	<br/>
 *	<b>Responsibility:</b> A single point of entry for client connections.
 *	<p/>
 *	Example usage:
 *	{@snippet id="Server" lang="java" group="Server":
 *		Server server = new Server();
 *		server.start();
 *	}
 *
 *	@since 0.1
 *	@created 2024-02-14
 * @see ClientHandler for how client connections are handled.
 */
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
