package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Atraccion;
import persistence.commons.ConnectionProvider;

public class DAOAtraccion {

	public List<Atraccion> findAll() {
		List<Atraccion> atraccionesList = new ArrayList<Atraccion>();
		String sql = "SELECT * FROM Atraccion;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				atraccionesList.add(new Atraccion(result.getInt(1), result.getString(2), result.getInt(3),
						result.getDouble(4), result.getInt(5), result.getInt(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atraccionesList;
	}

	public Atraccion findById(int Id) {
		Atraccion atraccion = new Atraccion(0, "", 0, 0, 0, 0);
		String sql = "SELECT * FROM Atraccion WHERE Atraccion.id = ?";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, Id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				atraccion = new Atraccion(result.getInt(1), result.getString(2), result.getInt(3), result.getDouble(4),
						result.getInt(5), result.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atraccion;
	}
	
	public boolean restarCupo(Atraccion atraccion) {
		int rows = 0;
		String sql = "UPDATE Atraccion SET cupo = ? WHERE id = ?";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, atraccion.getCupo());
			statement.setInt(2, atraccion.getId());
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
	
	public boolean update(Atraccion atraccion) {
		int rows = 0;
		String sql = "UPDATE Atraccion SET nombre = ?, costo = ?, tiempo = ?, cupo = ? WHERE id = ?";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setInt(2, atraccion.getCosto());
			statement.setDouble(3, atraccion.getTiempo());
			statement.setInt(4, atraccion.getCupo());
			statement.setInt(5, atraccion.getId());
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
	
	public boolean insert(Atraccion atraccion) {
		int rows = 0;
		String sql = "INSERT INTO Atraccion (nombre, costo, tiempo, cupo, baja) VALUES (?, ?, ?, ?, ?)";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setInt(2, atraccion.getCosto());
			statement.setDouble(3, atraccion.getTiempo());
			statement.setInt(4, atraccion.getCupo());
			statement.setInt(5, 0);
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
	
	public boolean darBaja(int id) {
		int rows = 0;
		String sql = "UPDATE Atraccion SET baja = ? WHERE id = ?";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setInt(2, id);
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
}
