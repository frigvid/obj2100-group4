package usn.obj2100;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
	private static DatabaseConnectionManager instance;
	
	private DatabaseConnectionManager() {}
	
	/**
	 * Returns the instance of the database connection manager.
	 *
	 * @since 0.1
	 * @created 2024-02-13
	 * @return The instance of the database connection manager.
	 */
	public static DatabaseConnectionManager getInstance()
	{
		if (instance == null)
		{
			instance = new DatabaseConnectionManager();
		}
		
		return instance;
	}
	
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
				initializeDatabase(connection);
				connection.close();
			}
			catch (Exception error)
			{
				error.printStackTrace(System.err);
			}
		}
	}
	
	private void initializeDatabase(Connection connection)
	{
		try
		{
			String[] createTableStatements =
			{
				"CREATE TABLE IF NOT EXISTS plassering (id INTEGER PRIMARY KEY, bygg TEXT NOT NULL, floy INTEGER NOT NULL, etasje INTEGER, rom INTEGER)",
				"CREATE TABLE IF NOT EXISTS inventar (sku INTEGER PRIMARY KEY, beskrivelse TEXT, innkjopsdato DATETIME, innkjopspris REAL, antall INTEGER, forventetLevetid SMALLINT, kategori INTEGER, plassering INTEGER, kassert INTEGER)",
				"CREATE TABLE IF NOT EXISTS kassert (id INTEGER PRIMARY KEY, dato DATE, tid TIMESTAMP, begrunnelse INTEGER)",
				"CREATE TABLE IF NOT EXISTS kassertType (id INTEGER PRIMARY KEY, begrunnelse TEXT)",
				"CREATE TABLE IF NOT EXISTS kategori (id INTEGER PRIMARY KEY, type INTEGER NOT NULL, kategori TEXT)",
				"CREATE TABLE IF NOT EXISTS kategoriType (id INTEGER PRIMARY KEY, type TEXT)"
			};
			
			for (String statement: createTableStatements)
			{
				try (Statement stmt = connection.createStatement())
				{
					stmt.execute(statement);
				}
			}
		}
		catch (SQLException error)
		{
			error.printStackTrace(System.err);
		}
	}
}
