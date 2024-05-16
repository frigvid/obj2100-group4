package usn.obj2100.server.service;

import usn.obj2100.server.DatabaseConnectionManager;
import usn.obj2100.shared.model.KategoriType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for handling the KategoriType objects in the database.
 *
 * @created 2024-05-13
 */
public class KategoriTypeService
	implements IService<KategoriType>
{
	private Connection connection;
	
	public KategoriTypeService()
	{
		connection = DatabaseConnectionManager.getInstance().getConnection();
	}
	
	/**
	 * Get a KategoriType object from the database.
	 *
	 * @param id The ID of the KategoriType object.
	 * @return The KategoriType object.
	 */
	@Override
	public KategoriType get(int id)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM kategoriType WHERE id = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next())
			{
				return new KategoriType(
					resultSet.getInt("id"),
					resultSet.getString("type")
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
	 * Get all KategoriType objects from the database.
	 *
	 * @return A list of KategoriType objects.
	 */
	@Override
	public List<KategoriType> getAll()
	{
		List<KategoriType> kategoriTypeList = new ArrayList<>();
		
		try
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM kategoriType");
			
			while (resultSet.next())
			{
				kategoriTypeList.add(new KategoriType(
					resultSet.getInt("id"),
					resultSet.getString("type")
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
	 * Create a KategoriType object in the database.
	 * <p/>
	 * Note that if you create an object with an ID, and it is not
	 * available, the database sets an available one for it. So if you
	 * need to get the newly created object's ID, you just save the
	 * response to an Object of KategoriType or a generic Object, and get
	 * the ID from there.
	 *
	 * @param kategoriType The KategoriType object to create.
	 * @return The created KategoriType object.
	 */
	@Override
	public KategoriType create(KategoriType kategoriType)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("INSERT INTO kategoriType (type) VALUES (?)");
			statement.setString(1, kategoriType.getType());
			
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0)
			{
				throw new SQLException("Oppretting av database objekt kategoriType feilet, ingen rader påvirket.");
			}
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys())
			{
				if (generatedKeys.next())
				{
					kategoriType.setId(generatedKeys.getInt(1));
				}
				else
				{
					throw new SQLException("Oppretting av database objekt kategoriType feilet, ingen ID generert.");
				}
			}
			
			return kategoriType;
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
	 * Update a KategoriType object in the database.
	 *
	 * @param kategoriType The KategoriType object to update.
	 * @return True if the update was successful, false otherwise.
	 */
	@Override
	public boolean update(KategoriType kategoriType)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement(
				"""
					UPDATE kategoriType SET
						type = ?
					WHERE id = ?
				"""
			);
			
			statement.setString(1, kategoriType.getType());
			
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
	 * Delete a KategoriType object from the database.
	 *
	 * @param kategoriType The KategoriType object to delete.
	 * @return True if the deletion was successful, false otherwise.
	 */
	@Override
	public boolean delete(KategoriType kategoriType)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("DELETE FROM kategoriType WHERE id = ?");
			statement.setInt(1, kategoriType.getId());
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
