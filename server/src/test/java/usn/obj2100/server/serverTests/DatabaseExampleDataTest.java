package usn.obj2100.server.serverTests;

import org.junit.jupiter.api.*;
import usn.obj2100.server.DatabaseConnectionManager;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the example data in the database.
 * <p/>
 * Note that this test does <i>not</i> remove the added example data.
 * This is meant to be run once, for testing purposes, and not to be run again.
 *
 * @created 2024-05-14
 * @see DatabaseConnectionManager
 * @see DatabaseConnectionManagerTest
 */
@Tag("manual")
@DisplayName("Eksempel data for databasen er")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DatabaseExampleDataTest
{
	private DatabaseConnectionManager dcm;

	@BeforeEach
	public void setUp()
	{
		dcm = DatabaseConnectionManager.getInstance();
		dcm.getConnection();
	}

	@Test
	@Order(1)
	@DisplayName("opprettet.")
	@Disabled("DatabaseExampleDataTest should only be run manually.")
	public void createObject()
	{
		Connection connection = dcm.getConnection();
		assertNotNull(connection, "Database connection should not be null");

		try
		{
			/* Example data generation.
			 *
			 * Since this is pretty basic stuff, it's just being executed directly. */
			Statement statement = connection.createStatement();
			
			/* Plassering. */
			statement.execute("INSERT INTO plassering (id, bygg, floy, etasje, rom) VALUES (1, 'Bygg A', 1, 2, 101)");
			statement.execute("INSERT INTO plassering (id, bygg, floy, etasje, rom) VALUES (2, 'Bygg B', 2, 3, 202)");
			
			/* KategoriType. */
			statement.execute("INSERT INTO kategoriType (id, type) VALUES (1, 'Møbler')");
			statement.execute("INSERT INTO kategoriType (id, type) VALUES (2, 'Utsmykning')");
			statement.execute("INSERT INTO kategoriType (id, type) VALUES (3, 'Teknisk Utstyr')");
			
			/* Kategori. */
			// Mobler.
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (1, 1, 'Bord')");
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (2, 1, 'Stol')");
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (3, 1, 'Sofa')");
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (4, 1, 'Hylle')");
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (5, 1, 'Tavle')");
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (6, 1, 'Annet')");
			
			// Utsmykning.
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (7, 2, 'Maleri')");
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (8, 2, 'Grafikk')");
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (9, 2, 'Tekstil')");
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (10, 2, 'Bilde')");
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (11, 2, 'Skulptur')");
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (12, 2, 'Annet')");
			
			// Teknisk utstyr.
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (13, 3, 'Datamaskiner')");
			
			/* KassertType. */
			statement.execute("INSERT INTO kassertType (id, begrunnelse) VALUES (1, 'Solgt')");
			statement.execute("INSERT INTO kassertType (id, begrunnelse) VALUES (2, 'Kassert')");
			statement.execute("INSERT INTO kassertType (id, begrunnelse) VALUES (3, 'På lager')");
			statement.execute("INSERT INTO kassertType (id, begrunnelse) VALUES (4, 'Annet')");
			
			/* Kassert. */
			statement.execute("INSERT INTO kassert (id, dato, tid, begrunnelse) VALUES (1, '2022-01-01', '10:00:00', 1)");
			statement.execute("INSERT INTO kassert (id, dato, tid, begrunnelse) VALUES (2, '2022-02-01', '11:00:00', 2)");
			
			/* Finally, Inventar data. */
			statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (1, 'Stol', '2022-01-01 10:00:00', 100.0, 10, 5, 1, 1, 1)");
			statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (2, 'Maleri', '2022-02-01 11:00:00', 200.0, 20, 7, 2, 2, 2)");
			statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (3, 'Lyse brun, mahogany', '2024-12-24 22:38:21', 300.0, 30, 4, 1, 2, null)");
			statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (4, 'Gaming stol/pult kombo ting', '2021-01-13 12:14:13', 50.0, 85, 3, 6, 2, 2)");
			statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (4, 'Bilde av mamma', '2025-01-13 12:14:13', 74.38, 12, 7, 10, 1, 4)");
		}
		catch (SQLException e)
		{
			fail("An error occurred while checking the database tables: " + e.getMessage());
		}
	}
}
