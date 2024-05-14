package usn.obj2100.client.api;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import usn.obj2100.controller.InventarController;
import usn.obj2100.model.Inventar;
import usn.obj2100.client.utils.FakeClient;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("En inventar er")
@TestMethodOrder(OrderAnnotation.class)
public class TestInventar
{
	private FakeClient client;
	private InventarController controller;
	private Inventar newInventar;
	
	@BeforeEach
	@DisplayName("Oppsett for hver test.")
	public void setup()
	{
		try
		{
			client = new FakeClient();
			controller = new InventarController();
		}
		catch (Exception error)
		{
			error.printStackTrace(System.err);
		}
	}
	
	@Test
	@Order(1)
	@DisplayName("opprettet.")
	public void testCreateAndRetrieveInventar()
	{
		newInventar = new Inventar(
			1,
			"Inventar test objekt beskrivelse",
			LocalDateTime.now(),
			100.0,
			10,
			1,
			1,
			1,
			0
		);
		controller.createInventar(newInventar);
	}
	
	@Test
	@Order(2)
	@DisplayName("hentet fra databasen.")
	public void testRetrieveInventar()
	{
		Inventar retrievedInventar = controller.getInventarById(1);
		assertEquals(newInventar.getSKU(), retrievedInventar.getSKU());
		assertEquals(newInventar.getBeskrivelse(), retrievedInventar.getBeskrivelse());
	}
	
	/*
	private InventarController controller;
	
	@BeforeEach
	public void setup()
	{
		controller = new InventarController();
	}
	
	@Test
	@Order(1)
	@DisplayName("er opprettet og hentet fra databasen.")
	public void testCreateAndRetrieveInventar()
	{
		Inventar newInventar = new Inventar(
			1,
			"Inventar test objekt beskrivelse.",
			LocalDateTime.now(),
			100.0,
			10,
			1,
			1,
			1,
			0
		);
		
		System.out.println("Inventar: " + newInventar);
		
		controller.createInventar(newInventar);
		
		Inventar retrievedInventar = controller.getInventarById(1);
		assertEquals(newInventar.getSKU(), retrievedInventar.getSKU());
		assertEquals(newInventar.getBeskrivelse(), retrievedInventar.getBeskrivelse());
	}
	
	@Test
	@Order(2)
	@DisplayName("er oppdatert.")
	public void testUpdateInventar()
	{
		Inventar inventar = controller.getInventarById(1);
		inventar.setBeskrivelse("Oppdatert Inventar objekt.");
		controller.updateInventar(inventar);
		
		Inventar updatedInventar = controller.getInventarById(1);
		assertEquals("Oppdatert Inventar objekt.", updatedInventar.getBeskrivelse());
	}
	
	@Test
	@Order(3)
	@DisplayName("er slettet.")
	public void testDeleteInventar()
	{
		Inventar inventar = controller.getInventarById(1);
		controller.deleteInventar(inventar);
		
		Inventar deletedInventar = controller.getInventarById(1);
		assertNull(deletedInventar);
	}
	*/
}
