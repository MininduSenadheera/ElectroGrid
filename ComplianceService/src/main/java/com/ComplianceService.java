package com;
import model.Compliance;

//for REST service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import bean.ComplianceBean;

@Path("/Complaint")
public class ComplianceService {

	 Compliance complainObject = new Compliance();

		// get complaints by complaint ID
		@GET
		@Path("/{complainID}")
		@Produces(MediaType.TEXT_HTML)
		public String readComplain(@PathParam("complainID") String complainID) {
			
			String output = complainObject.readComplain(complainID);
			return output;
		}

}
