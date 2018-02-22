package com.redhat.training.msa.gateway.client;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.redhat.training.msa.gateway.rest.AlohaService;
import com.redhat.training.msa.gateway.rest.HolaService;


public class ClientConfiguration {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ClientConfiguration.class);

    @Inject
	@ConfigProperty(name = "alohaPort")
	private String alohaPort;
	
	@Inject
	@ConfigProperty(name = "alohaHostname")
	private String alohaHostname;

	@Inject
	@ConfigProperty(name = "holaPort")
	private String holaPort;
	
	@Inject
	@ConfigProperty(name = "holaHostname")
	private String holaHostname;


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

	@Produces
	@Singleton
	public HolaService holaService() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://" + holaHostname + ":" + holaPort + "/api");
		log.info("Hola service is located at " + target.getUri());
		ResteasyWebTarget rtarget = (ResteasyWebTarget) target;
		HolaService service = rtarget.proxy(HolaService.class);
		return service;
	}
}
