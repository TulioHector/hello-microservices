package com.redhat.training.msa.hola;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.Hystrix;
import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.redhat.training.msa.hola.client.AlohaService;
import com.redhat.training.msa.hola.rest.HolaResource;

public class HolaCircuitBreakerTest {

	@Mock
	private AlohaService proxy;

	@Before
	public void before() throws Exception {
		MockitoAnnotations.initMocks(this);
		resetHystrix();
		warmUpCircuitBreaker();
		openCircuitBreakerAfterOneFailingRequest();

	}

	@Test
	public void testGetCartCircuitBreaker() throws Exception {
		doThrow(RuntimeException.class).when(proxy).aloha();
		HystrixCircuitBreaker circuitBreaker = getCircuitBreaker();

		// demonstrates circuit is actually closed
		assertThat(circuitBreaker.isOpen(), equalTo(false));
		assertThat(circuitBreaker.allowRequest(), equalTo(true));

		HolaResource.HolaChainingCommand command = new HolaResource.HolaChainingCommand(proxy);
		try {
			command.execute();
			fail();
		} catch (HystrixRuntimeException e) {
            waitUntilCircuitBreakerOpens();
			circuitBreaker = getCircuitBreaker();
			assertThat(circuitBreaker.isOpen(), is(true));
		}
	}

	private void waitUntilCircuitBreakerOpens() throws InterruptedException {
		Thread.sleep(1000);
	}

	private void resetHystrix() {
		Hystrix.reset();
	}

	private void warmUpCircuitBreaker() {
		when(proxy.aloha()).thenReturn(null);
		HolaResource.HolaChainingCommand command = new HolaResource.HolaChainingCommand(proxy);
		command.execute();
	}

	public static HystrixCircuitBreaker getCircuitBreaker() {
		return HystrixCircuitBreaker.Factory.getInstance(getCommandKey());
	}

	private static HystrixCommandKey getCommandKey() {
		return HystrixCommandKey.Factory.asKey("HolaChainingCommand");
	}

	private void openCircuitBreakerAfterOneFailingRequest() {
		ConfigurationManager.getConfigInstance().setProperty(
				"hystrix.command.HolaChainingCommand.circuitBreaker.requestVolumeThreshold", 1);
	}

}
