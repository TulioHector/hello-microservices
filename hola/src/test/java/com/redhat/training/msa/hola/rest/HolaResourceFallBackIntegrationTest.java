package com.redhat.training.msa.hola.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.net.URL;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;

import com.redhat.training.msa.hola.ArquillianTestUtils;


@RunWith(Arquillian.class)
public class HolaResourceFallBackIntegrationTest {

	//TODO Inject the URL used by Arquillian to test the application
    @ArquillianResource
    private URL url;
	
	@Deployment
	public static WebArchive deploy() {
		//TODO Delegate a call to the deploy static method from ArquillianTestUtils class
		return ArquillianTestUtils.deploy().addAsManifestResource("META-INF/microprofile-config.properties","microprofile-config.properties");
	}
	
	@CreateSwarm
	public static Swarm createSwarm() throws Exception {
		//TODO Delegate a call to the newContainer static method from ArquillianTestUtils class
		return ArquillianTestUtils.newContainer();
	}
	@Test
	public void testFallback() {
		//TODO Use the ClientBuilder class from javax.ws.rs.client.ClientBuilder class
		final Client client = ClientBuilder.newBuilder().build();
		//TODO Define call the target method and pass this.url.toExternalForm()+"/api/hola" as a parameter
        WebTarget target = client.target(this.url.toExternalForm()+"/api/hola");
		//TODO The REST Endpoint returns only text, set the request to get MediaType.TEXT_PLAIN and store the output to a Response object
        Response response = target.request(MediaType.TEXT_PLAIN).get();
		//TODO Evaluate the HTTP code is 200        
		assertEquals(200,response.getStatus());
		//TODO Evaluate the Body of the REST response with "Hola de localhost"
		assertEquals("Hola de localhost",response.readEntity(String.class));
	}

}
