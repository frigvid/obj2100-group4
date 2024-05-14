package usn.obj2100.client.api;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import usn.obj2100.Command;
import usn.obj2100.client.utils.FakeClient;
import usn.obj2100.model.Inventar;

import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("En inventar er")
@TestMethodOrder(OrderAnnotation.class)
public class InventarTest
{
	private FakeClient client;
	private static Inventar newInventar;
	
	@BeforeAll
	@DisplayName("Delt oppsett for alle tester.")
	public static void preSetup()
	{
		Random random = new Random();
		
		newInventar = new Inventar(
			random.nextInt(1000),
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
	
	@Test
	@Order(1)
	@DisplayName("opprettet.")
	public void createObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		assertTrue((boolean) client.request(Command.CREATE, newInventar));
	}
	
	@Test
	@Order(2)
	@DisplayName("hentet.")
	public void getObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Inventar fakeInventar = new Inventar(
			1
		);
		
		Inventar retrievedObject = (Inventar) client.request(Command.READ, fakeInventar);
		assertNotNull(retrievedObject, "Inventar objektet ble ikke hentet.");
		assertEquals(newInventar.getSKU(), retrievedObject.getSKU(), "SKU er ikke lik.");
	}
	
	@Test
	@Order(3)
	@DisplayName("oppdatert.")
	public void updateObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		newInventar.setBeskrivelse("KlientTest: Oppdatert inventar objekt beskrivelse.");
		
		System.out.println(newInventar.toString());
		
		assertTrue((boolean) client.request(Command.UPDATE, newInventar));
	}
	
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
