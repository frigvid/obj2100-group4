package usn.obj2100.client.api;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import usn.obj2100.Command;
import usn.obj2100.client.utils.FakeClient;
import usn.obj2100.model.Inventar;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
		newInventar = new Inventar(
			1,
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
		
		client.sendObject(Command.CREATE, newInventar);
	}
	
	@Test
	@Order(2)
	@DisplayName("hentet inventar element.")
	public void receiveInventar()
	{
		// FIXME: TEMPORARY.
		assertTrue(true);
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
