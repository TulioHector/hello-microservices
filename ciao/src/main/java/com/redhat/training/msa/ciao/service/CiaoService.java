package com.redhat.training.msa.ciao.service;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

@ProxyGen
public interface CiaoService {

	public void ciao(String host, String nome, Handler<AsyncResult<String>> resultHandler);
}
