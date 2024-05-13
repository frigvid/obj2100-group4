package usn.obj2100;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class for managing the connection to the database.
 * <p/>
 * <b>Pattern:</b> Singleton.
 * <br/>
 * <b>Role:</b> Singleton.
 * <br/>
 * <b>Responsibility:</b> To provide a single point of access to the database connection.
 * <p/>
 * Example usage:
 * {@snippet id="DatabaseConnectionManagerExample" lang="java" group="DatabaseConnectionManager":
 * 	DatabaseConnectionManager dcm = new DatabaseConnectionManager();
 * 	dcm.getConnection();
 * }
 *
 * @since 0.1
 * @created 2024-02-13
 */
public class DatabaseConnectionManager
{
	/**
	 * Returns a connection to the database.
	 * <p/>
	 * If the database file does not exist, it will be created.
	 *
	 * @since 0.1
	 * @created 2024-02-13
	 * @return A connection to the database.
	 */
	public Connection getConnection()
	{
		try
		{
			createIfNotExists();
			return DriverManager.getConnection(Constants.DB_URL);
		}
		catch (SQLException error)
		{
			error.printStackTrace(System.err);
			return null;
		}
	}
	
	/**
	 * Creates the database file if it does not exist.
	 *
	 * @since 0.1
	 * @created 2024-02-13
	 */
	private void createIfNotExists()
	{
		File db = new File(Constants.DB_PATH);
		
		if (!db.exists())
		{
			try
			{
				System.out.println("Is it necessary to create a new database file? " + db.createNewFile());
				Connection connection = DriverManager.getConnection(Constants.DB_URL);
				connection.close();
			}
			catch (Exception error)
			{
				error.printStackTrace(System.err);
			}
		}
	}
}
