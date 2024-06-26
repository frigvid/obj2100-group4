package usn.obj2100.server.clientTests.api;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import usn.obj2100.shared.Command;
import usn.obj2100.server.clientTests.utils.FakeClient;
import usn.obj2100.shared.Type;
import usn.obj2100.shared.model.Inventar;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the Inventar model.
 * <p/>
 * Ensures that a simulated client can create, read, update, and delete an Inventar object.
 */
@DisplayName("En inventar er")
@TestMethodOrder(OrderAnnotation.class)
public class InventarTest
{
	private FakeClient client;
	private static Inventar newInventar;
	
	/**
	 * Create a new Inventar object before all tests.
	 */
	@BeforeAll
	@DisplayName("Delt oppsett for alle tester.")
	public static void preSetup()
	{
		newInventar = new Inventar(
			"KlientTest: Inventar objekt beskrivelse.",
			LocalDateTime.now(),
			100.0,
			10,
			1,
			1,
			1,
			0
		);
	}
	
	/**
	 * Create a fake client before each test.
	 * <p/>
	 * This also ensures a server is started.
	 */
	@BeforeEach
	@DisplayName("Oppsett for hver test.")
	public void setup()
	{
		try
		{
			client = new FakeClient();
		}
		catch (Exception error)
		{
			error.printStackTrace(System.err);
		}
	}
	
	/**
	 * Create an Inventar object.
	 */
	@Test
	@Order(1)
	@DisplayName("opprettet.")
	public void createObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		try
		{
			newInventar = (Inventar) client.request(Command.CREATE, newInventar);
		}
		catch (Exception error)
		{
			fail("Kunne ikke opprette et nytt Inventar objekt.");
		}
	}
	
	/**
	 * Get an Inventar object.
	 */
	@Test
	@Order(2)
	@DisplayName("hentet.")
	public void getObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Inventar fakeInventar = new Inventar(
			(newInventar.getSKU())
		);
		
		Inventar retrievedObject = (Inventar) client.request(Command.READ, fakeInventar);
		assertNotNull(retrievedObject, "Inventar objektet ble ikke hentet.");
		assertEquals(newInventar.getSKU(), retrievedObject.getSKU(), "SKU er ikke lik.");
	}
	
	/**
	 * Get all Inventar objects.
	 */
	@Test
	@Order(3)
	@DisplayName("hent alle")
	public void getAllObjects()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Object response = client.request(Type.INVENTAR);
		
		System.out.println("Respons: " + response);
		
		assertNotNull(response, "Kunne ikke hente alle Inventar objekter.");
	}
	
	
	/**
	 * Update an Inventar object.
	 */
	@Test
	@Order(4)
	@DisplayName("oppdatert.")
	public void updateObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		newInventar.setBeskrivelse("KlientTest: Oppdatert inventar objekt beskrivelse.");
		
		assertTrue((boolean) client.request(Command.UPDATE, newInventar));
	}
	
	/**
	 * Delete an Inventar object.
	 */
	@Test
	@Order(5)
	@DisplayName("slettet.")
	public void deleteObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		assertTrue((boolean) client.request(Command.DELETE, newInventar));
	}
	
	/**
	 * Disconnect the client from the server after each test.
	 */
	@AfterEach
	public void teardown()
	{
		client.disconnect();
	}
	
	/**
	 * Stop the server after all tests are done.
	 */
	@AfterAll
	public static void stopServer()
	{
		System.out.println("Server stoppes.");
		FakeClient.stopServer();
	}
}
