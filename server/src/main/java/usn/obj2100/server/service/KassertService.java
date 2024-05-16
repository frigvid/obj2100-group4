package usn.obj2100.server.service;

import usn.obj2100.server.DatabaseConnectionManager;
import usn.obj2100.shared.model.Kassert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for handling the Kassert objects in the database.
 *
 * @created 2024-05-13
 */
public class KassertService
	implements IService<Kassert>
{
	private Connection connection;
	
	public KassertService()
	{
		connection = DatabaseConnectionManager.getInstance().getConnection();
	}
	
	/**
	 * Get a Kassert object from the database.
	 *
	 * @param id The ID of the Kassert object.
	 * @return The Kassert object.
	 */
	@Override
	public Kassert get(int id)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM kassert WHERE id = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next())
			{
				return new Kassert(
					resultSet.getInt("id"),
					resultSet.getDate("dato"),
					resultSet.getTimestamp("tid"),
					resultSet.getInt("begrunnelse")
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
	 * Get all Kassert objects from the database.
	 *
	 * @return A list of Kassert objects.
	 */
	@Override
	public List<Kassert> getAll()
	{
		List<Kassert> kassertList = new ArrayList<>();
		
		try
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM kassert");
			
			while (resultSet.next())
			{
				kassertList.add(new Kassert(
					resultSet.getInt("id"),
					resultSet.getDate("dato"),
					resultSet.getTimestamp("tid"),
					resultSet.getInt("begrunnelse")
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
	 * Create a Kassert object in the database.
	 * <p/>
	 * Note that if you create an object with an ID, and it is not
	 * available, the database sets an available one for it. So if you
	 * need to get the newly created object's ID, you just save the
	 * response to an Object of Kassert or a generic Object, and get
	 * the ID from there.
	 *
	 * @param kassert The Kassert object to create.
	 * @return The created Kassert object.
	 */
	@Override
	public Kassert create(Kassert kassert)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("INSERT INTO kassert (dato, tid, begrunnelse) VALUES (?, ?, ?)");
			statement.setDate(1, kassert.getDato());
			statement.setTimestamp(2, kassert.getTid());
			
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0)
			{
				throw new SQLException("Oppretting av kassert database objekt feilet, ingen rader påbirket.");
			}
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys())
			{
				if (generatedKeys.next())
				{
					kassert.setId(generatedKeys.getInt(1));
				}
				else
				{
					throw new SQLException("Oppretting av kassert database objekt feilet, ingen ID generert.");
				}
			}
			
			return kassert;
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
	 * Update a Kassert object in the database.
	 *
	 * @param kassert The Kassert object to update.
	 * @return True if the update was successful, false otherwise.
	 */
	@Override
	public boolean update(Kassert kassert)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement(
				"""
					UPDATE kassert SET
						dato = ?,
						tid = ?,
						begrunnelse = ?
					WHERE id = ?
				"""
			);
			
			statement.setDate(1, kassert.getDato());
			statement.setTimestamp(2, kassert.getTid());
			statement.setInt(3, kassert.getBegrunnelse());
			
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
	 * Delete a Kassert object from the database.
	 *
	 * @param kassert The Kassert object to delete.
	 * @return True if the deletion was successful, false otherwise.
	 */
	@Override
	public boolean delete(Kassert kassert)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("DELETE FROM kassert WHERE id = ?");
			statement.setInt(1, kassert.getId());
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
