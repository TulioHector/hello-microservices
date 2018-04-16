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
public class CiaoServiceEBProxyTest {

    private Vertx vertx;

    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();
    }

    @After
    public void tearDown(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    final static String ADDRESS = "ciao-service";

    @Test(timeout=3000)
    public void ciaoProxyTest(TestContext testContext) {
        final Async async = testContext.async();
      
        //TODO: uncomment the following lines, and remove the line after the comment
        //CiaoService serviceImpl = new CiaoServiceImpl();        
  	    //ProxyHelper.registerService(CiaoService.class, vertx, serviceImpl, ADDRESS);
  	    //CiaoService proxy = new CiaoServiceVertxEBProxy(vertx, ADDRESS);
        CiaoService proxy = null;
  	
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
