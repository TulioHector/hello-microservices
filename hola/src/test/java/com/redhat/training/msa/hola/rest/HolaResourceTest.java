package com.redhat.training.msa.hola.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import java.io.File;
import java.util.Properties;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;

@RunWith(Arquillian.class)
public class HolaResourceTest {


    @Deployment
    public static Archive<?> createWebArchive() {
    	
    	File[] deps = Maven.resolver()
				.loadPomFromFile("pom.xml")
				.importDependencies(ScopeType.COMPILE,ScopeType.RUNTIME,ScopeType.TEST).resolve()
				.withTransitivity().asFile();
		WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(true, "com.redhat.training.msa")
				.addAsLibraries(deps)
				.addAsResource("project-defaults.yml","project-defaults.yml");		
		return webArchive;
    }
	

	@CreateSwarm
    public static Swarm newContainer() throws Exception {
        Properties properties = new Properties();
        properties.put("swarm.http.port", 8080);
        return new Swarm(properties).withProfile("defaults");
    }


    @Test
    @RunAsClient
    public void testHola() throws Exception {
    	given().when().get("/api/hola").then().body(containsString("Hola de localhost"));
    }


}
