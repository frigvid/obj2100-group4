package usn.obj2100.server.service;

import usn.obj2100.server.DatabaseConnectionManager;
import usn.obj2100.shared.model.KategoriType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KategoriTypeService
	implements IService<KategoriType>
{
	private Connection connection;
	
	public KategoriTypeService()
	{
		connection = DatabaseConnectionManager.getInstance().getConnection();
	}
	
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
