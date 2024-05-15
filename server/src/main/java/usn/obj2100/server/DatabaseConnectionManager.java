package usn.obj2100.server;

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
 * @created 2024-05-13
 */
public class DatabaseConnectionManager
{
	/* BE WARNED
	 *
	 * JetBrains have decided that all tests are ran in a module scope by default,
	 * instead of being explicitly configured. This will cause the database to be
	 * created in <root>/server/database.sqlite when run as a test, and created in
	 * <root>/database.sqlite when actually built.
	 */
	private final String DB_PATH = "database.sqlite";
	
	private final String DB_URL = "jdbc:sqlite:" + DB_PATH;
	private static DatabaseConnectionManager instance;
	
	private DatabaseConnectionManager() {}
	
	/**
	 * Returns the instance of the database connection manager.
	 *
	 * @since 0.1
	 * @created 2024-05-13
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
	 * @created 2024-05-13
	 * @return A connection to the database.
	 */
	public Connection getConnection()
	{
		try
		{
			createIfNotExists();
			return DriverManager.getConnection(DB_URL);
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
	 * @created 2024-05-13
	 */
	private void createIfNotExists()
	{
		File db = new File(DB_PATH);
		
		if (!db.exists())
		{
			try
			{
				System.out.println("Is it necessary to create a new database file? " + db.createNewFile());
				System.out.println("Created a new database file at: " + DB_PATH);
				Connection connection = DriverManager.getConnection(DB_URL);
				initializeDatabase(connection);
				connection.close();
			}
			catch (Exception error)
			{
				error.printStackTrace(System.err);
			}
		}
	}
	
	// TODO: Add example data to the database when it's first created.
	//			See DatabaseExampleDataTest for this.
	/**
	 * Initializes the database by creating the necessary tables.
	 * <p/>
	 * This method is called when the database file is created.
	 *
	 * @param connection The connection to the database.
	 */
	private void initializeDatabase(Connection connection)
	{
		try
		{
			System.out.println("Initializing the database...");
			
			String[] createTableStatements =
				{
					"CREATE TABLE IF NOT EXISTS plassering (id INTEGER PRIMARY KEY, bygg TEXT NOT NULL, floy INTEGER NOT NULL, etasje INTEGER, rom INTEGER)",
					"CREATE TABLE IF NOT EXISTS inventar (sku INTEGER PRIMARY KEY, beskrivelse TEXT, innkjopsdato DATETIME, innkjopspris REAL, antall INTEGER, forventetLevetid SMALLINT, kategori INTEGER, plassering INTEGER, kassert INTEGER, FOREIGN KEY(kategori) REFERENCES kategori(id), FOREIGN KEY(plassering) REFERENCES plassering(id), FOREIGN KEY(kassert) REFERENCES kassert(id))",
					"CREATE TABLE IF NOT EXISTS kassert (id INTEGER PRIMARY KEY, dato DATE, tid TIMESTAMP, begrunnelse INTEGER, FOREIGN KEY(begrunnelse) REFERENCES kassertType(id))",
					"CREATE TABLE IF NOT EXISTS kassertType (id INTEGER PRIMARY KEY, begrunnelse TEXT)",
					"CREATE TABLE IF NOT EXISTS kategori (id INTEGER PRIMARY KEY, type INTEGER NOT NULL, kategori TEXT, FOREIGN KEY(type) REFERENCES kategoriType(id))",
					"CREATE TABLE IF NOT EXISTS kategoriType (id INTEGER PRIMARY KEY, type TEXT)",
					"CREATE INDEX IF NOT EXISTS idx_kategori ON kategori(id, type)",
					"CREATE INDEX IF NOT EXISTS idx_kategori_name ON kategori(kategori)"
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
