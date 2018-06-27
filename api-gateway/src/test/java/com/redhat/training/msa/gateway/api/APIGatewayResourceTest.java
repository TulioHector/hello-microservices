package com.redhat.training.msa.gateway.api;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

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
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.arquillian.CreateSwarm;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.redhat.training.msa.gateway.proxy.HolaProxy;

@RunWith(Arquillian.class)
public class APIGatewayResourceTest {
	
	@Rule
	public WireMockRule holaMockRule = new WireMockRule(options().port(7070));

	@Rule
	public WireMockRule bonjourMockRule = new WireMockRule(options().port(7071));
	
	private static String port = System.getProperty("arquillian.swarm.http.port", "18080");
	private Client client;

	@CreateSwarm
	public static Swarm newContainer() throws Exception {
		Properties properties = new Properties();
		properties.put("swarm.http.port", port);
		return new Swarm(properties);
	}

	@Deployment
	public static Archive<?> createDeployment() {
		WebArchive archive = ShrinkWrap.create(WebArchive.class, "hello-gateway.war")
				.addPackages(true, JaxRsActivator.class.getPackage())
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource("config-test.properties","microprofile-config.properties")
				.addPackages(true, HolaProxy.class.getPackage());

		return archive;
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
	public void testHolaProxy() {
		holaMockRule.stubFor(get(urlMatching("/api/hola"))
		        .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "text/plain").withBodyFile("hola.txt")));
		
		WebTarget target = client.target("http://localhost:" + port).path("/api").path("/es");
		Response response = target.request(MediaType.TEXT_PLAIN).get();
		assertThat(response.getStatus(), equalTo(new Integer(200)));
		assertThat(response.readEntity(String.class), containsString("Hola"));
	}

	@Test
	@RunAsClient
	public void testBonjourProxy() {
		bonjourMockRule.stubFor(get(urlMatching("/api/bonjour"))
		        .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "text/plain").withBodyFile("bonjour.txt")));
		
		WebTarget target = client.target("http://localhost:" + port).path("/api").path("/fr");
		Response response = target.request(MediaType.TEXT_PLAIN).get();
		assertThat(response.getStatus(), equalTo(new Integer(200)));
		assertThat(response.readEntity(String.class), containsString("Bonjour"));
	}

}
