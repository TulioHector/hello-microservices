package com.redhat.training.msa.hello;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO: Declare Spring Boot Component
// TODO: Configure Path for this resource
public class BonjourResource {

    // TODO: Inject BonjourService

    @Context
    private HttpServletRequest servletRequest;

    // TODO: Declare HTTP method type
    // TODO: Configure Path for this method
    // TODO: Configure response type (Content-Type header) for this methid
    public String bonjour() {
        String hostname = servletRequest.getServerName(); 

        // TODO: Invoke the bonjour() method on BonjourService
        // and return the response
        return "";
    }

}
