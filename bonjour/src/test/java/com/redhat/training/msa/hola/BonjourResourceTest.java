package com.redhat.coolstore.cart.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

// TODO: Set up the Spring Boot test runner and configuration
public class BonjourResourceTest {

    @Value("${local.server.port}")
    private int port;

    @Before
    public void beforeTest() {
        // TODO: Add the URL for the Bonjour service
        RestAssured.baseURI = "";
    }

    @Test
    public void invokeHealthCheck() throws Exception {
        given().get("/health").then().assertThat().statusCode(200).body(containsString("UP"));
    }

    @Test
    public void invokeHello() throws Exception {
        // TODO: Invoke the bonjour end point and verify the reponse
    }

}
