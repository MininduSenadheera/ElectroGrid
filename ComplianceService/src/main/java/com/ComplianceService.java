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

		// update complain
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateComplain(String complainData ) {
			ComplianceBean complianceBean = new ComplianceBean();
		    complianceBean.convertStringToJSONUpdate(complainData);
		    
		    String output = complainObject.updateComplain(complianceBean);
		    return output;
		}
		
				// delete complain
		@DELETE
		@Path("/{complainID}")
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteComplain(@PathParam("complainID") String complainID) {
			ComplianceBean complianceBean = new ComplianceBean();
			complianceBean.setComplainID(Integer.parseInt(complainID));  
			
			String output = complainObject.deleteComplain(complianceBean);
			return output;
		}
		
		//insert a new Complain
	    @POST
	    @Path("/")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public String newComplain (String complainData){
	        ComplianceBean complianceBean = new ComplianceBean();
	        complianceBean.convertStringToJSONInsert(complainData);

	        String output = complainObject.newComplain(complianceBean);
	        return output;
	    }
}
