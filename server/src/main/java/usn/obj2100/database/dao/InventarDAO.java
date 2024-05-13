package usn.obj2100.database.dao;

import usn.obj2100.database.model.Inventar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InventarDAO
	implements IDao<Inventar>
{
	private final Connection connection;
	
	public InventarDAO(Connection connection)
	{
		this.connection = connection;
	}
	
	@Override
	public Inventar get(int id)
	{
		String query = "SELECT * FROM inventar WHERE sku = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(query))
		{
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next())
			{
				return extractFromResultSet(resultSet);
			}
		}
		catch (SQLException error)
		{
			throw new RuntimeException(error);
		}
		
		return null;
	}
	
	@Override
	public List<Inventar> getAll()
	{
		List<Inventar> inventoryItems = new ArrayList<>();
		
		String query = "SELECT * FROM inventar";
		try (PreparedStatement statement = connection.prepareStatement(query))
		{
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next())
			{
				inventoryItems.add(extractFromResultSet(resultSet));
			}
		}
		catch (SQLException error)
		{
			throw new RuntimeException(error);
		}
		return inventoryItems;
	}
	
	@Override
	public void save(Inventar inventar)
	{
		// TODO.
	}
	
	@Override
	public void update(Inventar inventar)
	{
		// TODO.
	}
	
	@Override
	public void delete(Inventar inventar)
	{
		// TODO.
	}
	
	private Inventar extractFromResultSet(ResultSet resultSet)
	{
		try
		{
			int sku = resultSet.getInt("sku");
			String beskrivelse = resultSet.getString("beskrivelse");
			Date innkjopsdato = resultSet.getDate("innkjopsdato");
			double innkjopspris = resultSet.getDouble("innkjopspris");
			int antall = resultSet.getInt("antall");
			int forventetLevetid = resultSet.getInt("forventetLevetid");
			int kategori = resultSet.getInt("kategori");
			int plassering = resultSet.getInt("plassering");
			int kassert = resultSet.getInt("kassert");
			
			return new Inventar(
				sku,
				beskrivelse,
				innkjopsdato,
				innkjopspris,
				antall,
				forventetLevetid,
				kategori,
				plassering,
				kassert
			);
		}
		catch (SQLException error)
		{
			throw new RuntimeException(error);
		}
	}
}
