package com.redhat.training.msa.gateway.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/")
@Api("API Gateway")
@ApplicationScoped
public class APIGatewayResource {

    @Inject
    private AlohaService alohaService;

    @Inject
    private HolaService holaService;

	@GET
    @Path("/es")
    @Produces("text/plain")
    @ApiOperation("Returns the greeting in Spanish")
    public String hola() {
        String response = holaService.hola();

        return response;
    }

    @GET
    @Path("/haw")
    @Produces("text/plain")
    @ApiOperation("Returns the greeting in Hawaiian")
    public String aloha() {
        String response = alohaService.aloha();

        return response;
    }
}
