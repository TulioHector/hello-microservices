package com.redhat.training.msa.gateway.api;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.msa.gateway.proxy.BonjourProxy;
import com.redhat.training.msa.gateway.proxy.HolaProxy;

@Path("/")
@RequestScoped
public class APIGatewayResource {
	
	private static final Logger log = LoggerFactory.getLogger(APIGatewayResource.class);

	@Inject
	@ConfigProperty(name = "HOLA_SERVICE_URL")
	private String holaURL;
	
	@Inject
	@ConfigProperty(name = "BONJOUR_SERVICE_URL")
	private String bonjourURL;


	private HolaProxy buildHolaProxy() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(holaURL);
		ResteasyWebTarget restEasyTarget = (ResteasyWebTarget) target;
		return restEasyTarget.proxy(HolaProxy.class);
	}
	
	private BonjourProxy buildBonjourProxy() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(bonjourURL);
		ResteasyWebTarget restEasyTarget = (ResteasyWebTarget) target;
		return restEasyTarget.proxy(BonjourProxy.class);
	}

	@GET
    @Path("/es")
    @Produces("text/plain")
    public String hola() {
        HolaProxy proxy = buildHolaProxy();
        String response = proxy.hola();
        
        return response;
    }

    @GET
    @Path("/fr")
    @Produces("text/plain")
    public String bonjour() {
    	BonjourProxy proxy = buildBonjourProxy();
        String response = proxy.bonjour();

        return response;
    }
}
