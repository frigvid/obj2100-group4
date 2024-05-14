package usn.obj2100.server;

import org.junit.jupiter.api.*;
import usn.obj2100.DatabaseConnectionManager;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A test class for the example data in the database.
 * <p/>
 * Note that this test does <i>not</i> remove the added example data.
 * This is meant to be run once, for testing purposes, and not to be run again.
 *
 * @created 2024-04-14
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
			statement.execute("INSERT INTO kategoriType (id, type) VALUES (1, 'mobler')");
			statement.execute("INSERT INTO kategoriType (id, type) VALUES (2, 'utsmykning')");
			statement.execute("INSERT INTO kategoriType (id, type) VALUES (3, 'teknisk')");
			
			/* Kategori. */
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (1, 1, 'Stoler')");
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (2, 2, 'Malerier')");
			statement.execute("INSERT INTO kategori (id, type, kategori) VALUES (3, 3, 'Datamaskiner')");
			
			/* KassertType. */
			statement.execute("INSERT INTO kassertType (id, begrunnelse) VALUES (1, 'solgt')");
			statement.execute("INSERT INTO kassertType (id, begrunnelse) VALUES (2, 'kassert')");
			statement.execute("INSERT INTO kassertType (id, begrunnelse) VALUES (3, 'på lager')");
			statement.execute("INSERT INTO kassertType (id, begrunnelse) VALUES (4, 'annet')");
			
			/* Kassert. */
			statement.execute("INSERT INTO kassert (id, dato, tid, begrunnelse) VALUES (1, '2022-01-01', '10:00:00', 1)");
			statement.execute("INSERT INTO kassert (id, dato, tid, begrunnelse) VALUES (2, '2022-02-01', '11:00:00', 2)");
			
			/* Finally, Inventar data. */
			statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (1, 'Stol', '2022-01-01 10:00:00', 100.0, 10, 5, 1, 1, 1)");
			statement.execute("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (2, 'Maleri', '2022-02-01 11:00:00', 200.0, 20, 10, 2, 2, 2)");
		}
		catch (SQLException e)
		{
			fail("An error occurred while checking the database tables: " + e.getMessage());
		}
	}
}
