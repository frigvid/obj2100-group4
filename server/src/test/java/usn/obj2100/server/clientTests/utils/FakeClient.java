package usn.obj2100.server.clientTests.utils;

import usn.obj2100.shared.Command;
import usn.obj2100.shared.Constants;
import usn.obj2100.server.Server;
import usn.obj2100.shared.Type;
import usn.obj2100.shared.model.Search;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

// TODO: Make sure tests delete the <root>/server/database.sqlite file for TESTS.
//			JetBrains, in their infinite wisdom, decided to make tests relative to the
//			module by default. Which means a multi-module project like this, where you
//			cannot EXPLICITLY tell it that "this" and "this" test are ONLY relevant to
//			module, you cannot properly test cross-module functionality without some
//			. . . major headaches, let's say.
/**
 * This is a utility class for unit testing, and functions as a "fake" client.
 * Fake in the sense that it simulates a client, since the actual client is a GUI program,
 * and is under development. Which isn't really suitable for unit testing.
 * <p/>
 *	References used for developing this class:
 *	<ul>
 *	    <li><b>Introduction to Java Programming and Data Structures, Comprehensive Version, 12th Edition</b>, chapter 44 - Testing Using JUnit.</li>
 *	    <li><a href="https://junit.org/junit5/docs/current/user-guide">JUnit 5 User Guide</a></li>
 *	</ul>
 *
 *	@since 0.2
 *	@created 2024-05-14
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
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	
	/**
	 * Constructs a new FakeClient object and
	 * starts the server.
	 *
	 * @created 2024-05-14
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
				System.out.println("Prøver å koble på " + InetAddress.getLocalHost().getHostAddress() + ":" + port);
				socket = new Socket(InetAddress.getLocalHost().getHostAddress(), port);
				objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				objectInputStream = new ObjectInputStream(socket.getInputStream());
				
				break;
			}
			catch (IOException error)
			{
				error.printStackTrace(System.err);
			}
		}
	}
	
	/**
	 * Makes a request to the server.
	 *
	 * @param command The enum C(R)UD command.
	 * @param object The object being sent.
	 */
	public Object request(Command command, Object object)
	{
		try
		{
			objectOutputStream.writeObject(command);
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
			
			return objectInputStream.readObject();
		}
		catch (IOException error)
		{
			error.printStackTrace(System.err);
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
		
		return null;
	}
	
	/**
	 * Overloaded metode ment for å sende søk til serveren.
	 *
	 * @param query Search objektet som sendes til serveren.
	 * @return Returnerer et søkerespons objekt fra serveren.
	 */
	public Object request(Search query)
	{
		try
		{
			objectOutputStream.writeObject(Command.SEARCH);
			objectOutputStream.writeObject(query);
			objectOutputStream.flush();
			
			return objectInputStream.readObject();
		}
		catch (IOException error)
		{
			error.printStackTrace(System.err);
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
		
		return null;
	}
	
	/**
	 * Overloaded metode ment for å hente alle objekter av en gitt type.
	 *
	 * @param type Typen objekt som skal hentes.
	 * @return Returnerer en liste med objekter av en gitt type.
	 */
	public Object request(Type type)
	{
		try
		{
			objectOutputStream.writeObject(Command.READALL);
			objectOutputStream.writeObject(type);
			objectOutputStream.flush();
			
			return objectInputStream.readObject();
		}
		catch (IOException error)
		{
			error.printStackTrace(System.err);
		}
		catch (ClassNotFoundException e)
		{
			throw new RuntimeException(e);
		}
		
		return null;
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
	 * @created 2024-05-14
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
	 * @created 2024-05-14
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
