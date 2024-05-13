package usn.obj2100.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import usn.obj2100.DatabaseConnectionManager;
import usn.obj2100.model.Inventar;

public class InventarService
	implements IService<Inventar>
{
	private Connection connection;
	
	public InventarService()
	{
		connection = DatabaseConnectionManager.getInstance().getConnection();
	}
	
	@Override
	public Inventar get(int id)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM inventar WHERE id = ?");
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
		
		return null;
	}
	
	@Override
	public List<Inventar> getAll()
	{
		List<Inventar> inventarList = new ArrayList<>();
		
		try
		{
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM inventar");
			
			while (resultSet.next())
			{
				inventarList.add(new Inventar(
					resultSet.getInt("sku"),
					resultSet.getString("beskrivelse"),
					resultSet.getTimestamp("innkjopsdato").toLocalDateTime(),
					resultSet.getDouble("innkjopspris"),
					resultSet.getInt("antall"),
					resultSet.getShort("forventetLevetid"),
					resultSet.getInt("kategori"),
					resultSet.getInt("plassering"),
					resultSet.getInt("kassert")
				));
			}
		}
		catch (SQLException error)
		{
			error.printStackTrace(System.err);
		}
		
		return null;
	}
	
	@Override
	public void create(Inventar inventar)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement(
				"""
					  INSERT INTO inventar (
						sku,
						beskrivelse,
						innkjopsdato,
						innkjopspris,
						antall,
						forventetLevetid,
						kategori,
						plassering,
						kassert
					) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
				"""
			);
		}
		catch (SQLException error)
		{
			error.printStackTrace(System.err);
		}
	}
	
	@Override
	public void update(Inventar inventar)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement(
				"""
					UPDATE inventar SET
						sku = ?,
						beskrivelse = ?,
						innkjopsdato = ?,
						innkjopspris = ?,
						antall = ?,
						forventetLevetid = ?,
						kategori = ?,
						plassering = ?,
						kassert = ?
					WHERE id = ?
				"""
			);
			
			statement.setInt(1, inventar.getSKU());
			statement.setString(2, inventar.getBeskrivelse());
			statement.setTimestamp(3, Timestamp.valueOf(inventar.getInnkjopsdato()));
			statement.setDouble(4, inventar.getInnkjopspris());
			statement.setInt(5, inventar.getAntall());
			statement.setInt(6, inventar.getForventetLevetid());
			statement.setInt(7, inventar.getKategori());
			statement.setInt(8, inventar.getPlassering());
			statement.setInt(9, inventar.getKassert());
			
			statement.executeUpdate();
		}
		catch (SQLException error)
		{
			error.printStackTrace(System.err);
		}
	}
	
	@Override
	public void delete(Inventar inventar)
	{
		try
		{
			PreparedStatement statement = connection.prepareStatement("DELETE FROM inventar WHERE id = ?");
			statement.setInt(1, inventar.getSKU());
			statement.executeUpdate();
		}
		catch (SQLException error)
		{
			error.printStackTrace(System.err);
		}
	}
}
