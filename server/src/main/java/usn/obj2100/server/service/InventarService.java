package usn.obj2100.server.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import usn.obj2100.server.DatabaseConnectionManager;
import usn.obj2100.shared.model.Inventar;
import usn.obj2100.server.controller.InventarController;

/**
 * Service for the Inventar model.
 * <p/>
 * Do not use this directly.
 *
 * @version 0.1
 * @see InventarController for usage of this service.
 */
public class InventarService
	implements IService<Inventar>
{
	private Connection connection;
	
	public InventarService()
	{
		connection = DatabaseConnectionManager.getInstance().getConnection();
	}
	
	/**
	 * Get an inventar object by its ID.
	 *
	 * @param id The ID of the inventar object.
	 * @return The inventar object.
	 */
	@Override
	public Inventar get(int id)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM inventar WHERE sku = ?");
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next())
			{
				return new Inventar(
					resultSet.getInt("sku"),
					resultSet.getString("beskrivelse"),
					resultSet.getTimestamp("innkjopsdato").toLocalDateTime(),
					resultSet.getDouble("innkjopspris"),
					resultSet.getInt("antall"),
					resultSet.getShort("forventetLevetid"),
					resultSet.getInt("kategori"),
					resultSet.getInt("plassering"),
					resultSet.getInt("kassert")
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
	 * Get all inventar objects.
	 *
	 * @return A list of all inventar objects.
	 */
	@Override
	public List<Inventar> getAll()
	{
		List<Inventar> inventarList = new ArrayList<>();

		try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
			  Statement stmt = conn.createStatement();
			  ResultSet rs = stmt.executeQuery("select * from inventar")) {


			while (rs.next())
			{
				inventarList.add(new Inventar(
					rs.getInt("sku"),
					rs.getString("beskrivelse"),
					rs.getTimestamp("innkjopsdato").toLocalDateTime(),
					rs.getDouble("innkjopspris"),
					rs.getInt("antall"),
					rs.getShort("forventetLevetid"),
					rs.getInt("kategori"),
					(
						rs.getInt("plassering") == 0
							? 0
							: rs.getInt("plassering")
					),
					rs.getInt("kassert")
				));
			}
			
			return inventarList;
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
	 * Create a new Inventar object.
	 * <p/>
	 * Note that if you create an object with an ID, and it is not
	 * available, the database sets an available one for it. So if you
	 * need to get the newly created object's ID, you just save the
	 * response to an Object of Inventar or a generic Object, and get
	 * the ID from there.
	 *
	 * @param inventar The inventar object to create.
	 * @return Inventar object if the inventar object was created, null otherwise.
	 */
	@Override
	public Inventar create(Inventar inventar)
	{
		try
		{

			PreparedStatement statement = connection.prepareStatement("INSERT INTO inventar (sku, beskrivelse, innkjopsdato, innkjopspris, antall, forventetLevetid, kategori, plassering, kassert) VALUES (?,?,?,?,?,?,?,?,?)");
			statement.setInt(1, inventar.getSKU());
			statement.setString(2, inventar.getBeskrivelse());
			statement.setTimestamp(3, Timestamp.valueOf(inventar.getInnkjopsdato()));
			statement.setDouble(4, inventar.getInnkjopspris());
			statement.setInt(5, inventar.getAntall());
			statement.setInt(6, inventar.getForventetLevetid());
			statement.setInt(7, inventar.getKategori());
			statement.setInt(8, inventar.getPlassering());
			statement.setInt(9, inventar.getKassert());
			
			
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows == 0)
			{
				throw new SQLException("Oppretting av plassering database objekt feilet, ingen rader påvirket.");
			}
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys())
			{
				if (generatedKeys.next())
				{
					inventar.setSKU(generatedKeys.getInt(1));
				}
				else
				{
					throw new SQLException("Oppretting av plassering objekt feilet, ingen ID mottat.");
				}
			}
			
			return inventar;
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
	 * Update an inventar object.
	 *
	 * @param inventar The inventar object to update.
	 * @return True if the inventar object was updated, false otherwise.
	 */
	@Override
	public boolean update(Inventar inventar)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement(
				"""
					UPDATE inventar SET
						beskrivelse = ?,
						innkjopsdato = ?,
						innkjopspris = ?,
						antall = ?,
						forventetLevetid = ?,
						kategori = ?,
						plassering = ?,
						kassert = ?
					WHERE sku = ?
				"""
			);
			
			statement.setString(1, inventar.getBeskrivelse());
			statement.setTimestamp(2, Timestamp.valueOf(inventar.getInnkjopsdato()));
			statement.setDouble(3, inventar.getInnkjopspris());
			statement.setInt(4, inventar.getAntall());
			statement.setInt(5, inventar.getForventetLevetid());
			statement.setInt(6, inventar.getKategori());
			statement.setInt(7, inventar.getPlassering());
			statement.setInt(8, inventar.getKassert());
			statement.setInt(9, inventar.getSKU());
			
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
	 * Delete an inventar object.
	 *
	 * @param inventar The inventar object to delete.
	 * @return True if the inventar object was deleted, false otherwise.
	 */
	@Override
	public boolean delete(Inventar inventar)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("DELETE FROM inventar WHERE sku = ?");
			statement.setInt(1, inventar.getSKU());
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
