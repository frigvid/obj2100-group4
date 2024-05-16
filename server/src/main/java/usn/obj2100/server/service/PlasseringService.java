package usn.obj2100.server.service;

import usn.obj2100.server.DatabaseConnectionManager;
import usn.obj2100.shared.model.Plassering;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO: Implement check, and/or test, that verifies that you
//			can create Plassering objects without "etasje" and "rom."
/**
 * This class is responsible for handling the Plassering objects in the database.
 *
 * @created 2024-05-13
 */
public class PlasseringService
	implements IService<Plassering>
{
	private Connection connection;
	
	public PlasseringService ()
	{
		connection = DatabaseConnectionManager.getInstance().getConnection();
	}
	
	/**
	 * Get a Plassering object from the database.
	 *
	 * @param id The ID of the Plassering object.
	 * @return The Plassering object.
	 */
	@Override
	public Plassering get(int id)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM plassering WHERE id = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next())
			{
				return new Plassering(
					resultSet.getInt("id"),
					resultSet.getString("bygg"),
					resultSet.getInt("floy"),
					resultSet.getInt("etasje"),
					resultSet.getInt("rom")
				);
			}
		}
		catch (SQLException error)
		{
			error.printStackTrace(System.err);
		}
		finally
		{
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException error)
				{
					error.printStackTrace(System.err);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Get all Plassering objects from the database.
	 *
	 * @return A list of Plassering objects.
	 */
	@Override
	public List<Plassering> getAll()
	{
		List<Plassering> plasseringList = new ArrayList<>();
		
		try
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM plassering");
			
			while (resultSet.next())
			{
				plasseringList.add(new Plassering(
					resultSet.getInt("id"),
					resultSet.getString("bygg"),
					resultSet.getInt("floy"),
					resultSet.getInt("etasje"),
					resultSet.getInt("rom")
				));
			}
		}
		catch (SQLException error)
		{
			error.printStackTrace(System.err);
		}
		finally
		{
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException error)
				{
					error.printStackTrace(System.err);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Create a Plassering object in the database.
	 * <p/>
	 * Note that if you create an object with an ID, and it is not
	 * available, the database sets an available one for it. So if you
	 * need to get the newly created object's ID, you just save the
	 * response to an Object of Plassering or a generic Object, and get
	 * the ID from there.
	 *
	 * @param plassering The Plassering object to create.
	 * @return The created Plassering object.
	 */
	@Override
	public Plassering create(Plassering plassering)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("INSERT INTO plassering (bygg, floy, etasje, rom) VALUES (?, ?, ?, ?)");
			statement.setString(1, plassering.getBygg());
			statement.setInt(2, plassering.getFloy());
			statement.setInt(3, plassering.getEtasje());
			statement.setInt(4, plassering.getRom());
			
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0)
			{
				throw new SQLException("Oppretting av plassering database objekt feilet, ingen rader påvirket.");
			}
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys())
			{
				if (generatedKeys.next())
				{
					plassering.setId(generatedKeys.getInt(1));
				}
				else
				{
					throw new SQLException("Oppretting av plassering objekt feilet, ingen ID mottat.");
				}
			}
			
			return plassering;
		}
		catch (SQLException error)
		{
			error.printStackTrace(System.err);
			return null;
		}
		finally
		{
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException error)
				{
					error.printStackTrace(System.err);
				}
			}
		}
	}
	
	/**
	 * Update a Plassering object in the database.
	 *
	 * @param plassering The Plassering object to update.
	 * @return True if the update was successful, false otherwise.
	 */
	@Override
	public boolean update(Plassering plassering)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement(
				"""
					UPDATE plassering SET
						bygg = ?,
						floy = ?,
						etasje = ?,
						rom = ?
					WHERE id = ?
				"""
			);
			
			statement.setString(1, plassering.getBygg());
			statement.setInt(2, plassering.getFloy());
			statement.setInt(3, plassering.getEtasje());
			statement.setInt(4, plassering.getRom());
			statement.setInt(5, plassering.getId());
			
			statement.executeUpdate();
			
			return true;
		}
		catch (SQLException error)
		{
			error.printStackTrace(System.err);
			return false;
		}
		finally
		{
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException error)
				{
					error.printStackTrace(System.err);
				}
			}
		}
	}
	
	/**
	 * Delete a Plassering object from the database.
	 *
	 * @param plassering The Plassering object to delete.
	 * @return True if the deletion was successful, false otherwise.
	 */
	@Override
	public boolean delete(Plassering plassering)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("DELETE FROM plassering WHERE id = ?");
			statement.setInt(1, plassering.getId());
			statement.executeUpdate();
			
			return true;
		}
		catch (SQLException error)
		{
			error.printStackTrace(System.err);
			return false;
		}
		finally
		{
			if (connection != null)
			{
				try
				{
					connection.close();
				}
				catch (SQLException error)
				{
					error.printStackTrace(System.err);
				}
			}
		}
	}
}
