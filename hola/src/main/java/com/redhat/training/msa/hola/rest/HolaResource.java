/**
 * Copyright 2016, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

@Path("/")

@ApplicationScoped
public class HolaResource {

    @Inject
    private AlohaService alohaService;

    @Context
    private SecurityContext securityContext;

    @Context
    private HttpServletRequest servletRequest;

    private String serverName;


    @Inject
    @ConfigProperty(name="alohaHostname")
    private String hostname;

    @Inject
    @ConfigProperty(name="alohaPort")
    private String port;


    @PostConstruct
    public void init() {
        serverName = servletRequest.getServerName();
    }

    /* (non-Javadoc)
     * @see com.redhat.training.msa.hola.rest.HolaResource#hola()
     */
    @GET
    @Path("/hola")
    @Produces("text/plain")
    public String hola() {

        return String.format("Hola de %s", hostname);
    }

    /* (non-Javadoc)
     * @see com.redhat.training.msa.hola.rest.HolaResource#holaChaining()
     */
    @GET
    @Path("/hola-chaining")
    @Produces("application/json")
    public List<String> holaChaining() {
        List<String> greetings = new ArrayList<>();
        greetings.add(hola());
        greetings.add(new HolaChainingCommand(alohaService).execute());
        return greetings;
    }

    public static class HolaChainingCommand extends HystrixCommand<String>{
    	public static final String HOLA_CHAINING_COMMAND_KEY = "HolaChainingCommand";
		private AlohaService alohaService;

		public HolaChainingCommand(AlohaService alohaService) {
        	super(Setter
    				.withGroupKey(HystrixCommandGroupKey.Factory.asKey("group"))
    				.andCommandKey(HystrixCommandKey.Factory.asKey(HOLA_CHAINING_COMMAND_KEY))
    				.andCommandPropertiesDefaults(
    						HystrixCommandProperties
    							.Setter()
    								.withCircuitBreakerRequestVolumeThreshold(2)
    								.withCircuitBreakerSleepWindowInMilliseconds(5000)));
        	this.alohaService = alohaService;

    	}
    	
		@Override
		protected String run() throws Exception {
			return alohaService.aloha();
		}}

}
