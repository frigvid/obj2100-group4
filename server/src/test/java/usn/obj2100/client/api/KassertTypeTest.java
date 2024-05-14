package usn.obj2100.client.api;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import usn.obj2100.Command;
import usn.obj2100.client.utils.FakeClient;
import usn.obj2100.model.KassertType;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("En kassert type er")
@TestMethodOrder(OrderAnnotation.class)
public class KassertTypeTest
{
	private FakeClient client;
	private static KassertType newKassertType;
	
	@BeforeAll
	@DisplayName("Delt oppsett for alle tester.")
	public static void preSetup()
	{
		newKassertType = new KassertType(
			"Oktagonell banan"
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
			newKassertType = (KassertType) client.request(Command.CREATE, newKassertType);
		}
		catch (Exception error)
		{
			fail("Kunne ikke opprette en ny kassert type.");
		}
	}
	
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
	@DisplayName("oppdatert.")
	public void updateObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		newKassertType.setBegrunnelse("Kvadratisk banan");
		
		assertTrue((boolean) client.request(Command.UPDATE, newKassertType), "Kassert type ble ikke oppdatert.");
	}
	
	@Test
	@Order(4)
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
