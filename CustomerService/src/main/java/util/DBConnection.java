package util;

import java.sql.*;

public class DBConnection {
	// common method to connect to DB
	public static Connection connect() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DB connection details
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electroGrid", "root", "12345");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}
}
//jdbc:mysql://localhost:3306/fleet_management?createDatabaseIfNotExist=true&useSSL=true