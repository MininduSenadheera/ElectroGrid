package com;

import model.Customer;

import javax.annotation.security.RolesAllowed;

//import java.ws.rs.*;
//import bean.CustomerBean;

//import javax.ejb.Stateless;
//for REST service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.parser.Parser;
//
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;

//for REST service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//For JSON
import com.google.gson.*;

import bean.CustomerBean;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Customers")
public class CustomerService {
	Customer customerObject = new Customer();

//	================= view all customers ===============
//	============= task done by the admin===================

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAllCustomers() {
		return customerObject.readAllCustomers();
	}
	

	//================get users by user id==========
//	//==================================
	@GET
	@Path("/{customerId}")
	@Produces(MediaType.TEXT_HTML)
	public String viewCustomer(@PathParam("customerId") String customerId) {
	return customerObject.viewCustomer(customerId);
	}
	

	// ============insert customer============
	//	===========task dome by the customer only====================
	@RolesAllowed({"admin","user"})
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(
			@FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName,
			@FormParam("nic") String nic,
			@FormParam("phoneNumber") int phoneNumber, 
			@FormParam("email") String email,
			@FormParam("address") String address)
			
	{


		String output = customerObject.insertCustomer( firstName, lastName, nic, phoneNumber,email, address);
		return output;
	}
	//===========================delet customer via xml==========================================
		//======================task done by admin=====================
		@DELETE
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deletecustomer(String customerData) { 
		
			//Convert the input string to an XML document
			Document doc = Jsoup.parse(customerData, "", Parser.xmlParser()); 
		 
			//Read the value from the element <customerID>
			String customerId = doc.select("customerId").text(); 
			
			//Pass this cusid can call the deleteItem() method in the modeland
			String output = customerObject.deletecustomer(customerId); 
			return output; 
		}
	
	
	
	
	
	
	
	

}
