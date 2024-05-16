package usn.obj2100.server.clientTests.search;

import org.junit.jupiter.api.*;
import usn.obj2100.server.clientTests.utils.FakeClient;
import usn.obj2100.shared.model.Search;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Søk etter")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class SearchTest
{
	private FakeClient client;
	
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
	@DisplayName("(Testing) implementerings test")
	public void isImplemented()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		//TODO kommenterte ut denne testen, må man ikke bruke build!?
		//Search searchQuery = new Search(
		//	"Ost"
		//);
		
		Search query = new Search.Builder()
			.search("Ost")
			.build();
		
		Object response = client.request(query);
		assertNotNull(response, "Kunne ikke hente søket.");
		
		System.out.println("Respons: " + response);
	}
	
	/* BASIC SEARCHES. */
	
	@Test
	@DisplayName("en gjenstand som ikke eksisterer")
	public void searchForKnownUnknownItem()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("Ost")
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("en gjenstand som eksisterer")
	public void searchForKnownItem()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("a")
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	/* ADVANCED SEARCHES. */
	
	@Test
	@DisplayName("noe og filtrer etter beskrivelse")
	public void searchWithFilterDescription()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("a")
			.searchByBeskrivelse("ri")
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter kategori")
	public void searchWithFilterCategory()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("a")
			.searchByKategori("Stol")
			.build();
		
		System.out.println("Search: " + query.toString());
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter plassering")
	public void searchWithFilterLocation()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("a")
			.searchByPlassering("Bygg A")
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	// FIXME: Fungerer ikke riktig akkurat nå.
	@Test
	@DisplayName("noe og filtrer etter innkjøpsdato")
	public void searchWithFilterPurchaseDate()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByInnkjopsdato(Date.valueOf("2024-02-01"))
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	
	// FIXME: Fungerer ikke riktig akkurat nå.
	@Test
	@DisplayName("noe og filtrer etter pris")
	public void searchWithFilterPrice()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByPris(13.0, 48.0)
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	// FIXME: Fungerer ikke riktig akkurat nå.
	@Test
	@DisplayName("noe og filtrer etter type")
	public void searchWithFilterType()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByType("a")
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter levetid")
	public void searchWithFilterLifetime()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByLevetid(1, 5)
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter forventet kassering")
	public void searchWithFilterExpectedDisposal()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByForventetKassering(1,4)
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter om den er i bruk")
	public void searchWithFilterIsInUse()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByIBruk(true)
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
}
