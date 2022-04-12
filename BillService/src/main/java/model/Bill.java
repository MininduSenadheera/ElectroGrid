package model;

import java.sql.*;

import bean.BillBean;
import util.DBConnection;
public class Bill {

    // read bills by connection ID
    public String readConnectionBills(String connectionID) {
        String output = "";
         
        try {
            Connection connection = DBConnection.connect();

            if (connection == null) {
                return "Error while connecting database for reading bills by connection";
            }

            // Prepare the html table to be displayed
			output = "<table border=\"1\">" 
                        + "<tr>"
                            + "<th>Bill ID</th>" + "<th>Connection ID</th>" + "<th>Customer ID</th>"
                            + "<th>Customer Name</th>" + "<th>Issued Date</th>" + "<th>Due Date</th>"
                            + "<th>Units</th>" + "<th>Amount</th>" + "<th>Payment Status</th>"
                        + "</tr>";

            // sql statement to retrieve bills by connection ID
            String sql =    "SELECT * " +
                            "FROM Bill B, Connection C, Customer U " + 
                            "WHERE B.connectionID = C.connectionID and C.customerID = U.customerID and C.connectionID = ?";

            // binding connectionID and executing the query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(connectionID));
            ResultSet resultSet = preparedStatement.executeQuery();

            BillBean billBean = new BillBean();

            // looping through the rows
            while (resultSet.next()) {
                billBean.setBillID(resultSet.getInt("billID"));
                billBean.setConnectionID(resultSet.getInt("connectionID"));
                billBean.setCustomerID(resultSet.getInt("customerID"));
                billBean.setCustomerName(resultSet.getString("firstname") + " " + resultSet.getString("lastname"));
                billBean.setPaymentID(resultSet.getInt("paymentID"));
                billBean.setIssuedDate(resultSet.getString("issueDate"));
                billBean.setDueDate(resultSet.getString("dueDate"));
                billBean.setStatus(resultSet.getString("status"));
                billBean.setUnits(resultSet.getInt("units"));
                billBean.setAmount(resultSet.getDouble("amount"));

                // add the data to the html table
                output += "<tr><td>" + billBean.getBillID() + "</td>";
                output += "<td>" + billBean.getConnectionID() + "</td>";
                output += "<td>" + billBean.getCustomerID() + "</td>";
                output += "<td>" + billBean.getCustomerName() + "</td>";
                output += "<td>" + billBean.getIssuedDate() + "</td>";
                output += "<td>" + billBean.getDueDate() + "</td>";
                output += "<td>" + billBean.getUnits() + "</td>";
                output += "<td>" + billBean.getAmount() + "</td>";
                output += "<td>" + billBean.getStatus() + "</td></tr>";
            }
            
            connection.close();

            output += "</table>";
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            output = "Error while reading bills by connection ID";
        }

        return output;
    }
    
    // read bills by customerID
    public String readCustomerBills(String customerID) {
        String output = "";
            
        try {
            Connection connection = DBConnection.connect();

            if (connection == null) {
                return "Error while connecting database for reading bills by connection";
            }

            // Prepare the html table to be displayed
            output = "<table border=\"1\">" 
                        + "<tr>"
                            + "<th>Bill ID</th>" + "<th>Connection ID</th>" + "<th>Customer ID</th>"
                            + "<th>Customer Name</th>" + "<th>Issued Date</th>" + "<th>Due Date</th>"
                            + "<th>Units</th>" + "<th>Amount</th>" + "<th>Payment Status</th>"
                        + "</tr>";

            // sql statement to retrieve bills by connection ID
            String sql =    "SELECT * " +
                            "FROM Bill B, Connection C, Customer U " + 
                            "WHERE B.connectionID = C.connectionID and C.customerID = U.customerID and U.customerID = ?";

            // binding connectionID and executing the query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(customerID));
            ResultSet resultSet = preparedStatement.executeQuery();

            BillBean billBean = new BillBean();

            // looping through the rows
            while (resultSet.next()) {
                billBean.setBillID(resultSet.getInt("billID"));
                billBean.setConnectionID(resultSet.getInt("connectionID"));
                billBean.setCustomerID(resultSet.getInt("customerID"));
                billBean.setCustomerName(resultSet.getString("firstname") + " " + resultSet.getString("lastname"));
                billBean.setPaymentID(resultSet.getInt("paymentID"));
                billBean.setIssuedDate(resultSet.getString("issueDate"));
                billBean.setDueDate(resultSet.getString("dueDate"));
                billBean.setStatus(resultSet.getString("status"));
                billBean.setUnits(resultSet.getInt("units"));
                billBean.setAmount(resultSet.getDouble("amount"));

                // add the data to the html table
                output += "<tr><td>" + billBean.getBillID() + "</td>";
                output += "<td>" + billBean.getConnectionID() + "</td>";
                output += "<td>" + billBean.getCustomerID() + "</td>";
                output += "<td>" + billBean.getCustomerName() + "</td>";
                output += "<td>" + billBean.getIssuedDate() + "</td>";
                output += "<td>" + billBean.getDueDate() + "</td>";
                output += "<td>" + billBean.getUnits() + "</td>";
                output += "<td>" + billBean.getAmount() + "</td>";
                output += "<td>" + billBean.getStatus() + "</td></tr>";
            }
            
            connection.close();

            output += "</table>";
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            output = "Error while reading bills by customer ID";
        }

        return output;
    }

    // updating bill
    public String updateBill(BillBean billBean) {
		String output = ""; 

		try {
			Connection connection = DBConnection.connect(); 
			
			if (connection == null) {
				return "Error while connecting to the database for updating bill."; 
			}

            String sql = " UPDATE Bill SET paymentID = ?, status = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, billBean.getPaymentID());
            preparedStatement.setString(2, "Paid");
            preparedStatement.execute();

			connection.close();
			
			output = "Bill updated successfully"; 
		} catch (Exception e) {
			output = "Error while updating the bill."; 
			System.err.println(e.getMessage());
		}
		return output;
	}

    // deleting bill
    public String deleteBill(String billID) {
        String output = "";

        try {
            Connection connection = DBConnection.connect();

            if (connection == null) {
                return "Error while connecting database for deleting bill";
            }

            String sql = "DELETE FROM Bill WHERE billID = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(billID));
            preparedStatement.execute();

            connection.close();

            output = "Bill deleted successfully";
        } catch (Exception e) {
            System.err.println(e.getMessage());
            output = "Error while deleting bill";
        }
        return output;
    }
}
