package com.redhat.training.msa.aloha.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("/")
public class AlohaResource {

    @Context
    private HttpServletRequest servletRequest;


    @GET
    @Path("/aloha")
    @Produces("text/plain")
    public String hola() {
        String hostname = servletRequest.getServerName(); 
        return String.format("Aloha mai %s\n", hostname);

    }

}
