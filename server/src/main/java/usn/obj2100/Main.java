package usn.obj2100;

import java.sql.Connection;

public class Main
{
	public static void main(String[] args)
	{
		/* This is pretty temporary. But this ensures the database will be created if missing.
		 * It'll do the same wherever else something tries to get a connection, but at least this
		 * way it ensures it'll be there if the code for some reason assumes it'll already be in place.
		 */
		Connection dcm = DatabaseConnectionManager.getInstance().getConnection();
		
		Server server = new Server();
		server.start();
	}
}
