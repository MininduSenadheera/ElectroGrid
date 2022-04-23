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
		

}