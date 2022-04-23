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

}