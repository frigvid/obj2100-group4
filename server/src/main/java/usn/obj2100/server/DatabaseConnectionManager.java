package usn.obj2100.server;

import usn.obj2100.shared.Constants;

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
			
			if (Constants.CREATE_EXAMPLE_DATA)
			{
				/* Eksempeldata plasseres manuelt med en execution hver. Dette er ganske basic stuff,
				 * så skader ikke å gjøre det slik. Enklere å legge til mer data senere slik.
				 */
				Statement statement = connection.createStatement();
				
				/* Plassering. */
				statement.execute("INSERT INTO plassering (id, bygg, floy, etasje, rom) VALUES (1, 'Bygg A', 1, 2, 101)");
				statement.execute("INSERT INTO plassering (id, bygg, floy, etasje, rom) VALUES (2, 'Bygg B', 2, 3, 202)");
				
				/* KategoriType. */
				statement.execute("INSERT INTO kategoriType (id, type) VALUES (1, 'Møbler')");
				statement.execute("INSERT INTO kategoriType (id, type) VALUES (2, 'Utsmykning')");
				statement.execute("INSERT INTO kategoriType (id, type) VALUES (3, 'Teknisk Utstyr')");
				
				/* Kategori. */
				// Mobler.
				statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (1, 1, 'Bord')");
				statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (2, 1, 'Stol')");
				statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (3, 1, 'Sofa')");
				statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (4, 1, 'Hylle')");
				statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (5, 1, 'Tavle')");
				statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (6, 1, 'Annet')");
				
				// Utsmykning.
				statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (7, 2, 'Maleri')");
				statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (8, 2, 'Grafikk')");
				statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (9, 2, 'Tekstil')");
				statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (10, 2, 'Bilde')");
				statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (11, 2, 'Skulptur')");
				statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (12, 2, 'Annet')");
				
				// Teknisk utstyr.
				statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (13, 3, 'Datamaskiner')");
				
				/* KassertType. */
				statement.execute("INSERT INTO kassertType (id, begrunnelse) VALUES (1, 'Solgt')");
				statement.execute("INSERT INTO kassertType (id, begrunnelse) VALUES (2, 'Kassert')");
				statement.execute("INSERT INTO kassertType (id, begrunnelse) VALUES (3, 'På lager')");
				statement.execute("INSERT INTO kassertType (id, begrunnelse) VALUES (4, 'Annet')");
				
				/* Kassert. */
				statement.execute("INSERT INTO kassert (id, dato, tid, begrunnelse) VALUES (1, '2022-01-01', '10:00:00', 1)");
				statement.execute("INSERT INTO kassert (id, dato, tid, begrunnelse) VALUES (2, '2022-02-01', '11:00:00', 2)");
				
				/* Finally, Inventar data. */
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (1, 'Stol', '2022-01-01 10:00:00', 100.0, 10, 5, 1, 1, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (2, 'Maleri', '2022-02-01 11:00:00', 200.0, 20, 7, 2, 2, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (3, 'Lyse brun, mahogany', '2024-12-24 22:38:21', 300.0, 30, 4, 1, 2, null)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (4, 'Gaming stol/pult kombo ting', '2021-01-13 12:14:13', 50.0, 85, 3, 6, 2, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (5, 'Bilde av mamma', '2025-01-13 12:14:13', 74.38, 12, 7, 10, 1, 4)");
				
				// Litt ekstra, kanskje.
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (6, 'Description 6', '2022-01-01 10:00:00', 100.0, 10, 5, 1, 1, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (7, 'Description 7', '2022-02-01 11:00:00', 200.0, 20, 7, 2, 2, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (8, 'Description 8', '2022-03-01 12:00:00', 300.0, 30, 6, 3, 1, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (9, 'Description 9', '2022-04-01 13:00:00', 400.0, 40, 5, 4, 2, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (10, 'Description 10', '2022-05-01 14:00:00', 500.0, 50, 4, 5, 1, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (11, 'Description 11', '2022-06-01 15:00:00', 600.0, 60, 3, 6, 2, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (12, 'Description 12', '2022-07-01 16:00:00', 700.0, 70, 2, 7, 1, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (13, 'Description 13', '2022-08-01 17:00:00', 800.0, 80, 1, 8, 2, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (14, 'Description 14', '2022-09-01 18:00:00', 900.0, 90, 5, 9, 1, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (15, 'Description 15', '2022-10-01 19:00:00', 1000.0, 100, 4, 10, 2, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (16, 'Description 16', '2022-11-01 20:00:00', 1100.0, 110, 3, 11, 1, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (17, 'Description 17', '2022-12-01 21:00:00', 1200.0, 120, 2, 12, 2, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (18, 'Description 18', '2023-01-01 22:00:00', 1300.0, 130, 1, 13, 1, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (19, 'Description 19', '2023-02-01 23:00:00', 1400.0, 140, 5, 1, 2, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (20, 'Description 20', '2023-03-01 00:00:00', 1500.0, 150, 4, 2, 1, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (21, 'Description 21', '2023-04-01 01:00:00', 1600.0, 160, 3, 3, 2, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (22, 'Description 22', '2023-05-01 02:00:00', 1700.0, 170, 2, 4, 1, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (23, 'Description 23', '2023-06-01 03:00:00', 1800.0, 180, 1, 5, 2, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (24, 'Description 24', '2023-07-01 04:00:00', 1900.0, 190, 5, 6, 1, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (25, 'Description 25', '2023-08-01 05:00:00', 2000.0, 200, 4, 7, 2, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (26, 'Description 26', '2023-09-01 06:00:00', 2100.0, 210, 3, 8, 1, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (27, 'Description 27', '2023-10-01 07:00:00', 2200.0, 220, 2, 9, 2, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (28, 'Description 28', '2023-11-01 08:00:00', 2300.0, 230, 1, 10, 1, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (29, 'Description 29', '2023-12-01 09:00:00', 2400.0, 240, 5, 11, 2, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (30, 'Description 30', '2024-01-01 10:00:00', 2500.0, 250, 4, 12, 1, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (31, 'Description 31', '2024-02-01 11:00:00', 2600.0, 260, 3, 13, 2, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (32, 'Description 32', '2024-03-01 12:00:00', 2700.0, 270, 2, 1, 1, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (33, 'Description 33', '2024-04-01 13:00:00', 2800.0, 280, 1, 2, 2, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (34, 'Description 34', '2024-05-01 14:00:00', 2900.0, 290, 5, 3, 1, 2)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (35, 'Description 35', '2024-06-01 15:00:00', 3000.0, 300, 4, 4, 2, 1)");
				statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (36, 'Description 36', '2024-07-01 16:00:00', 3100.0, 310, 3, 5, 1, 2)");
			}
		}
		catch (SQLException error)
		{
			error.printStackTrace(System.err);
		}
	}
}
