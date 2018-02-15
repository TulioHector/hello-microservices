package com.redhat.training.msa.gateway.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public interface HolaService {

	@Path("hola")
	@Produces("text/plain")
	@GET
	public String hola();
	
}
