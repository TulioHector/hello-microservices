package com.redhat.training.msa.hola.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/")
public class HelloResource {

    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello() {
        return "Hello World!\n";
    }
}
