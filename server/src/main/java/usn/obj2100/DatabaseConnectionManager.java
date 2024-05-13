package usn.obj2100;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager
{
	private static final String DB_URL = "jdbc:sqlite:path/to/your/database.db";
	private Connection connection;
	
	public DatabaseConnectionManager()
	{
		try
		{
			connection = DriverManager.getConnection(DB_URL);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
}
