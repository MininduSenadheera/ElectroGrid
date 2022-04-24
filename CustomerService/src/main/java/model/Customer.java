package model;

import java.sql.*;

//import com.google.gson.JsonElement;
//For JSON
import com.google.gson.*;

//import bean.CustomerBean;
import util.DBConnection;

public class Customer {

	// =============A common method to connect to the DB===========
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			// add mysql details
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electroGrid", "root", "12345");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String readAllCustomers() {
		String output = "";
		try {
			Connection connection = connect();
			if (connection == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Customer Id</th><th>First Name</th>" + "<th>Last Name</th>"
					+ "<th>Nic</th>" + "<th>Phone Number</th>" + "<th>Email</th>" + "<th>Address</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from customer";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				
			}
			connection.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
}
