package usn.obj2100.client.api;

import org.junit.jupiter.api.*;
import usn.obj2100.Command;
import usn.obj2100.client.utils.FakeClient;
import usn.obj2100.model.Kategori;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("En kategori er")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KategoriTest
{
	private FakeClient client;
	private static Kategori newKategori;
	
	@BeforeAll
	@DisplayName("Delt oppsett for alle tester.")
	public static void preSetup()
	{
		newKategori = new Kategori(
			1,
			"Elektronikk"
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
			fail("Kunne ikke opprette en ny klient.");
		}
	}
	
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
	
	@Test
	@Order(4)
	@DisplayName("slettet.")
	public void deleteObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		assertTrue((boolean) client.request(Command.DELETE, newKategori), "Kategori ble ikke slettet.");
	}
	
	@AfterEach
	@DisplayName("Rydd opp etter hver test.")
	public void cleanup()
	{
		client.disconnect();
	}
	
	@AfterAll
	@DisplayName("Delt opprydding for alle tester.")
	public static void postCleanup()
	{
		System.out.println("Server stoppes.");
		FakeClient.stopServer();
	}
}
