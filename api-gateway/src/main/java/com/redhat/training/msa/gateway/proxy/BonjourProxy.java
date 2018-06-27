package com.redhat.training.msa.gateway.proxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public interface BonjourProxy {

	@Path("bonjour")
	@Produces("text/plain")
	@GET
	public String bonjour();
	
}
