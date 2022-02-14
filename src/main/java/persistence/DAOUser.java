package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;
import persistence.commons.ConnectionProvider;

public class DAOUser {
	
	public User findByUsername(String username) {
		User logUser = new User(0, "", 0, 0);
		String sql = "SELECT * FROM Usuario WHERE Usuario.nombre = ?";
		try {
			Connection conexion = ConnectionProvider.getConexion();
			PreparedStatement statement = conexion.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				logUser = new User(result.getInt(1), result.getString(2), result.getInt(3), result.getDouble(4));
			}
			System.out.println(logUser);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return logUser;
	};
}
