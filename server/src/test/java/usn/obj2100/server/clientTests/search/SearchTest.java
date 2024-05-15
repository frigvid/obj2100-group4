package usn.obj2100.server.clientTests.search;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import usn.obj2100.shared.Command;
import usn.obj2100.server.clientTests.utils.FakeClient;
import usn.obj2100.shared.model.Search;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Søk etter")
@TestMethodOrder(OrderAnnotation.class)
public class SearchTest
{
	private FakeClient client;
	private static Search newSearch;
	
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
	@DisplayName("Implementert test")
	public void isImplemented()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search searchQuery = new Search(
			"Ost"
		);
		
		Object response = client.request(searchQuery);
		assertNotNull(response, "Kunne ikke hente søket.");
		
		System.out.println("Respons: " + response);
	}
}
