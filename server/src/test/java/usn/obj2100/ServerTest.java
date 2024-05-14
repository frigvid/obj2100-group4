package usn.obj2100;

import org.junit.jupiter.api.*;
import usn.obj2100.utils.FakeClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A test class for the server.
 * <p/>
 * This simply attempts a connection to the server, and
 * attempts to send and receive a message.
 *
 * @created 2024-04-14
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
	public void testConnection()
	{
		System.out.println("Is client connected? " + client.isConnected());
		
		// The client should be connected
		assertTrue(client.isConnected());
	}
	
	/**
	 * Test sending and receiving a message to the server.
	 */
	@Test
	@Order(2)
	@DisplayName("sende en melding til serveren.")
	public void testSendAndRecieveEcho()
	{
		/* Send a message from the server to the client. */
		String message = "Hello, client!";
		client.sendMessage(message);
		
		/* The client should receive the message. */
		String receivedMessage = client.receiveMessage();
		assertEquals("Echo: " + message, receivedMessage);
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
