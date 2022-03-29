package persistence.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static String url ="jdbc:sqlite:C:\\Users\\Linkin\\eclipse-workspace\\ParqueTierraMedia\\TierraMedia.db";
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException e){
			throw new SQLException(e);
		}
		if (connection == null) {
			connection = DriverManager.getConnection(url);
		}
		return connection;
	}
}

