package usn.obj2100.server.clientTests.api;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import usn.obj2100.shared.Command;
import usn.obj2100.server.clientTests.utils.FakeClient;
import usn.obj2100.shared.model.KategoriType;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the KategoriType model.
 * <p/>
 * Ensures that a simulated client can create, read, update, and delete a KategoriType object.
 *
 * @created 2024-05-14
 */
@DisplayName("En kategori type er")
@TestMethodOrder(OrderAnnotation.class)
public class KategoriTypeTest
{
	private FakeClient client;
	private static KategoriType newKategoriType;
	
	@BeforeAll
	@DisplayName("Delt oppsett for alle tester.")
	public static void preSetup()
	{
		newKategoriType = new KategoriType(
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
			newKategoriType = (KategoriType) client.request(Command.CREATE, newKategoriType);
		}
		catch (Exception error)
		{
			fail("Kunne ikke opprette en ny kategori type.");
		}
	}
	
	@Test
	@Order(2)
	@DisplayName("hentet.")
	public void getObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		KategoriType fakeKategoriType = new KategoriType(
			(newKategoriType.getId())
		);
		
		KategoriType retrievedKategoriType = (KategoriType) client.request(Command.READ, fakeKategoriType);
		assertNotNull(retrievedKategoriType, "Kategori type Kunne ikke hentes eller finnes.");
		assertEquals(newKategoriType.getId(), retrievedKategoriType.getId(), "Kategori type ble ikke hentet.");
	}
	
	@Test
	@Order(3)
	@DisplayName("oppdatert.")
	public void updateObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		newKategoriType.setType("4-dimensjonale møbler");
		
		assertTrue((boolean) client.request(Command.UPDATE, newKategoriType), "Kategori type ble ikke oppdatert.");
	}
	
	@Test
	@Order(4)
	@DisplayName("slettet.")
	public void deleteObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		assertTrue((boolean) client.request(Command.DELETE, newKategoriType), "Kategori type ble ikke slettet.");
	}
	
	@AfterEach
	@DisplayName("Rydding etter hver test.")
	public void cleanup()
	{
		client.disconnect();
	}
	
	@AfterAll
	@DisplayName("Delt rydding etter alle tester.")
	public static void postCleanup()
	{
		System.out.println("Server stoppes.");
		FakeClient.stopServer();
	}
}
