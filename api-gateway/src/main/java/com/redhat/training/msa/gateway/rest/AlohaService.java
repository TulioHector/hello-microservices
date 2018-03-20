package com.redhat.training.msa.gateway.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

public interface AlohaService {

	//TODO specify the path as 'aloha'
	@Path("aloha")
	//TODO specify that this method produces content with a content-type of 'text/plain'
	@Produces("text/plain")
	//TODO specify this method maps to HTTP GET requests
	@GET
	public String aloha();

}