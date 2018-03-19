package com.redhat.training.msa.gateway.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.faulttolerance.Fallback;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/")
@Api("API Gateway")
@ApplicationScoped
public class APIGatewayResource {

	// TODO inject this using CDI
	@Inject
	private AlohaService alohaService;

	// TODO inject this using CDI
	@Inject
	private HolaService holaService;

	@GET
	@Path("/es")
	@Produces("text/plain")
	@ApiOperation("Returns the greeting in Spanish")
	//TODO specify the alohaFallback method as the fallback
	@Fallback(fallbackMethod="holaFallback")
	public String hola() {
		String response = holaService.hola();

		return response;
	}

	@GET
	@Path("/haw")
	@Produces("text/plain")
	@ApiOperation("Returns the greeting in Hawaiian")
	//TODO specify the alohaFallback method as the fallback
	@Fallback(fallbackMethod="alohaFallback")
	public String aloha() {
		String response = alohaService.aloha();

		return response;
	}

	@SuppressWarnings("unused")
	private String alohaFallback() {
		return "Aloha fallback";

	}
	@SuppressWarnings("unused")
	private String holaFallback() {
		return "Hola fallback";

	}
}
