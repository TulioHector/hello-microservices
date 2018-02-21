package com.redhat.training.msa.hello;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO: Set up the Path for this resource and declare this class 
// as a Spring Boot component
public class BonjourResource {

    // TODO: Inject the BonjourService component

    @Context
    private HttpServletRequest servletRequest;

    // TODO: Declare the HTTP method type
    // TODO: Declare the path to this method/end point
    // TODO: Declare the response type (Content-Type header)
    public String bonjour() {
        String hostname = servletRequest.getServerName(); 
        // TODO: Invoke the bonjour() method on BonjourService
        // and return the response
        return null;
    }

}
