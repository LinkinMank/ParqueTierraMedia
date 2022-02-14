package persistence.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static String url ="jdbc:sqlite:C:\\Users\\Linkin\\eclipse-workspace\\ParqueTierraMedia\\TierraMedia.db";
	private static Connection conexion;
	
	public static Connection getConexion() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException e){
			throw new SQLException(e);
		}
		if (conexion == null) {
			conexion = DriverManager.getConnection(url);
		}
		return conexion;
	}
}

