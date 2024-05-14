package usn.obj2100.utils;

import usn.obj2100.Constants;
import usn.obj2100.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * A fake client that connects to a server and sends and receives messages.
 * <p/>
 * This is used by the unit tests to simulate a client connecting to the server.
 */
public class FakeClient
{
	private Socket socket;
	private DataInputStream fromServer;
	private DataOutputStream toServer;
	
	public FakeClient()
	{
		int port = Constants.PORT;
		Server server = new Server();
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
	
	public boolean isConnected()
	{
		return !socket.isClosed();
	}
	
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
}
