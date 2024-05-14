package usn.obj2100.client.api;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import usn.obj2100.Command;
import usn.obj2100.client.utils.FakeClient;
import usn.obj2100.model.Inventar;

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
	private static final int SKU = 7839223;
	
	/**
	 * Create a new Inventar object before all tests.
	 */
	@BeforeAll
	@DisplayName("Delt oppsett for alle tester.")
	public static void preSetup()
	{
		newInventar = new Inventar(
			SKU,
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
		
		assertTrue((boolean) client.request(Command.CREATE, newInventar));
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
			SKU
		);
		
		Inventar retrievedObject = (Inventar) client.request(Command.READ, fakeInventar);
		assertNotNull(retrievedObject, "Inventar objektet ble ikke hentet.");
		assertEquals(newInventar.getSKU(), retrievedObject.getSKU(), "SKU er ikke lik.");
	}
	
	/**
	 * Update an Inventar object.
	 */
	@Test
	@Order(3)
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
	@Order(4)
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
