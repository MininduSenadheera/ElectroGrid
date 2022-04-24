package model; 

import java.sql.*;
 
import bean.ComplianceBean; 
import util.DBConnection; 

public class Compliance { 
 
	//view connection by complainID
      public String readComplain(String complainID) { 

        String output = ""; 
      
        try { 

            Connection connection = DBConnection.connect(); 

             if (connection == null) { 
                return "Error while connecting database for reading complaints by connection"; 
            } 
          
             // Prepare the html table to be displayed 
             output = "<table border=\"1\">"  
                        + "<tr>" 
                            + "<th>Complain ID</th>" +  "<th>Customer ID</th>" + "<th>Content</th>" + "<th>Heading</th>"
                            + "<th>Specification</th>" 
                        + "</tr>"; 
 
            // sql statement to retrieve complaints by complain ID 
            String sql =    "SELECT * " + 
                            "FROM complaint C " +
                            "where  C.complainID=?";       

            // binding complaintID and executing the query 
            PreparedStatement preparedStatment = connection.prepareStatement(sql);
            preparedStatment.setInt( 1,Integer.parseInt(complainID));
            ResultSet resultSet = preparedStatment.executeQuery();
            
            ComplianceBean complianceBean = new ComplianceBean(); 
 
            // looping through the rows 
            while (resultSet.next()) { 
                complianceBean.setComplainID(resultSet.getInt("complainID")); 
                complianceBean.setCustomerID(resultSet.getInt("customerID")); 
                complianceBean.setContent(resultSet.getString("content")); 
                complianceBean.setHeading(resultSet.getString("heading")); 
                complianceBean.setSpecification(resultSet.getString("specification")); 
                           
               // add the data to the html table 
                output += "<tr><td>" +  complianceBean.getComplainID() + "</td>"; 
                output += "<td>" +  complianceBean.getCustomerID() + "</td>"; 
                output += "<td>" +  complianceBean.getContent() + "</td>"; 
                output += "<td>" +  complianceBean.getHeading() + "</td>"; 
                output += "<td>" +  complianceBean.getSpecification() + "</td>"; 
          
              } 
        
            connection.close(); 

            output += "</table>"; 
          
        } catch (Exception e) { 
            System.err.println(e.getMessage()); 
            output = "Error while reading complaints by complain ID"; 
        } 

         return output; 

    } 
     
   // updating an inserted complaint 
    public String updateComplain(ComplianceBean complianceBean) { 

    	String output = "";  
			
    	try { 
    		Connection connection = DBConnection.connect();  
    			if (connection == null) { 
    				return "Error while connecting to the database for updating complain.";  
    			} 

             String sql = " UPDATE complaint SET  content = ?  WHERE complainID =?"; 

            PreparedStatement preparedStatement = connection.prepareStatement(sql); 
            preparedStatement.setString(1, complianceBean.getContent()); 
            //preparedStatement.setString(2, complianceBean.getHeading()); 
            preparedStatement.setInt(2, complianceBean.getComplainID()); 
            preparedStatement.execute();

            connection.close(); 

 
            	output = "Complain updated successfully"; 

    		} catch (Exception e) { 
    			output = "Error while updating the complain.";  
    			System.err.println(e.getMessage()); 
    		} 

    		return output; 

    	} 
     

} 