package com.redhat.training.msa.ciao;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.serviceproxy.ProxyHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.training.msa.ciao.service.CiaoService;
import com.redhat.training.msa.ciao.service.CiaoServiceImpl;

public class MainVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);

    private CiaoService ebProxy;
    
    @Override
    public void stop() {
        LOG.info("Shutting down Ciao service...");
    }

    @Override
    public void start(Future<Void> future) {

        LOG.info("Welcome to Vertx. Starting Ciao service...");
        
        registerCiaoService();

        Router router = Router.router(vertx);
        //TODO: add the /api/ciao/:name API entry point

        //TODO: add the Router as request handler for the HTTP server
        vertx.createHttpServer()
            .listen(8080, result -> {
                if (result.succeeded()) {
                    future.complete();
                }
                else {
                    future.fail(result.cause());
                }
            });
    }
    
    final static String ADDRESS = "ciao-service";
    
    private void registerCiaoService() {
        LOG.info("Registering ciao service to the event bus...");
        
    	//TODO: instantiate the service implementation
        //TODO: register the service implementation to the event bus
        //TODO: instantiate an event bus proxy for the service
    }
    
    private void ciao(RoutingContext rc) {
    	String nome = rc.request().getParam("nome");
        String host = rc.request().host();
        
        LOG.info("Got API request for nome = '" + nome + "' ...");
        
        //TODO: invoke the ciao service using the event bus proxy
        //TODO: use the service return value as the request respose data
    }

}
