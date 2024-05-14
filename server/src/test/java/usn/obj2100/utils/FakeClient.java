package usn.obj2100.utils;

import usn.obj2100.Constants;
import usn.obj2100.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * This is a utility class for unit testing, and functions as a "fake" client.
 * Fake in the sense that it simulates a client, since the actual client is a GUI program,
 * and is under development. Which isn't really suitable for unit testing.
 * <p/>
 *	References used for developing this class:
 *	<ul>
 *	    <li><b>Introduction to Java Programming and Data Structures, Comprehensive Version, 12th Edition</b>, chapter 44 - Testing Using JUnit.</li>
 *	</ul>
 *
 *	@since 0.2
 *	@created 2024-02-14
 */
public class FakeClient
{
	/**
	 * A static reference to the server object.
	 * <p/>
	 * Note that this is meant to be used in "@AfterAll" JUnit tests,
	 * to ensure the server is closed after a unit test.
	 */
	private static Server server;
	
	private Socket socket;
	private DataInputStream fromServer;
	private DataOutputStream toServer;
	
	/**
	 * Constructs a new FakeClient object and
	 * starts the server.
	 *
	 * @created 2024-02-14
	 */
	public FakeClient()
	{
		int port = Constants.PORT;
		server = new Server();
		server.start();
		
		while (true)
		{
			try
			{
				socket = new Socket(InetAddress.getLocalHost().getHostAddress(), port);
				fromServer = new DataInputStream(socket.getInputStream());
				toServer = new DataOutputStream(socket.getOutputStream());
				
				break;
			}
			catch (IOException error)
			{
				error.printStackTrace(System.err);
			}
		}
	}
	
	/**
	 * Sends a message to the server.
	 * <p/>
	 * This is a temporary implementation until the server API is more developed.
	 *
	 * @param message The message to send to the server.
	 */
	public void sendMessage(String message)
	{
		try
		{
			toServer.writeUTF(message);
			toServer.flush();
		}
		catch (IOException error)
		{
			error.printStackTrace(System.err);
		}
	}
	
	/**
	 * Receives a message from the server.
	 * <p/>
	 * This is a temporary implementation until the server API is more developed.
	 *
	 * @return The message received from the server.
	 */
	public String receiveMessage()
	{
		try
		{
			return fromServer.readUTF();
		}
		catch (IOException error)
		{
			error.printStackTrace(System.err);
			return null;
		}
	}
	
	/**
	 * Checks if the client is connected to the server.
	 *
	 * @return True if the client is connected to the server, false otherwise.
	 */
	public boolean isConnected()
	{
		return !socket.isClosed();
	}
	
	/**
	 * Disconnects the client from the server, and
	 * stops the server.
	 *
	 * @created 2024-02-14
	 */
	public void disconnect()
	{
		try
		{
			socket.close();
		}
		catch (IOException error)
		{
			error.printStackTrace(System.err);
		}
	}
	
	/**
	 * Stops the server.
	 *
	 * @created 2024-02-14
	 */
	public static void stopServer()
	{
		try
		{
			server.stop();
		}
		catch (Exception error)
		{
			error.printStackTrace(System.err);
		}
	}
}
