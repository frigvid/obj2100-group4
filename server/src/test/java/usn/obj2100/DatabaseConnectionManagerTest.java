package usn.obj2100;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionManagerTest
{
	private DatabaseConnectionManager dcm;
	
	@BeforeEach
	public void setUp()
	{
		dcm = DatabaseConnectionManager.getInstance();
		dcm.getConnection();
	}
	
	@Test
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
	
	@Test
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
