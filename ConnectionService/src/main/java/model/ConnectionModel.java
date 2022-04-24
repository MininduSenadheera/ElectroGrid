package model;

import java.sql.*;

import bean.ConnectionBean;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Request;
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
		
	//updating connection units
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

			output = "Used units fo this month :   " + Integer.toString(monthlyUnits);
		} catch (Exception e){
			output = "Error while updating the connection.";
			System.err.println(e.getMessage());
		}

		return output;
			
	}

	//update connection status
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
            int st =preparedStatement.executeUpdate();

			if(st>0){
				output = "Status updated successfully";
			}else{
				output = "Connection record not found with the corresponding ID";
			}

			connection.close();

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
			
			String query  = "SELECT * "+"FROM Bill b " + "WHERE b.connectionID=?";
			
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.setInt(1, connectionBean.getConnectionID());
			ResultSet resultSet = preparedStmt.executeQuery();
			// ConnectionBean connectionBean = new ConnectionBean();

			while(resultSet.next()){
				//connectionBean.setConnectionID(resultSet.getInt("connectionID"));
				GetDeleteServiceFromBill(resultSet.getInt("billID"));
			}

			String sql = "DELETE FROM Connection WHERE connectionID=?";

			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,connectionBean.getConnectionID());
			int st =preparedStatement.executeUpdate();

			if(st>0){
				output = "Connection record deleted successfully";
			}else{
				output = "Connection record not found with the corresponding ID";
			}

			connection.close();

			
		} catch(Exception e){
			System.err.println(e.getMessage());
			output = "Error while deleting connection record";
		}

		return output;
	}

	//creating a new record
	public String newConnection(ConnectionBean connectionBean) {
		
		String output ="";

		try {
			Connection connection = DBConnection.connect();

			if(connection==null){
				return "Error while connecting to the database";
			}

			String sql = "INSERT INTO Connection (`customerID`,`status`,`type`,`units`)" + " VALUES(?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,connectionBean.getCustomerID());
			preparedStatement.setString(2, connectionBean.getStatus());
			preparedStatement.setString(3,connectionBean.getConnectionType());
			preparedStatement.setInt(4, connectionBean.getUnits());

			preparedStatement.execute();
			connection.close();

			output = "Connection Record Inserted Successfully";
		} catch(Exception e){
			System.err.println(e.getMessage());
			output = "Error while inserting the connection";
		}

			return output;
	}

	//this method call delete method in bill   
	public String GetDeleteServiceFromBill(int billID) {

		try {
	
			MediaType JSONType = MediaType.get("application/json; charset=utf-8");
			OkHttpClient client = new OkHttpClient();
			RequestBody body = RequestBody.create("{ 'billID':'" + billID + "'}", JSONType);
			Request request = new Request.Builder().url("http://localhost:8080/BillService/Bill/").delete(body).build();
	
			try (okhttp3.Response response = client.newCall(request).execute()) {
	
				return response.body().string();
			}
	
		} catch (Exception e) {
	
			System.err.println(e.getMessage());
			return "Error while deleting bill related to connection";
		}
	
	}

	public String readUnits(String connectionID){
		String output = "";

		try {
			Connection connection = DBConnection.connect();
			
			if (connection == null) {
				return "Error while connecting database for get units";
			}
			
			String sql = "SELECT units " +
				"FROM Connection " + "WHERE connectionID=?";
				
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,Integer.parseInt(connectionID));
			ResultSet resultSet = preparedStatement.executeQuery();	

			ConnectionBean connectionBean = new ConnectionBean();

			while (resultSet.next()) {
				connectionBean.setUnits(resultSet.getInt("units")); 
				
			}

			output += "<p>" + connectionBean.getUnits() + "</p>";

		connection.close();

	} catch (Exception e){
		System.err.println(e.getMessage());
		output = "Error while viewing the units";
	}
	return output;

	}	
}