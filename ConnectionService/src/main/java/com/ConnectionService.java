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

}
