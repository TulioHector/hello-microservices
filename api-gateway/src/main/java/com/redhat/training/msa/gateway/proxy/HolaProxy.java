package com.redhat.training.msa.gateway.proxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public interface HolaProxy {

	@Path("hola")
	@Produces("text/plain")
	@GET
	public String hola();
	
}
