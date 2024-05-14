package usn.obj2100.service;

import usn.obj2100.DatabaseConnectionManager;
import usn.obj2100.model.Kassert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KassertService
	implements IService<Kassert>
{
	private Connection connection;
	
	public KassertService()
	{
		connection = DatabaseConnectionManager.getInstance().getConnection();
	}
	
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
