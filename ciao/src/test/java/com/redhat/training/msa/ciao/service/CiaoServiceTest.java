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

@RunWith(VertxUnitRunner.class)
public class CiaoServiceTest {

    private Vertx vertx;

    @Before
    public void setup(TestContext testContext) {
        vertx = Vertx.vertx();
    }

    @After
    public void tearDown(TestContext testContext) {
        vertx.close(testContext.asyncAssertSuccess());
    }

    @Test(timeout=3000)
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

}
