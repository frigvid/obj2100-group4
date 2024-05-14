package usn.obj2100.service;

import usn.obj2100.DatabaseConnectionManager;
import usn.obj2100.model.Plassering;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO: Implement check, and/or test, that verifies that you
//			can create Plassering objects without "etasje" and "rom."

public class PlasseringService
	implements IService<Plassering>
{
	private Connection connection;
	
	public PlasseringService ()
	{
		connection = DatabaseConnectionManager.getInstance().getConnection();
	}
	
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
	
	@Override
	public boolean create(Plassering plassering)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("INSERT INTO plassering (bygg, floy, etasje, rom) VALUES (?, ?, ?, ?)");
			statement.setString(1, plassering.getBygg());
			statement.setInt(2, plassering.getFloy());
			statement.setInt(3, plassering.getEtasje());
			statement.setInt(4, plassering.getRom());
			
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
