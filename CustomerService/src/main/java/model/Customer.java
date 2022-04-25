package model;

import java.sql.*;

//For JSON
import com.google.gson.*;



public class Customer {

	// =============A common method to connect to the DB===========
	private Connection connect() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			// add mysql details
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electroGrid", "root", "12345");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	
	
	
	
	
//********************* view all customer **************************

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

	// *************************get particular customer details by customer id*********************
	public String viewCustomer(String customerId) {
		String output = "";
		try {
			Connection connection = connect();
			if (connection == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>First Name</th><th>Last Name</th>" + "<th>NIC</th>" + "<th>Address</th>"
					+ "<th>Phone</th>" + "<th>Email</th>" + "<th>Update</th>";

			String query = "select * from customer where customerId= '" + customerId + "' ";
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
	
	
	
	public String readUserDetails(String userName,String password)
	 {
		 String output = "";
		 try
		 {
			 Connection connection = connect();
		 if (connection == null)
		 {
			 return "Error while connecting to the database for reading."; }
}
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr>"+
				 "<th>User ID</th>" +
				 "<th>User Name</th>" +
				 "<th>Email</th>" +
				 "<th>First Name</th><th>Last Name</th>" +
				 "<th>Card Number</th>" +
				 "<th>CVV</th>" +
				 "<th>Expiration Date</th>" +
				 "<th>Password</th></tr>";
		
		 String query = "select * from user where userName = '" + userName +"' AND password = '" + password + "'";
		 Statement stmt = connection.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
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
			 output +="<input name='userId' type='hidden' value='" + userId
			 + "'>" + "</form></td></tr>";
	 }
		 connection.close();
		 
		 // Complete the html table
		 output += "</table>";
	 }
	catch (Exception e)
	 {
		 output = "Error while reading Users.";
		 System.err.println(e.getMessage());
	 }
		 return output;
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 }

	// ********************** insert Customer ****************
	
	public String insertCustomer(String firstName, String lastName, String nic, int phoneNumber, String email,
			String address) {
		String output = "";
		try {
			Connection connection = connect();
			if (connection == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into customer(`customerId`,`firstName`,`lastName`,`nic`,`phone`,`email`,`address`)"
					+ " values (?, ?, ?, ?, ?,?,?)";
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			// binding values

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, firstName);
			preparedStmt.setString(3, lastName);
			preparedStmt.setString(4, nic);
			preparedStmt.setInt(5, phoneNumber);
			preparedStmt.setString(6, email);

			preparedStmt.setString(7, address);
			// execute the statement

			preparedStmt.execute();
			connection.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// ************************** delete customer *******************
	// ********************* xml method lab 6 **********************
	public String deletecustomer(String customerId) {
		String output = "";
		try {
			Connection connection = connect();
			if (connection == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from customer where customerId=?";
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(customerId));
			// execute the statement
			preparedStmt.execute();
			connection.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//********************* update customer ********************

	public String updatecustomer(String customerId, String firstName, String lastName, String nic, String phone,
			String email, String address) {
		String output = "";
		try {
			Connection connection = connect();
			if (connection == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE customer SET firstName=?,lastName=?,nic=?,phone=?,address=? WHERE customerId=?";
			PreparedStatement preparedStmt = connection.prepareStatement(query);

			// binding values
//  			 preparedStmt.setInt(1, customerId);
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setString(3, nic);
			preparedStmt.setInt(4, Integer.parseInt(phone));
			preparedStmt.setString(5, email);

			preparedStmt.setString(6, address);
			preparedStmt.setInt(6, Integer.parseInt(customerId));

			// execute the statement
			preparedStmt.execute();
			connection.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

//   

}
