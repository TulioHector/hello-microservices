package com.redhat.training.msa.hola;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
  classes = com.redhat.training.msa.hello.Application.class
)
public class BonjourResourceTest {

    @Value("${local.server.port}")
    private int port;
    
    private String baseURI;

    @Before
    public void beforeTest() {
        baseURI = String.format("http://localhost:%d", port);
    }

    @Test
    public void invokeHealthCheck() throws Exception {
    	RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(
		    baseURI + "/health", String.class);
		int status =response.getStatusCodeValue();
		String body = response.getBody();
		assertThat(status, equalTo(200));
		assertThat(body, containsString("{\"status\":\"UP\""));
    }

    @Test
    public void invokeHello() throws Exception {
    	RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(
		    baseURI + "/api/bonjour", String.class);
		int status =response.getStatusCodeValue();
		String body = response.getBody();
		assertThat(status, equalTo(200));
		assertThat(body, containsString("Bonjour"));
    }

}
