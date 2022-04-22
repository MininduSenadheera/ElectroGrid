package com;

import model.ConnectionModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import bean.ConnectionBean;
@Path("Connection")
public class ConnectionService {

    ConnectionModel connectionObject = new ConnectionModel();

    //get Connection by connection ID
    @GET
    @Path("/{connectionID}")
    @Produces(MediaType.TEXT_HTML)
    public String readConnections(@PathParam("connectionID") String connectionID){
        String output = connectionObject.readConnections(connectionID);
        return output;
    }

    //insert a new Connection
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String newConnection (String connectionData){
        ConnectionBean connectionBean = new ConnectionBean();
        connectionBean.convertStringToJSONInsert(connectionData);

        String output = connectionObject.newConnection(connectionBean);
        return output;
    }

    //update connection units
    @PUT
    @Path("/Units")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
    public String updateConnectionUnits(String connectionData){
        ConnectionBean connectionBean = new ConnectionBean();
        connectionBean.convertStringToJSONUpdateUnits(connectionData);

        String output = connectionObject.updateConnectionUnits(connectionBean);
        return output;
    }

    //update connection status
    @PUT
    @Path("/Status")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
    public String updateConnectionStatus(String connectionData){
        ConnectionBean connectionBean = new ConnectionBean();
        connectionBean.convertStringToJSONUpdateStatus(connectionData);

        String output = connectionObject.updateConnectionStatus(connectionBean);
        return output;
    }



}
