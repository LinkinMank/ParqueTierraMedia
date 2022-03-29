package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Itinerario;
import persistence.commons.ConnectionProvider;

public class DAOItinerario {
	/*
	public boolean checkearComprasPrevias(int userId) {
		boolean contieneAlgo = false;
		String sql = "SELECT count(*) FROM Itinerario WHERE Itinerario.usuario_id = ?;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, userId);
			ResultSet result = statement.executeQuery();
			if(result.getInt(1) > 0) {
				contieneAlgo = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return contieneAlgo;
	}*/
	
	public Itinerario findByUserId(int userId) {
		Itinerario itinerario = new Itinerario(0, 0, null, null);
		String sql = "SELECT * FROM Itinerario WHERE Itinerario.usuario_id = ?;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, userId);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				itinerario = new Itinerario(result.getInt(1), result.getInt(2), result.getString(3), result.getString(4));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return itinerario;
	}
	
	public boolean insert(int userId, Itinerario itinerario) {
		int rows = 0;
		String sql = "INSERT INTO Itinerario (usuario_id, promocion_id, atraccion_id) VALUES (?, ?, ?);";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, userId);
			statement.setString(2, itinerario.getPromocionId());
			statement.setString(3, itinerario.getAtraccionId());
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
	
	public boolean updateAtrac(int userId, Itinerario itinerario) {
		int rows = 0;
		String sql = "UPDATE Itinerario SET atraccion_id = ? WHERE usuario_id = ?;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setString(1, itinerario.getAtraccionId());
			statement.setInt(2, userId);
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
	
	public boolean updatePromo(int userId, Itinerario itinerario) {
		int rows = 0;
		String sql = "UPDATE Itinerario SET promocion_id = ? WHERE usuario_id = ?;";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setString(1, itinerario.getPromocionId());
			statement.setInt(2, userId);
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
}
