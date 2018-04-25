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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericType;

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
