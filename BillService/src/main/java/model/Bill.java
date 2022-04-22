package model;

import java.sql.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Request;

import bean.BillBean;
import util.DBConnection;

public class Bill {

    // read bills by bill ID
    public Response readBill(String billID) {
        BillBean billBean = new BillBean();
        billBean.setBillID(Integer.parseInt(billID));

        try {
            Connection connection = DBConnection.connect();

            if (connection == null) {
                return Response.status(Status.INTERNAL_SERVER_ERROR)
                                .entity("Error while connecting database for reading bill")
                                .build();
            }

            // sql statement to retrieve bill
            String sql =    "SELECT * " +
                            "FROM Bill B, Connection C, Customer U " + 
                            "WHERE B.connectionID = C.connectionID and C.customerID = U.customerID and B.billID = ?";

            // binding connectionID and executing the query
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, billBean.getBillID());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
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
            } else {
            	return Response.status(Status.NOT_FOUND).entity("No bills found with the corresponding ID").build();
            }
            
            
            connection.close();

            return Response.status(Status.OK).entity(billBean.convertObjectToJsonString(billBean)).build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

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

    // issuing a new bill
    public Response newBill(BillBean billBean) {

        try {
            Connection connection = DBConnection.connect();

            if (connection == null) {
                return Response.status(Status.INTERNAL_SERVER_ERROR)
                                .entity("Error while connecting database for inserting bill")
                                .build();
            }

            // get units difference
            String d_units = GetMonthlyUnitsFromConnectionService(billBean);
            int diff_units = Integer.parseInt(d_units);
            billBean.calculateAmount(diff_units);

            String sql =    " INSERT INTO Bill (`connectionID`,`issueDate`,`dueDate`,`units`,`amount`,`status`)" + 
                            " values (?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY), ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, billBean.getConnectionID());
			preparedStatement.setInt(2, diff_units);
            preparedStatement.setDouble(3, billBean.getAmount());
            preparedStatement.setString(4, "Pending");
            preparedStatement.execute();

            connection.close();
            
            return Response.status(Status.CREATED).entity("Bill issued successfully").build();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    // updating bill
    public Response updateBill(BillBean billBean) {

		try {
			Connection connection = DBConnection.connect(); 
			
			if (connection == null) {
				return Response.status(Status.INTERNAL_SERVER_ERROR)
                                .entity("Error while connecting database for updating bill")
                                .build();
			}

            String sql = "UPDATE Bill SET paymentID = ?, status = ? WHERE billID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, billBean.getPaymentID());
            preparedStatement.setString(2, "Paid");
            preparedStatement.setInt(3, billBean.getBillID());
            int status = preparedStatement.executeUpdate();

			connection.close();

            if(status > 0) {
                return Response.status(Status.OK).entity("Bill updated successfully").build(); 
            } else {
                return Response.status(Status.NOT_FOUND).entity("No bills found with the corresponding ID").build();
            }
			
		} catch (SQLException se) {
            System.err.println(se.getMessage());
            return Response.status(Status.NOT_MODIFIED).entity(se.getMessage()).build(); 
        } catch (Exception e) {
			System.err.println(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build(); 
		}
	}

    // deleting bill
    public Response deleteBill(BillBean billBean) {

        try {
            Connection connection = DBConnection.connect();

            if (connection == null) {
                return Response.status(Status.INTERNAL_SERVER_ERROR)
                                .entity("Error while connecting database for deleting bill")
                                .build();
            }

            // get payment related to bill
            String sql1 = "SELECT paymentID FROM Bill WHERE billID = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setInt(1, billBean.getBillID());
            ResultSet resultSet = preparedStatement1.executeQuery();

            // call payment delete only if a payment is related to bill
            if(resultSet.next()) {
                billBean.setPaymentID(resultSet.getInt("paymentID"));

                if (billBean.getPaymentID() != 0) {
                    GetDeleteServiceFromPayment(billBean);
                }
            }

            // delete bill
            String sql2 = "DELETE FROM Bill WHERE billID = ? ";
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, billBean.getBillID());
            int status = preparedStatement2.executeUpdate(); 

            connection.close(); 

            if(status > 0) {
                return Response.status(Status.OK).entity("Bill deleted successfully").build();
            } else {
                return Response.status(Status.NOT_FOUND).entity("No bills found with the corresponding ID").build();
            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    // this method call update units method in connection service
	public String GetMonthlyUnitsFromConnectionService(BillBean billBean) {
		try {
			MediaType JSONType = MediaType.get("application/json; charset=utf-8");
			OkHttpClient client = new OkHttpClient();
			RequestBody body = RequestBody.create("{ 'connectionID':'" + billBean.getConnectionID() + "' , 'units':'" + billBean.getUnits() + "'}", JSONType);
			Request request = new Request.Builder().url("http://localhost:8080/ConnectionService/connection/units").put(body).build();

			try (okhttp3.Response response = client.newCall(request).execute()) {
			 	return response.body().string();
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
            return "Error while retrieving monthly units";
		}
	}

    //this method call delete method in payment service
	public String GetDeleteServiceFromPayment(BillBean billBean) {
		try {
			MediaType JSONType = MediaType.get("application/json; charset=utf-8");
			OkHttpClient client = new OkHttpClient();
			RequestBody body = RequestBody.create("{ 'PaymentID':'" + billBean.getPaymentID() + "'}", JSONType);
			Request request = new Request.Builder().url("http://localhost:8080/PaymentService/payment/deletebill").delete(body).build();

			try (okhttp3.Response response = client.newCall(request).execute()) {
			 	return response.body().string();
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
            return "Error while deleting payment related to bill";
		}
	}
}
