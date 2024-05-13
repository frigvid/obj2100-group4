package usn.obj2100;

import java.io.IOException;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("Server started!");
		
		DatabaseConnectionManager dcm = DatabaseConnectionManager.getInstance();
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
