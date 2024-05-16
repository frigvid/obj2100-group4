package usn.obj2100.server.clientTests.api;

import org.junit.jupiter.api.*;
import usn.obj2100.shared.Command;
import usn.obj2100.server.clientTests.utils.FakeClient;
import usn.obj2100.shared.model.Kategori;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the Kategori model.
 * <p/>
 * Ensures that a simulated client can create, read, update, and delete a Kategori object.
 *
 * @created 2024-05-14
 */
@DisplayName("En kategori er")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KategoriTest
{
	private FakeClient client;
	private static Kategori newKategori;
	
	/**
	 * Create a new Kategori object before all tests.
	 */
	@BeforeAll
	@DisplayName("Delt oppsett for alle tester.")
	public static void preSetup()
	{
		newKategori = new Kategori(
			1,
			"Elektronikk"
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
	 * Test the connection to the server.
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
			newKategori = (Kategori) client.request(Command.CREATE, newKategori);
		}
		catch (Exception error)
		{
			fail("Kunne ikke opprette en ny kategori.");
		}
	}
	
	/**
	 * Test the connection to the server.
	 */
	@Test
	@Order(2)
	@DisplayName("hentet.")
	public void getObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Kategori fakeKategori = new Kategori(
			(newKategori.getId())
		);
		
		Kategori retrievedKategori = (Kategori) client.request(Command.READ, fakeKategori);
		assertNotNull(retrievedKategori, "Kategori ble ikke hentet eller funnet.");
		assertEquals(newKategori.getId(), retrievedKategori.getId(), "ID er ikke lik.");
	}
	
	/**
	 * Test the connection to the server.
	 */
	@Test
	@Order(3)
	@DisplayName("oppdatert.")
	public void updateObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		newKategori.setType(3);
		newKategori.setKategori("Elektronikk og data");
		
		assertTrue((boolean) client.request(Command.UPDATE, newKategori), "Kategori ble ikke oppdatert.");
	}
	
	/**
	 * Test the connection to the server.
	 */
	@Test
	@Order(4)
	@DisplayName("slettet.")
	public void deleteObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		assertTrue((boolean) client.request(Command.DELETE, newKategori), "Kategori ble ikke slettet.");
	}
	
	/**
	 * Disconnect the client from the server after each test.
	 */
	@AfterEach
	@DisplayName("Rydd opp etter hver test.")
	public void cleanup()
	{
		client.disconnect();
	}
	
	/**
	 * Stop the server after all tests.
	 */
	@AfterAll
	@DisplayName("Delt opprydding for alle tester.")
	public static void postCleanup()
	{
		System.out.println("Server stoppes.");
		FakeClient.stopServer();
	}
}
