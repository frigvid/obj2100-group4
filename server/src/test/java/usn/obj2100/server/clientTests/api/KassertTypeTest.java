package usn.obj2100.server.clientTests.api;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import usn.obj2100.shared.Command;
import usn.obj2100.server.clientTests.utils.FakeClient;
import usn.obj2100.shared.Type;
import usn.obj2100.shared.model.KassertType;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the KassertType model.
 * <p/>
 * Ensures that a simulated client can create, read, update, and delete a KassertType object.
 *
 * @created 2024-05-14
 * @since 0.3
 */
@DisplayName("En kassert type er")
@TestMethodOrder(OrderAnnotation.class)
public class KassertTypeTest
{
	private FakeClient client;
	private static KassertType newKassertType;
	
	/**
	 * Create a new KassertType object before all tests.
	 */
	@BeforeAll
	@DisplayName("Delt oppsett for alle tester.")
	public static void preSetup()
	{
		newKassertType = new KassertType(
			"Oktagonell banan"
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
	 * Create a new KassertType object.
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
			newKassertType = (KassertType) client.request(Command.CREATE, newKassertType);
		}
		catch (Exception error)
		{
			fail("Kunne ikke opprette en ny kassert type.");
		}
	}
	
	/**
	 * Get the KassertType object.
	 */
	@Test
	@Order(2)
	@DisplayName("hentet.")
	public void getObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		KassertType fakeKassertType = new KassertType(
			(newKassertType.getId())
		);
		
		KassertType retrievedKassertType = (KassertType) client.request(Command.READ, fakeKassertType);
		assertNotNull(retrievedKassertType, "Kassert type ble ikke hentet eller funnet.");
		assertEquals(newKassertType.getId(), retrievedKassertType.getId(), "ID er ikke lik.");
	}
	
	@Test
	@Order(3)
	@DisplayName("hent alle")
	public void getAllObjects()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Object response = client.request(Type.KASSERT_TYPE);
		
		System.out.println("Respons: " + response);
		
		assertNotNull(response, "Kunne ikke hente alle kassert typer.");
	}
	
	@Test
	@Order(4)
	@DisplayName("oppdatert.")
	public void updateObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		newKassertType.setBegrunnelse("Kvadratisk banan");
		
		assertTrue((boolean) client.request(Command.UPDATE, newKassertType), "Kassert type ble ikke oppdatert.");
	}
	
	@Test
	@Order(5)
	@DisplayName("slettet.")
	public void deleteObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		assertTrue((boolean) client.request(Command.DELETE, newKassertType), "Kassert type ble ikke slettet.");
	}
	
	@AfterEach
	@DisplayName("Rydding etter hver test.")
	public void cleanup()
	{
		client.disconnect();
	}
	
	@AfterAll
	@DisplayName("Delt rydding for alle tester.")
	public static void postCleanup()
	{
		System.out.println("Server stoppes.");
		FakeClient.stopServer();
	}
}
