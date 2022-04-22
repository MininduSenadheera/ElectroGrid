package model;

import java.sql.*;

import bean.ConnectionBean;
import util.DBConnection;

public class ConnectionModel {

	//view connection by connectionID
	public String readConnections(String connectionID) {
		String output ="";
		
		try {
			Connection connection = DBConnection.connect();
			
			if (connection == null) {
				return "Error while connecting database for read the connection";
			}
			
			//prepare html table
			output = "<table border=\"1\">"
			+"<tr>"
					+ "<th> Connection ID </th>" + "<th> User ID"+"<th> User First Name" +"<th> User Last Name"
					+ "<th> Connection Type" +"<th> Units"
			+"</tr>";
			
			//sql statement for retrieve the connection
			String sql = "SELECT * "+
						"FROM Connection C, Customer U " + 
						"WHERE U.customerID= C.customerID and C.connectionID=?";

			//binding connectionID and executing query
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,Integer.parseInt(connectionID));
			ResultSet resultSet = preparedStatement.executeQuery();

			ConnectionBean connectionBean = new ConnectionBean();

			//looping through the rows
			while(resultSet.next()){
				connectionBean.setConnectionID(resultSet.getInt("connectionID"));
				connectionBean.setCustomerID(resultSet.getInt("customerID"));
				connectionBean.setFirstName(resultSet.getString("firstName"));
				connectionBean.setLastName(resultSet.getString("lastName"));
				connectionBean.setConnectionType(resultSet.getString("type"));
				connectionBean.setUnits(resultSet.getInt("units"));

				//add data to the table
				output += "<tr><td>" + connectionBean.getConnectionID() + "</td>";
				output += "<td>" + connectionBean.getCustomerID() + "</td>";
				output += "<td>" + connectionBean.getFirstName() + "</td>";
				output += "<td>" + connectionBean.getLastName() + "</td>";
				output += "<td>" + connectionBean.getConnectionType() + "</td>";
				output += "<td>" + connectionBean.getUnits() + "</td>";


			}

			connection.close();

			output += "</table>";

		} catch (Exception e){
			System.err.println(e.getMessage());
			output = "Error while viewing the connections by Connection ID";
		}
		return output;
		
	}
	
	
