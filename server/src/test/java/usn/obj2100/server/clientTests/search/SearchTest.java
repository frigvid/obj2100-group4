package usn.obj2100.server.clientTests.search;

import org.junit.jupiter.api.*;
import usn.obj2100.server.clientTests.utils.FakeClient;
import usn.obj2100.shared.model.Search;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the search functionality.
 * <p/>
 * This test class tests both individual basic searches,
 * and individual and grouped advanced searches.
 */
@DisplayName("Søk etter")
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class SearchTest
{
	private FakeClient client;
	
	/**
	 * Create a fake client before each test.
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
			fail("Kunne ikke opprette en ny klient.");
		}
	}
	
	/**
	 * Disconnect the client from the server after each test.
	 */
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
	/**
	 * Search for an item that does not exist.
	 */
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
	
	/**
	 * Search for an item that exists.
	 */
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
	
	/**
	 * Search for an item that exists and filter by description.
	 */
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
	
	/**
	 * Search for an item that exists and filter by category.
	 */
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
	
	/**
	 * Search for an item that exists and filter by location.
	 */
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
	/**
	 * Search for an item that exists and filter by purchase date.
	 */
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
	
	
	/**
	 * Search for an item that exists and filter by purchase price.
	 */
	@Test
	@DisplayName("noe og filtrer etter pris")
	public void searchWithFilterPrice()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("mamma")
			.searchByPris(13.0, 88.0)
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	/**
	 * Search for an item that exists and filter by amount.
	 */
	@Test
	@DisplayName("noe og filtrer etter type")
	public void searchWithFilterType()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByType("Møbler")
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	/**
	 * Search for an item that exists and filter by amount.
	 */
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
	
	/**
	 * Search for an item that exists and filter by disposal date.
	 */
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
	
	/**
	 * Search for an item that exists and filter by disposal date.
	 */
	@Test
	@DisplayName("noe og filtrer etter om den er i bruk")
	@Disabled("Denne er ikke ferdig implementert.")
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
	
	/* ADVANCED SEARCHES + MULTIPLES. */
	@Test
	@DisplayName("noe og filtrer etter beskrivelse og kategori")
	public void searchWithFilterDescriptionAndCategory()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByBeskrivelse("e")
			.searchByKategori("Stol")
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter beskrivelse og plassering")
	public void searchWithFilterDescriptionAndLocation()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByBeskrivelse("e")
			.searchByPlassering("Bygg A")
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter beskrivelse og innkjøpsdato")
	public void searchWithFilterDescriptionAndPurchaseDate()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByBeskrivelse("e")
			.searchByInnkjopsdato(Date.valueOf("2024-02-01"))
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter beskrivelse og forventet kassering")
	public void searchWithFilterDescriptionAndExpectedDisposalDate()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByBeskrivelse("e")
			.searchByForventetKassering(1,4)
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter beskrivelse og levetid")
	public void searchWithFilterDescriptionAndLifetime()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByBeskrivelse("e")
			.searchByLevetid(1, 5)
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter beskrivelse, kategori og plassering")
	public void searchWithFilterDescriptionCategoryAndLocation()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByBeskrivelse("e")
			.searchByKategori("Stol")
			.searchByPlassering("Bygg A")
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter beskrivelse, kategori og innkjøpsdato")
	public void searchWithFilterDescriptionCategoryAndPurchaseDate()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByBeskrivelse("e")
			.searchByKategori("Datamaskiner")
			.searchByInnkjopsdato(Date.valueOf("2024-02-01"))
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter beskrivelse, kategori og forventet kassering")
	public void searchWithFilterDescriptionCategoryAndExpectedDisposalDate()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByBeskrivelse("e")
			.searchByKategori("Datamaskiner")
			.searchByForventetKassering(1,4)
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter beskrivelse, kategori og levetid")
	public void searchWithFilterDescriptionCategoryAndLifetime()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByBeskrivelse("e")
			.searchByKategori("Datamaskiner")
			.searchByLevetid(1, 5)
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter beskrivelse, kategori, plassering og froventet kassering")
	public void searchWithFilterDescriptionCategoryLocationAndExpectedDisposalDate()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByBeskrivelse("e")
			.searchByKategori("Datamaskiner")
			.searchByPlassering("Bygg A")
			.searchByForventetKassering(1,4)
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter beskrivelse, kategori, plassering og levetid")
	public void searchWithFilterDescriptionCategoryLocationAndLifetime()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByBeskrivelse("e")
			.searchByKategori("Datamaskiner")
			.searchByPlassering("Bygg A")
			.searchByLevetid(1, 5)
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter beskrivelse, kategori, plassering og innkjøpsdato")
	public void searchWithFilterDescriptionCategoryLocationAndPurchaseDate()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByBeskrivelse("e")
			.searchByKategori("Datamaskiner")
			.searchByPlassering("Bygg B")
			.searchByInnkjopsdato(Date.valueOf("2024-02-01"))
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter beskrivelse, kategori, plassering, levetid og forventet kassering")
	public void searchWithFilterDescriptionCategoryLocationLifetimeAndExpectedDisposalDate()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByBeskrivelse("Description")
			.searchByKategori("Datamaskiner")
			.searchByPlassering("Bygg A")
			.searchByLevetid(1, 7)
			.searchByForventetKassering(1, 2)
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter beskrivelse, kategori, plassering, levetid, forventet kassering og innkjøpsdato")
	public void searchWithFilterDescriptionCategoryLocationLifetimeExpectedDisposalDateAndPurchaseDate()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("e")
			.searchByBeskrivelse("Description")
			.searchByKategori("Datamaskiner")
			.searchByPlassering("Bygg A")
			.searchByLevetid(1, 7)
			.searchByForventetKassering(1, 2)
			.searchByInnkjopsdato(Date.valueOf("2023-01-01"))
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
	
	@Test
	@DisplayName("noe og filtrer etter beskrivelse, kategori, plassering, levetid, innkjøpsdato og type")
	public void searchWithFilterDescriptionCategoryLocationLifetimeDatePurchaseDateAndType()
	{
		/* The client should be connected. */
		assertTrue(client.isConnected());
		
		Search query = new Search.Builder()
			.search("de")
			.searchByBeskrivelse("mamma")
			.searchByKategori("Bilde")
			.searchByType("Utsmykning")
			.searchByPlassering("Bygg A")
			.searchByLevetid(1, 12)
			.searchByInnkjopsdato(Date.valueOf("2025-01-13"))
			.build();
		
		/* Known type. */
		List<Object> response = (List<Object>) client.request(query);
		assertNotEquals(response.size(), 0);
		
		System.out.println("Respons: " + response);
	}
}
