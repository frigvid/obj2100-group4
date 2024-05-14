package usn.obj2100;

import org.junit.jupiter.api.*;
import usn.obj2100.utils.FakeClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("En (falsk) klient prøver å")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServerTest
{
	private Server server;
	private FakeClient client;
	
	@BeforeEach
	public void setup()
	{
		try
		{
			client = new FakeClient("127.0.0.1", 17244);
		}
		catch (Exception error)
		{
			error.printStackTrace(System.err);
		}
	}
	
	@Test
	@Order(1)
	@DisplayName("koble til serveren.")
	public void testConnection()
	{
		// The client should be connected
		assertTrue(client.isConnected());
	}
	
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
		assertEquals("Echo: ", message, receivedMessage);
	}
	
	@AfterEach
	public void teardown()
	{
		client.disconnect();
	}
}
