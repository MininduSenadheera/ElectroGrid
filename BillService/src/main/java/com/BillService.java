package com;

// for REST service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bean.BillBean;
import model.Bill;

@Path("Bill")
public class BillService {
    Bill billObject = new Bill();

	// get bill by ID
	@GET
	@Path("/{billID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readBill(@PathParam("billID") String billID) {
		Response response = billObject.readBill(billID);
		return response;
	}
   
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
    
	// insert new bill
    @POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response newBill(String billData) {
		BillBean billBean = new BillBean();
		billBean.convertStringToJSONInsert(billData);

		Response response = billObject.newBill(billBean);
		return response;
	}

	// update bill
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBill(String billData) {
		BillBean billBean = new BillBean();
		billBean.convertStringToJSONUpdate(billData);
		
		Response response = billObject.updateBill(billBean);
		return response;
	}

	// delete bill
	@DELETE
	@Path("/{billID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBill(@PathParam("billID") String billID) {
		BillBean billBean = new BillBean();
		billBean.setBillID(Integer.parseInt(billID));
		
		Response response = billObject.deleteBill(billBean);
		return response;
	}
}