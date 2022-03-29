package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;
import persistence.commons.ConnectionProvider;

public class DAOUser {
	
	public User findByUsername(String username, String password) {
		User logUser = new User(0, "", "", 0, 0);
		String sql = "SELECT * FROM Usuario WHERE Usuario.nombre = ? AND Usuario.password = ?";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				logUser = new User(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getDouble(5));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return logUser;
	}
	
	public User findById(int id) {
		User loggedUser = new User(0, "", "", 0, 0);
		String sql = "SELECT * FROM Usuario WHERE Usuario.id = ?";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				loggedUser = new User(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getDouble(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loggedUser;
	}
	
	public boolean registrarComprar(User user) {
		int rows = 0;
		String sql = "UPDATE Usuario SET dinero = ?, tiempo = ? WHERE id = ?";
		try {
			Connection conexion = ConnectionProvider.getConnection();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setInt(1, user.getDinero());
			statement.setDouble(2, user.getTiempo());
			statement.setInt(3, user.getId());
			rows = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows > 0;
	}
}
