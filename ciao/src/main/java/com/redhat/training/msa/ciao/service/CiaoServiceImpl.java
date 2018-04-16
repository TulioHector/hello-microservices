package com.redhat.training.msa.ciao.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class CiaoServiceImpl implements CiaoService {

	@Override
	public void ciao(String host, String nome, Handler<AsyncResult<String>> resultHandler) {
		String msg = "Ciao " + nome +  ", da " + host +"\n";
		//TODO: return the message to the caller
	}
}
