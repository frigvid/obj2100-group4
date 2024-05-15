package usn.obj2100.server.serverTests.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import usn.obj2100.server.DatabaseConnectionManager;
import usn.obj2100.server.Server;
import usn.obj2100.server.controller.InventarController;
import usn.obj2100.shared.model.Inventar;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("InventarController")
@TestMethodOrder(OrderAnnotation.class)
public class InventarControllerTest
{
	private Server server;
	private DatabaseConnectionManager dcm;
	private InventarController inventarController;
	
	/**
	 * Get a connection to the database before each test.
	 */
	@BeforeEach
	public void setUp()
	{
		server = new Server();
		
		dcm = DatabaseConnectionManager.getInstance();
		dcm.getConnection();
		
		inventarController = new InventarController();
	}
	
	@Test
	@Order(1)
	@DisplayName("kan hente alle")
	public void getAllObjects()
	{
		Connection connection = dcm.getConnection();
		assertNotNull(connection, "Database connection should not be null");
		
		assertNotNull(server, "Server must be running");
		
		List<Inventar> response = inventarController.getAll();
		
		for (Object object: response)
		{
			System.out.println("Inventar object: " + object);
			assertNotNull(object, "Inventar object should not be null");
		}
	}
}
