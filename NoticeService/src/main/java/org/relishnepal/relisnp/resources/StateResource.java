package org.relishnepal.relisnp.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.relishnepal.relisnp.beans.State;
import org.relishnepal.relisnp.service.StateService;

@Path("/notices")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StateResource {
	StateService statesService = new StateService();
	
	@GET
	public List<State> getMessages() throws SQLException{
		return statesService.getAllMessages();
	}
	
	@POST
	public State addMessage(State state) throws SQLException{
		return statesService.addMessage(state);
	}
	
	@PUT
	@Path("/{noticeId}")
	public State updateMessage(@PathParam("noticeId") int noticeId, State state) throws SQLException{
		state.setNoticeId(noticeId);
		return statesService.updateMessage(state);
	}
	
	@GET
	@Path("/{noticeId}")
	public State getMessage(@PathParam("noticeId") int noticeId) throws SQLException{
		return statesService.getMessage(noticeId);
	}
	
	@DELETE
	@Path("/{noticeId}")
	public void deleteMessage(@PathParam("noticeId") int noticeId) throws SQLException{
		statesService.deleteMessage(noticeId);
	}
}
