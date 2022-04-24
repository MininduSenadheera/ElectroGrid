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
				int customerId = rs.getInt("customerId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String nic = rs.getString("nic");
				int phoneNumber = rs.getInt("phone");
				String email = rs.getString("email");
				String address = rs.getString("address");
				// Add into the html table
				output += "<tr><td>" + customerId + "</td>";
				output += "<td>" + firstName + "</td>";
				output += "<td>" + lastName + "</td>";
				output += "<td>" + nic + "</td>";
				output += "<td>" + phoneNumber + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + address + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='Customers.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + customerId + "'>" + "</form></td></tr>";
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
	 //get particular customer details
  	public String viewCustomer(String customerId) {
  		String output = "";
  		try {
  			Connection connection = connect();
  			if (connection == null) {
  				return "Error while connecting to the database for reading.";
  			}
  			// Prepare the html table to be displayed
  			output = "<table border='1'><tr><th>First Name</th><th>Last Name</th>" + "<th>NIC</th>"
  					+ "<th>Address</th>" + "<th>Phone</th>"+ "<th>Email</th>"+ "<th>Update</th>";

  			String query = "select * from customer where customerId= '" + customerId +"' ";
  			Statement stmt = connection.createStatement();
  			ResultSet rs = stmt.executeQuery(query);
  			
  			// iterate through the rows in the result set
  			while (rs.next()) {
  				String userid = Integer.toString(rs.getInt("customerId"));
  				String firstName = rs.getString("firstName");
      			String lastName = rs.getString("lastName");
  				String nic = rs.getString("nic");
  				String phone = Integer.toString(rs.getInt("phone"));
  				String email = rs.getString("email");
  				String address = rs.getString("address");
  				
  				
  			
  				// Add into the html table
  				output += "<tr><td>" + firstName + "</td>";
  				output += "<td>" + lastName + "</td>";
  				output += "<td>" + nic + "</td>";
  				output += "<td>" + phone + "</td>";
  				output += "<td>" + email + "</td>";
  				output += "<td>" + address + "</td>";
  				
  				
  				// buttons
  				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>";
  			
  			}
  			connection.close();
  			// Complete the html table
  			output += "</table>";
  		} catch (Exception e) {
  			output = "Error while reading the customer details.";
  			System.err.println(e.getMessage());
  		}
  		return output;
  	}
	
	
	
	
	
	
	
	
	
	
}
