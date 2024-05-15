package usn.obj2100.server.clientTests;

import org.junit.jupiter.api.*;
import usn.obj2100.server.Server;
import usn.obj2100.server.clientTests.utils.FakeClient;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A test class for the server.
 * <p/>
 * This simply attempts a connection to the server.
 *
 * @created 2024-05-14
 * @see Server
 * @see FakeClient
 */
@DisplayName("En (falsk) klient prøver å")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServerTest
{
	private FakeClient client;
	
	/**
	 * Create a fake client before each test.
	 */
	@BeforeEach
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
	
	/**
	 * Test the connection to the server.
	 */
	@Test
	@Order(1)
	@DisplayName("koble til serveren.")
	public void connect()
	{
		System.out.println("Is client able to connect? " + client.isConnected());
		
		// The client should be connected
		assertTrue(client.isConnected());
	}
	
	/**
	 * Disconnect the client from the server after each test.
	 */
	@AfterEach
	public void teardown()
	{
		client.disconnect();
	}
}
