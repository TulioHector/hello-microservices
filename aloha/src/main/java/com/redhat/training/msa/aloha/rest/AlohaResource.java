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
package com.redhat.training.msa.aloha.rest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.msa.aloha.json.Person;
import com.redhat.training.msa.aloha.json.PersonParser;

import io.swagger.annotations.ApiOperation;


//TODO Add a class-level path of '/'
public class AlohaResource {

	private final Logger log = LoggerFactory.getLogger(AlohaResource.class);
	
	@Inject
	private PersonParser parser;
	
    //TODO Inject the request using the Context
    private HttpServletRequest servletRequest;

    
    //TODO Use the PostConstruct annotation to run this method every time an AlohaResource is created
    private void init() {
    	log.info("AlohaResource created!");
    }
    
    //TODO Map this method to HTTP GET requests
    //TODO Add a path of '/aloha'
    //TODO Specify that this method produces a media type of text/plain
    public String hola() {
        String hostname = servletRequest.getServerName(); 
        return String.format("Aloha mai %s", hostname);

    }
    
    //TODO Map this method to HTTP POST requests
    //TODO Add a path of '/aloha'
    //TODO Specify that this method produces a media type of text/plain
    //TODO Specify that this method consumes a media type of application/json
    public String hola(String json) {
    	Person p = parser.parse(json);
        String hostname = servletRequest.getServerName(); 
        return String.format("Aloha mai %s %s from %s on %s", p.getFirstName(), p.getLastName(), p.getLocation(), hostname);
    }

}
