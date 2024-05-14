package usn.obj2100.client.api;

import org.junit.jupiter.api.*;
import usn.obj2100.Command;
import usn.obj2100.client.utils.FakeClient;
import usn.obj2100.model.Plassering;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the Plassering model.
 * <p/>
 * Ensures that a simulated client can create, read, update, and delete a Plassering object.
 *
 * @created 2024-05-14
 * @version 0.1
 */
@DisplayName("En plassering er")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlasseringTest
{
	private FakeClient client;
	private static Plassering newPlassering;
	
	/**
	 * Start serveren før alle tester.
	 */
	@BeforeAll
	@DisplayName("Delt oppsett for alle tester.")
	public static void preSetup()
	{
		newPlassering = new Plassering(
			"USN-Bø",
			5,
			1,
			18
		);
	}
	
	/**
	 * Start serveren før alle tester.
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
			/* InventarTest bruker en stack trace, men bruk av JUnit5 sin fail assertion
			 * kan være mer tilpasselig for dette bruksområdet. Vi tester det her, og vil bruker
			 * det på andre plasser om det funker bra.
			 */
			fail("Kunne ikke opprette en ny klient.");
		}
	}
	
	/**
	 * Opprett et Plassering objekt.
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
			newPlassering = (Plassering) client.request(Command.CREATE, newPlassering);
		}
		catch (Exception error)
		{
			fail("Kunne ikke opprette et nytt Plassering objekt.");
		}
	}
	
	/**
	 * Hent et Plassering objekt.
	 */
	@Test
	@Order(2)
	@DisplayName("hentet.")
	public void getObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Plassering fakePlassering = new Plassering(
			(newPlassering.getId())
		);
		
		System.out.println("Fake plassering: " + fakePlassering.getId());
		
		Plassering retrievedPlassering = (Plassering) client.request(Command.READ, fakePlassering);
		assertNotNull(retrievedPlassering, "Plassering ble ikke hentet eller funnet.");
		assertEquals(newPlassering.getId(), retrievedPlassering.getId(), "ID er ikke lik.");
	}
	
	/**
	 * Oppdater et Plassering objekt.
	 */
	@Test
	@Order(3)
	@DisplayName("oppdatert.")
	public void updateObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		newPlassering.setEtasje(2);
		
		assertTrue((boolean) client.request(Command.UPDATE, newPlassering));
	}
	
	// TODO: Denne er nok litt for fragil. Den er nok "true" selv om ingenting
	//			faktisk blir slettet, siden SQL statementet kan sånn sett gjennomføres
	//			uten at den får en feilmelding. Det burde være en assertion for at
	//			den faktisk er slettet her.
	/**
	 * Slett et Plassering objekt.
	 */
	@Test
	@Order(4)
	@DisplayName("slettet.")
	public void deleteObject()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		assertTrue((boolean) client.request(Command.DELETE, newPlassering));
	}
	
	/**
	 * Rydd opp etter hver test.
	 */
	@AfterEach
	@DisplayName("Rydd opp etter hver test.")
	public void teardown()
	{
		client.disconnect();
	}
	
	/**
	 * Rydd opp etter alle tester er ferdige.
	 */
	@AfterAll
	@DisplayName("Rydd opp etter alle tester er ferdige.")
	public static void postTeardown()
	{
		System.out.println("Server stoppes.");
		FakeClient.stopServer();
	}
}
