package util;

import java.sql.*;

public class DBConnection {

	// common method to connect to DB
		public static Connection connect() {
			
			Connection connection = null;
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				//DB connection details
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid","root", "hotel*123");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return connection;
		}
}
