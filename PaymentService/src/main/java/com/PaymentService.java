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
		

}