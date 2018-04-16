package com.redhat.training.msa.ciao.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

//TODO: create the package-info.java and annotate the package for code generation

//TODO: add annotation for code generation
public interface CiaoService {

	public void ciao(String host, String nome, Handler<AsyncResult<String>> resultHandler);
}
