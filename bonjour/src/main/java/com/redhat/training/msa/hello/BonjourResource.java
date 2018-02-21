package com.redhat.training.msa.hello;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO: Declare root Path for this resource
@Component
public class BonjourResource {

    // TODO: Inject Bonjour Service Component

    @Context
    private HttpServletRequest servletRequest;

    // TODO: Declare HTTP method type
    // TODO: Declare Path to this method
    @Produces(MediaType.TEXT_PLAIN)
    public String bonjour() {
        String hostname = servletRequest.getServerName(); 
        // TODO: Invoke bonjour() method on BonjourService 
        // and return the response
        return "";
    }

}
