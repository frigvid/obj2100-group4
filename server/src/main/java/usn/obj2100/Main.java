package usn.obj2100;

import usn.obj2100.server.Server;

import java.io.IOException;
import java.sql.Connection;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("Server started!");
		
		DatabaseConnectionManager dcm = new DatabaseConnectionManager();
		dcm.getConnection();
		
		Thread serverThread = new Thread(() ->
		{
			try
			{
				Server.main(null);
			}
			catch (IOException error)
			{
				error.printStackTrace(System.err);
			}
		});
		
		serverThread.start();
	}
}
