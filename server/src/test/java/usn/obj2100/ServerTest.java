package usn.obj2100;

import org.junit.jupiter.api.*;
import usn.obj2100.utils.FakeClient;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("En (falsk) klient prøver å")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServerTest
{
	private FakeClient client;
	
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
	
	@Test
	@Order(1)
	@DisplayName("koble til serveren.")
	public void testConnection()
	{
		System.out.println("Is client connected? " + client.isConnected());
		
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
		assertEquals("Echo: " + message, receivedMessage);
	}
	
	@AfterEach
	public void teardown()
	{
		client.disconnect();
	}
}
