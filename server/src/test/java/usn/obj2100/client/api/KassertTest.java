package usn.obj2100.client.api;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import usn.obj2100.Command;
import usn.obj2100.client.utils.FakeClient;
import usn.obj2100.model.Kassert;

import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("En kassert er")
@TestMethodOrder(OrderAnnotation.class)
public class KassertTest
{
	private FakeClient client;
	private static Kassert newKassert;
	
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
			newKassert = (Kassert) client.request(Command.CREATE, newKassert);
		}
		catch (Exception error)
		{
			fail("Kunne ikke opprette en ny kassert.");
		}
	}
	
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
	
	@Test
	@Order(3)
	@DisplayName("oppdatert.")
	public void updateObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		newKassert.setBegrunnelse(1);
		
		assertTrue((boolean) client.request(Command.UPDATE, newKassert), "Kassert kunne ikke oppdateres.");
	}
	
	@Test
	@Order(4)
	@DisplayName("slettet.")
	public void deleteObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		assertTrue((boolean) client.request(Command.DELETE, newKassert), "Kassert ble ikke slettet.");
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
