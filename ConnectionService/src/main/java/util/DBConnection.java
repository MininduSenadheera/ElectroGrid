package util;

import java.sql.*;

public class DBConnection {
	   // common method to connect to DB
	public static Connection connect() {
		Connection connection = null;
			
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//DB connection details
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ElectroGrid","root","");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return connection;
	}
}

