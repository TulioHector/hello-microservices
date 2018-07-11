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
        return String.format("Hola de %s", serverName);
    }

    @GET
    @Path("/hola-chaining")
    @Produces("application/json")
    public List<String> holaChaining() {
        List<String> greetings = new ArrayList<>();
        greetings.add(hola());
        greetings.add(new HolaChainingCommand(alohaService).execute());
        return greetings;
    }

	public static class HolaChainingCommand extends HystrixCommand<String> {
		public static final String HOLA_CHAINING_COMMAND_KEY = "HolaChainingCommand";
		private AlohaService alohaService;

		public HolaChainingCommand(AlohaService alohaService) {
			super(Setter
				.withGroupKey(HystrixCommandGroupKey.Factory.asKey("group"))
				.andCommandKey(HystrixCommandKey.Factory.asKey(HOLA_CHAINING_COMMAND_KEY))
				.andCommandPropertiesDefaults(
					HystrixCommandProperties.Setter()
						.withCircuitBreakerRequestVolumeThreshold(2)
						.withCircuitBreakerSleepWindowInMilliseconds(5000))
			);
			this.alohaService = alohaService;
		}
    	
		@Override
		protected String run() throws Exception {
			return alohaService.aloha();
		}
	}

}
