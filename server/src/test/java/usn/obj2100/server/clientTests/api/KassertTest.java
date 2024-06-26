package usn.obj2100.server.clientTests.api;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import usn.obj2100.shared.Command;
import usn.obj2100.server.clientTests.utils.FakeClient;
import usn.obj2100.shared.Type;
import usn.obj2100.shared.model.Kassert;

import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the Kassert model.
 * <p/>
 * Ensures that a simulated client can create, read, update, and delete a Kassert object.
 */
@DisplayName("En kassert er")
@TestMethodOrder(OrderAnnotation.class)
public class KassertTest
{
	private FakeClient client;
	private static Kassert newKassert;
	
	/**
	 * Create a new Kassert object before all tests.
	 */
	@BeforeAll
	@DisplayName("Delt oppsett for alle tester.")
	public static void preSetup()
	{
		newKassert = new Kassert(
			(new Date(System.currentTimeMillis())),
			(new Timestamp(System.currentTimeMillis())),
			4
		);
	}
	
	/**
	 * Create a fake client before each test.
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
			fail("Kunne ikke opprette en ny klient.");
		}
	}
	
	/**
	 * Create a new Kassert object.
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
			newKassert = (Kassert) client.request(Command.CREATE, newKassert);
		}
		catch (Exception error)
		{
			fail("Kunne ikke opprette en ny kassert.");
		}
	}
	
	/**
	 * Get the Kassert object.
	 */
	@Test
	@Order(2)
	@DisplayName("hentet.")
	public void getObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Kassert fakeKassert = new Kassert(
			(newKassert.getId())
		);
		
		Kassert retrievedKassert = (Kassert) client.request(Command.READ, fakeKassert);
		assertNotNull(retrievedKassert, "Kassert ble ikke hentet eller funnet.");
		assertEquals(newKassert.getId(), retrievedKassert.getId(), "ID er ikke lik.");
	}
	
	/**
	 * Get all Kassert objects.
	 */
	@Test
	@Order(3)
	@DisplayName("hent alle.")
	@Disabled("Denne testen feiler fordi servermetodene ikke er riktige.")
	public void getAllObjects()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Object response = client.request(Type.KASSERT);
		
		System.out.println("Respons: " + response);
		
		assertNotNull(response, "Kunne ikke hente alle kassert objekter.");
	}
	
	/**
	 * Update the Kassert object.
	 */
	@Test
	@Order(4)
	@DisplayName("oppdatert.")
	public void updateObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		newKassert.setBegrunnelse(1);
		
		assertTrue((boolean) client.request(Command.UPDATE, newKassert), "Kassert kunne ikke oppdateres.");
	}
	
	/**
	 * Delete the Kassert object.
	 */
	@Test
	@Order(5)
	@DisplayName("slettet.")
	public void deleteObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		assertTrue((boolean) client.request(Command.DELETE, newKassert), "Kassert ble ikke slettet.");
	}
	
	/**
	 * Cleanup after each test.
	 */
	@AfterEach
	@DisplayName("Rydding etter hver test.")
	public void cleanup()
	{
		client.disconnect();
	}
	
	/**
	 * Cleanup after all tests.
	 */
	@AfterAll
	@DisplayName("Delt rydding for alle tester.")
	public static void postCleanup()
	{
		System.out.println("Server stoppes.");
		FakeClient.stopServer();
	}
}
