package usn.obj2100;

import usn.obj2100.server.Server;

import java.io.IOException;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("Server started!");
		
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
