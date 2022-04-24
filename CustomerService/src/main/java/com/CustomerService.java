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

}
