package com.redhat.training.msa.hola.rest;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;

@RunWith(Arquillian.class)
public class HolaResourceTest {
	
	private Client client;
	
    @Deployment
    public static Archive<?> createDeployment() {

        WebArchive archive =  ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, JaxRsActivator.class.getPackage());

        return archive;
    }
	
	@CreateSwarm
    public static Swarm newContainer() throws Exception {
        Properties properties = new Properties();
        properties.put("swarm.http.port", 8080);
        return new Swarm(properties);
    }
	
    @Before
    public void before() throws Exception {
        client = ClientBuilder.newClient();
    }

    @After
    public void after() throws Exception {
        client.close();
    }
    
    @Test
    @RunAsClient
    public void testHola() throws Exception {
        WebTarget target = client.target("http://localhost:8080").path("/api").path("/hola");
        Response response = target.request(MediaType.TEXT_PLAIN).get();
        String text = response.readEntity(String.class);
        assertThat(text, containsString("Hola de localhost"));
    }
}
