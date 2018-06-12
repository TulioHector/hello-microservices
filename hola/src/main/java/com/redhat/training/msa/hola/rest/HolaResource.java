package com.redhat.training.msa.hola.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("/")
public class HolaResource {

    @Context
    private HttpServletRequest servletRequest;

	  @GET
    @Path("/hola")
    @Produces("text/plain")
    public String hola() {
        String hostname = servletRequest.getServerName();
        return String.format("Hola de %s\n", hostname);
    }
}
