package com.redhat.training.msa.hola.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
@ApplicationScoped
public class HolaResource {

    private static final Logger LOG = LoggerFactory.getLogger(HolaResource.class);

    @Context
    private HttpServletRequest servletRequest;

    @Inject
    @ConfigProperty(name = "com.redhat.hola.config.a", defaultValue="")
    private String a;

    @Inject
    @ConfigProperty(name = "com.redhat.hola.config.b", defaultValue="Dos")
    private String b;

    @GET
    @Path("/hola")
    @Produces("text/plain")
    public String hola() {
        String hostname = servletRequest.getServerName();
        LOG.info("Value in variable a => "+a);
        LOG.info("Value in variable b => "+b);
        return String.format("Hola de %s\n", hostname);
    }
}
