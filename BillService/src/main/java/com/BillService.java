package com;

import model.Bill;

// for REST service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("Bill")
public class BillService {
    Bill billObject = new Bill();

	// get bills by connection ID
	@GET
	@Path("/connection/{connectionID}")
	@Produces(MediaType.TEXT_HTML)
	public String readConnectionBills(@PathParam("connectionID") String connectionID) {
		String output = billObject.readConnectionBills(connectionID);
		return output;
	}

	// get bills by customer ID
	@GET
	@Path("/customer/{customerID}")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomerBills(@PathParam("customerID") String customerID) {
		String output = billObject.readCustomerBills(customerID);
		return output;
	}
}