package usn.obj2100.client.api;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import usn.obj2100.client.utils.FakeClient;

@DisplayName("En inventar er")
@TestMethodOrder(OrderAnnotation.class)
public class InventarTest
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
			error.printStackTrace(System.err);
		}
	}
	
	@Test
	@Order(1)
	@DisplayName("opprettet inventar element.")
	public void createInventar()
	{
	}
}
