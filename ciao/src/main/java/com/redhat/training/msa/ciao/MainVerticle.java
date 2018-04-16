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
import com.redhat.training.msa.ciao.service.CiaoServiceVertxEBProxy;

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
        router.get("/api/ciao/:nome")
            .produces("application/text")
            .handler(this::ciao);

        vertx.createHttpServer()
            .requestHandler(router::accept)
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
    	CiaoService serviceImpl = new CiaoServiceImpl();        
    	ProxyHelper.registerService(CiaoService.class, vertx, serviceImpl, ADDRESS);
    	ebProxy = new CiaoServiceVertxEBProxy(vertx, ADDRESS);
    }
    
    private void ciao(RoutingContext rc) {
    	String nome = rc.request().getParam("nome");
        String host = rc.request().host();
        
        ebProxy.ciao(host, nome, ar -> {
        	rc.response().end(ar.result());
        });
    }

}
