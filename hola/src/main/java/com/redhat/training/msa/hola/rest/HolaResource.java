package com.redhat.training.msa.hola.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.redhat.training.msa.hola.client.AlohaService;

@Path("/")

@ApplicationScoped
public class HolaResource {

    @Inject
    private AlohaService alohaService;

    @Context
    private HttpServletRequest servletRequest;

    private String serverName;


    @PostConstruct
    public void init() {
        serverName = servletRequest.getServerName();
    }

    @GET
    @Path("/hola")
    @Produces("text/plain")
    public String hola() {
        return String.format("Hola de %s\n", serverName);
    }

    @GET
    @Path("/hola-chaining")
    @Produces("application/json")
    public List<String> holaChaining() {
        List<String> greetings = new ArrayList<>();
        greetings.add(hola());
        //TODO wrap the call with a Hystrix command
        greetings.add(alohaService.aloha());
        return greetings;
    }

	public static class HolaChainingCommand extends HystrixCommand<String> {
    	
		//TODO change the Hystrix command key
		public static final String HOLA_CHAINING_COMMAND_KEY = "CHANGE_ME";
		private AlohaService alohaService;

		public HolaChainingCommand(AlohaService alohaService) {
			super(Setter
				.withGroupKey(HystrixCommandGroupKey.Factory.asKey("group"))
				.andCommandKey(HystrixCommandKey.Factory.asKey(HOLA_CHAINING_COMMAND_KEY))
				.andCommandPropertiesDefaults(
					HystrixCommandProperties.Setter()
						//TODO change Hystrix settings
						.withCircuitBreakerRequestVolumeThreshold(-1)
						.withCircuitBreakerSleepWindowInMilliseconds(-1))
			);
			this.alohaService = alohaService;
		}
    	
		@Override
		protected String run() throws Exception {
			//TODO invoke the Aloha microservice
			return null;
		}
	}

}
