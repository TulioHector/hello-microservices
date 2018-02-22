package com.redhat.training.msa.hola.rest;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;


@RunWith(Arquillian.class)
public class HolaResourceFallBackIntegrationTest {

	//TODO Inject the URL used by Arquillian to test the application
    @ArquillianResource
    private URL url;
	
	@Deployment
	public static WebArchive deploy() {
		//TODO Delegate a call to the deploy static method from ArquillianTestUtils class
		//TODO Add the microprofile-config.properties file to the META-INF directory
		return null;
	}
	
	@CreateSwarm
	public static Swarm createSwarm() throws Exception {
		//TODO Delegate a call to the newContainer static method from ArquillianTestUtils class
		return null;
	}
	@Test
	public void testFallback() {
		//TODO Use the ClientBuilder class from javax.ws.rs.client.ClientBuilder class
		//TODO Define call the target method and pass this.url.toExternalForm()+"/api/hola" as a parameter
		//TODO The REST Endpoint returns only text, set the request to get MediaType.TEXT_PLAIN and store the output to a Response object
		//TODO Use Hamcrest to evaluate the HTTP code is 200        
		//TODO Use Hamcrest to evaluate the Body of the REST response with "Hola de localhost"
	}

}
