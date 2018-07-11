package com.redhat.training.msa.hola.client;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class ClientConfiguration {
	private static final Logger log = Logger.getLogger(ClientConfiguration.class.getName());
	
    @Inject
	@ConfigProperty(name = "alohaPort")
	private String alohaPort;
	
	@Inject
	@ConfigProperty(name = "alohaHostname")
	private String alohaHostname;

	@Produces
	@Singleton
	public AlohaService alohaService() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://" + alohaHostname + ":" + alohaPort + "/api");
		log.info("Aloha service is located at " + target.getUri());
		ResteasyWebTarget rtarget = (ResteasyWebTarget) target;
		AlohaService service = rtarget.proxy(AlohaService.class);
		return service;
	}
}
