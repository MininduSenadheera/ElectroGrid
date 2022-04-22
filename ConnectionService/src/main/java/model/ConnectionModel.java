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
	
	
	//updating connection 

	public String updateConnectionUnits(ConnectionBean connectionBean){

		String output = "";

		try {
			Connection connection =DBConnection.connect();

			if(connection==null){
				return "Error while connecting to database";
			}

			String query = "SELECT units FROM Connection WHERE connectionID=?";

			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, connectionBean.getConnectionID());
            ResultSet resultSet = preparedStmt.executeQuery();

			int oldUnits=0;

			if(resultSet.next()){
				oldUnits = resultSet.getInt("units");
				
			}

			int monthlyUnits = connectionBean.getUnits() - oldUnits;

			String sql = "UPDATE Connection SET  units = ?  WHERE connectionID=?";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
            preparedStatement.setInt(1, connectionBean.getUnits());
			preparedStatement.setInt(2, connectionBean.getConnectionID());
            preparedStatement.executeUpdate();

			connection.close();

			output = Integer.toString(monthlyUnits);
		} catch (Exception e){
			output = "Error while updating the connection.";
			System.err.println(e.getMessage());
		}

		return output;
			
	}

	public String updateConnectionStatus(ConnectionBean connectionBean){

		String output = "";

		try {
			Connection connection =DBConnection.connect();

			if(connection==null){
				return "Error while connecting to database";
			}

		String sql = "UPDATE Connection SET  status = ?  WHERE connectionID=?";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, connectionBean.getStatus());
        
			preparedStatement.setInt(2, connectionBean.getConnectionID());
            preparedStatement.execute();

			connection.close();

			output = "Status updated successfully";
		} catch (Exception e){
			output = "Error while updating the connection.";
			System.err.println(e.getMessage());
		}

		return output;
			
	}

	//deleting the connection record
	public String deleteConnection(ConnectionBean connectionBean){
		String output = "";

		try {
			Connection connection = DBConnection.connect();

			if (connection == null){
				return "Error while connecting database for deleting the connection record";
			}
			
			String sql = "DELETE FROM Connection WHERE connectionID=?";

			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,connectionBean.getConnectionID());
			preparedStatement.execute();

			connection.close();

			output = "Connection record deleted successfully";
		} catch(Exception e){
			System.err.println(e.getMessage());
			output = "Error while deleting connection record";
		}

		return output;
	}

	public String newConnection(ConnectionBean connectionBean) {
