package usn.obj2100.exam.database;

import java.sql.*;

/**
 *	Database management class. Used by the server client.
 * <p/>
 * <b>Pattern:</b>
 * <b>Role:</b>
 * <b>Responsibility:</b> To provide the entry point for the database,
 * 							  and to allow for a more granular way of managing the database.
 * @version 0.1
 * @created 2024-02-13
 *
 */
public class DBManager
{
	private static final String DB_URL = "jdbc:sqlite:database.sqlite";
	
	private Connection connect()
	{
		Connection connection = null;
		
		try
		{
			System.out.println(DB_URL);
			connection = DriverManager.getConnection(DB_URL);
		}
		catch (SQLException error)
		{
			System.err.println("Connection error: " + error.getMessage());
		}
		
		return connection;
	}
}
