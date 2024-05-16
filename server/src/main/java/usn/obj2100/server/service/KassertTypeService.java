package usn.obj2100.server.service;

import usn.obj2100.server.DatabaseConnectionManager;
import usn.obj2100.shared.model.KassertType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for handling the KassertType objects in the database.
 *
 * @created 2024-05-13
 */
public class KassertTypeService
	implements IService<KassertType>
{
	private Connection connection;
	
	public KassertTypeService()
	{
		connection = DatabaseConnectionManager.getInstance().getConnection();
	}
	
	/**
	 * Get a KassertType object from the database.
	 *
	 * @param id The ID of the KassertType object.
	 * @return The KassertType object.
	 */
	@Override
	public KassertType get(int id)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM kassertType WHERE id = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next())
			{
				return new KassertType(
					resultSet.getInt("id"),
					resultSet.getString("begrunnelse")
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
	 * Get all KassertType objects from the database.
	 *
	 * @return A list of KassertType objects.
	 */
	@Override
	public List<KassertType> getAll()
	{
		List<KassertType> kassertTypeList = new ArrayList<>();
		
		try
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM kassertType");
			
			while (resultSet.next())
			{
				kassertTypeList.add(new KassertType(
					resultSet.getInt("id"),
					resultSet.getString("begrunnelse")
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
	 * Create a KassertType object in the database.
	 * <p/>
	 * Note that if you create an object with an ID, and it is not
	 * available, the database sets an available one for it. So if you
	 * need to get the newly created object's ID, you just save the
	 * response to an Object of KassertType or a generic Object, and get
	 * the ID from there.
	 *
	 * @param kassertType The KassertType object to create.
	 * @return The KassertType object.
	 */
	@Override
	public KassertType create(KassertType kassertType)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("INSERT INTO kassertType (begrunnelse) VALUES (?)");
			statement.setString(1, kassertType.getBegrunnelse());
			
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0)
			{
				throw new SQLException("Oppretting av kassert type database objekt feilet, ingen rader påvirket.");
			}
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys())
			{
				if (generatedKeys.next())
				{
					kassertType.setId(generatedKeys.getInt(1));
				}
				else
				{
					throw new SQLException("Oppretting av kassert type database objekt feilet, ingen ID generert.");
				}
			}
			
			return kassertType;
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
	 * Update a KassertType object in the database.
	 *
	 * @param kassertType The KassertType object to update.
	 * @return True if the update was successful, false otherwise.
	 */
	@Override
	public boolean update(KassertType kassertType)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement(
				"""
					UPDATE kassertType SET
						begrunnelse = ?
					WHERE id = ?
				"""
			);
			
			statement.setString(1, kassertType.getBegrunnelse());
			
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
	 * Delete a KassertType object from the database.
	 *
	 * @param kassertType The KassertType object to delete.
	 * @return True if the deletion was successful, false otherwise.
	 */
	@Override
	public boolean delete(KassertType kassertType)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("DELETE FROM kassertType WHERE id = ?");
			statement.setInt(1, kassertType.getId());
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
