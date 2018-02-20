package com.redhat.training.msa.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainVerticle extends AbstractVerticle {

  private static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);

    @Override
    public void stop() {
        LOG.info("Shutting down Ciao service...");
  }

    @Override
    public void start(Future<Void> future) {

        LOG.info("Welcome to Vertx. Starting Ciao service...");

        // TODO: Setup Vert.x router
        // TODO: Add a route for the /api/ciao endpoint
        // TODO: Add a handler that responds with Ciao and the hostname

        // TODO: Create a new HTTP server instance that handles the route
        // TODO: Ensure you handle success or failure of each request and log failures (if any)

    }

}
