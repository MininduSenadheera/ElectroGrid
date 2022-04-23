package model;

import java.sql.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import bean.PaymentBean;
import util.DBConnection;

public class Payment {
	
	//Insert payment
	public String newPayment(PaymentBean payBean) {

	        try {
	            Connection connection = DBConnection.connect();

	            if (connection == null) {
	                return ("Error while connecting database for inserting payment");
	                               
	            }

	           String[] paymentId = { "paymentID" };
	            String sql =    " INSERT INTO payment (`customerID`,`paymentDateTime`,`amount`,`type`)" + 
	                            " values (?,  CURRENT_TIMESTAMP(), ?, ?)";

	            PreparedStatement preparedStatement = connection.prepareStatement(sql,paymentId);
				
				preparedStatement.setInt(1, payBean.getCustomerID());
				preparedStatement.setFloat(2,(float) payBean.getAmount());
	            preparedStatement.setString(3, payBean.getType());
	            preparedStatement.execute();

	            ResultSet rs = preparedStatement.getGeneratedKeys();
	             int gk=0;
					
	                if (rs.next()) {
	                	
	                	gk=rs.getInt(1);
	                	payBean.setPaymentID(gk);
	                    
	                }
	//read all payment details
		 public String readPaymentDetails() {
			
			String output = "";
						
			try {
						
				Connection con = DBConnection.connect();
							
				if(con == null) 
					return "Error while connecting to the database for reading.";
							
				//Preparing the HTML table, which is to be displayed
				output = "<table border = '1'>"
						+ "<tr><th>Payment ID </th>"
						+ "<th>Customer ID</th>"
						+ "<th>payment Date/Time</th>"
						+ "<th>Amount</th>"
						+ "<th>Type</th><tr>";
					
				String query = "SELECT * FROM payment";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
							
				//Iterate through the rows in the result set
				while(rs.next()) {
					Integer paymentID = rs.getInt("paymentID");	
					Integer customerID = rs.getInt("customerID");
					Timestamp paymentDateTime = rs.getTimestamp("paymentDateTime");
					float amount = rs.getFloat("amount");
					String type = rs.getString("type");
					
								
					// Add into the HTML table
					output += "<tr><td>" + paymentID + "</td>";
					output += "<td>" + customerID + "</td>";
					output += "<td>" + paymentDateTime + "</td>";
					output += "<td>" + amount + "</td>";
					output += "<td>" + type + "</td>";
					
				}	
								
			con.close();
							
			//Completing the HTML table
			output += "</table>";					
							
			} catch (SQLException e) {
							
				output += "Error while reading the payment details.";
				System.err.println(e.getMessage());
			}		
						
			return output ;		
		}
	 
	 

}