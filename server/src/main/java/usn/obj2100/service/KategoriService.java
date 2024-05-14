package usn.obj2100.service;

import usn.obj2100.DatabaseConnectionManager;
import usn.obj2100.model.Kategori;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KategoriService
	implements IService<Kategori>
{
	private Connection connection;
	
	public KategoriService()
	{
		connection = DatabaseConnectionManager.getInstance().getConnection();
	}
	
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
