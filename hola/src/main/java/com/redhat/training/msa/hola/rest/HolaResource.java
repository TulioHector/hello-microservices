package com.redhat.training.msa.hola.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/")
@ApplicationScoped
public class HolaResource {

    @Context
    private HttpServletRequest servletRequest;

    @Inject
    @ConfigProperty(name = "com.redhat.hola.config.a", defaultValue="")
    private String a;

    @Inject
    @ConfigProperty(name = "com.redhat.hola.config.b", defaultValue="Dos")
    private String b;

    // TODO: Configure method to accept GET requests at the /hola path and return plain text response
    public String hola() {
        String hostname = servletRequest.getServerName();
        return String.format("Hola de %s\nValue in property 'a' => %s\nValue in property 'b' => %s\n", hostname,a,b);
    }
}
