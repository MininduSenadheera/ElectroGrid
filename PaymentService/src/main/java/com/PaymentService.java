package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import bean.PaymentBean;
import model.Payment;

@Path("/payment")
public class PaymentService {
	
	Payment payObject = new Payment();
	
	//read payment details
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readPaymentDetails(){
		
			return payObject.readPaymentDetails();
		}
		
	// insert  Payment
		 @POST
			@Path("/")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.APPLICATION_JSON)
			public String newPill(String paymentData) {
				PaymentBean payBean = new PaymentBean();
				payBean.convertStringToJSONInsert(paymentData);

				String response =payObject.newPayment(payBean);
				return response;
			}
		
	// get Payments by customer ID
		@GET
		@Path("/customer/{customerID}")
		@Produces(MediaType.TEXT_HTML)
		public String readCustomerpayments(@PathParam("customerID") String customerID) {
			String output = payObject.readCustomerPayments(customerID);
			return output;
		}
		
	// update payment
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayment(String paymentData) {
			PaymentBean payBean = new PaymentBean();
			payBean.convertStringToJSONUpdate(paymentData);
			
			String output = payObject.updatePayment(payBean);
			return output;
		}
		
	// delete payment
		@DELETE
		@Path("/{paymentID}")
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePayment(@PathParam("paymentID") String paymentID) {
			PaymentBean payBean = new PaymentBean();
			payBean.setPaymentID(Integer.parseInt(paymentID));
			
			String output = payObject.deletePayment(payBean);
			return output;
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   

}