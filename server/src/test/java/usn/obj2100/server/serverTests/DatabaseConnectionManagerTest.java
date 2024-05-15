package usn.obj2100.server.serverTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import usn.obj2100.server.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the database connection manager.
 * <p/>
 * Ensures that the database connection manager can create a connection to the database.
 *
 * @created 2024-05-13
 * @see DatabaseConnectionManager
 */
@DisplayName("Databasetilkoblingsbehandleren")
public class DatabaseConnectionManagerTest
{
	private DatabaseConnectionManager dcm;
	
	/**
	 * Get a connection to the database before each test.
	 */
	@BeforeEach
	public void setUp()
	{
		dcm = DatabaseConnectionManager.getInstance();
		dcm.getConnection();
	}
	
	/**
	 * Test that the database connection manager can create a connection to the database by checking if tables exist.
	 */
	@Test
	@DisplayName("kan opprette en tilkobling til databasen.")
	public void testCreateIfNotExists()
	{
		Connection connection = dcm.getConnection();
		assertNotNull(connection, "Database connection should not be null");
		
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");
			
			assertTrue(rs.next(), "At least one table should exist in the database");
		}
		catch (SQLException e)
		{
			fail("An error occurred while checking the database tables: " + e.getMessage());
		}
	}
	
	/**
	 * Test that the database connection manager can initialize the database by checking if the expected tables exist.
	 *
	 * @see DatabaseConnectionManager#createIfNotExists()
	 */
	@Test
	@DisplayName("kan initialisere databasen.")
	public void testInitializeDatabase()
	{
		Connection connection = dcm.getConnection();
		assertNotNull(connection, "Database connection should not be null");
		
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");
			
			while (rs.next())
			{
				String tableName = rs.getString("name");
				assertTrue(
					tableName.equals("plassering") ||
						tableName.equals("inventar") ||
						tableName.equals("kassert") ||
						tableName.equals("kassertType") ||
						tableName.equals("kategori") ||
						tableName.equals("kategoriType"),
					"Unexpected table name: " + tableName
				);
			}
		}
		catch (SQLException e)
		{
			fail("An error occurred while checking the database tables: " + e.getMessage());
		}
	}
}
