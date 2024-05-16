package usn.obj2100.server.service;

import usn.obj2100.server.DatabaseConnectionManager;
import usn.obj2100.shared.model.Kategori;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for handling the Kategori objects in the database.
 *
 * @created 2024-05-13
 */
public class KategoriService
	implements IService<Kategori>
{
	private Connection connection;
	
	public KategoriService()
	{
		connection = DatabaseConnectionManager.getInstance().getConnection();
	}
	
	/**
	 * Get a Kategori object from the database.
	 *
	 * @param id The ID of the Kategori object.
	 * @return The Kategori object.
	 */
	@Override
	public Kategori get(int id)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM kategori WHERE id = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next())
			{
				return new Kategori(
					resultSet.getInt("id"),
					resultSet.getInt("type"),
					resultSet.getString("kategori")
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
	 * Get all Kategori objects from the database.
	 *
	 * @return A list of Kategori objects.
	 */
	@Override
	public List<Kategori> getAll()
	{
		List<Kategori> kategoriList = new ArrayList<>();
		
		try
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM kategori");
			
			while (resultSet.next())
			{
				kategoriList.add(new Kategori(
					resultSet.getInt("id"),
					resultSet.getInt("type"),
					resultSet.getString("kategori")
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
	 * Create a new Kategori object in the database.
	 * <p/>
	 * Note that if you create an object with an ID, and it is not
	 * available, the database sets an available one for it. So if you
	 * need to get the newly created object's ID, you just save the
	 * response to an Object of Kategori or a generic Object, and get
	 * the ID from there.
	 *
	 * @param kategori The Kategori object to create.
	 * @return The created Kategori object.
	 */
	@Override
	public Kategori create(Kategori kategori)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("INSERT INTO kategori (type, kategori) VALUES (?, ?)");
			statement.setInt(1, kategori.getType());
			statement.setString(2, kategori.getKategori());
			
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0)
			{
				throw new SQLException("Creating kategori failed, no rows affected.");
			}
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys())
			{
				if (generatedKeys.next())
				{
					kategori.setId(generatedKeys.getInt(1));
				}
				else
				{
					throw new SQLException("Creating kategori failed, no ID obtained.");
				}
			}
			
			return kategori;
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
	 * Update a Kategori object in the database.
	 *
	 * @param kategori The Kategori object to update.
	 * @return True if the update was successful, false otherwise.
	 */
	@Override
	public boolean update(Kategori kategori)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement(
				"""
					UPDATE kategori SET
						type = ?,
						kategori = ?
					WHERE id = ?
				"""
			);
			
			statement.setInt(1, kategori.getType());
			statement.setString(2, kategori.getKategori());
			statement.setInt(3, kategori.getId());
			
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
	 * Delete a Kategori object from the database.
	 *
	 * @param kategori The Kategori object to delete.
	 * @return True if the delete was successful, false otherwise.
	 */
	@Override
	public boolean delete(Kategori kategori)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("DELETE FROM kategori WHERE id = ?");
			statement.setInt(1, kategori.getId());
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
