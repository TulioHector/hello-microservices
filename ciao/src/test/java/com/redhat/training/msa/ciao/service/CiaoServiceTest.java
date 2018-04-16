package com.redhat.training.msa.ciao.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.redhat.training.msa.ciao.MainVerticle;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.serviceproxy.ProxyHelper;

@RunWith(VertxUnitRunner.class)
public class CiaoServiceTest {

    private Vertx vertx;

    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();

        vertx.deployVerticle(MainVerticle.class.getName(), testContext.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    @Test
    public void ciaoImplTest(TestContext testContext) {
        final Async async = testContext.async();
        
        CiaoService service = new CiaoServiceImpl();
        
        final String host = "corleone.example.com";
        final String nome = "Vito";
        service.ciao(host, nome, ar -> {
        	String msg = ar.result();
        	testContext.assertTrue(msg.contains("Ciao"));
            testContext.assertTrue(msg.contains(nome));
        	testContext.assertTrue(msg.contains(host));
            async.complete();
        });
    }

    final static String ADDRESS = "ciao-service";

    @Test
    public void ciaoImplProxy(TestContext testContext) {
        final Async async = testContext.async();
        
        CiaoService serviceImpl = new CiaoServiceImpl();        
    	ProxyHelper.registerService(CiaoService.class, vertx, serviceImpl, ADDRESS);
    	CiaoService proxy = new CiaoServiceVertxEBProxy(vertx, ADDRESS);
    	
        final String host = "corleone.example.com";
        final String nome = "Michael";
        proxy.ciao(host, nome, ar -> {
        	String msg = ar.result();
        	testContext.assertTrue(msg.contains("Ciao"));
            testContext.assertTrue(msg.contains(nome));
        	testContext.assertTrue(msg.contains(host));
            async.complete();
        });
    }

}
