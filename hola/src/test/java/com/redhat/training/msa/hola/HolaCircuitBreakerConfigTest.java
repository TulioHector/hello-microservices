package com.redhat.training.msa.hola;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.HystrixCommandProperties;
import com.redhat.training.msa.hola.rest.AlohaService;
import com.redhat.training.msa.hola.rest.HolaResource;

public class HolaCircuitBreakerConfigTest {

		@Mock
		private AlohaService proxy;

		@Before
		public void before() throws Exception {
			MockitoAnnotations.initMocks(this);
			warmUpAddToCartCircuitBreaker();

		}

	    @Test
	    public void testAddToCartTimeout() {
	        assertTrue(getCircuitBreakerCommandProperties("HolaChainingCommand").circuitBreakerRequestVolumeThreshold().get() == 2);
	        assertTrue(getCircuitBreakerCommandProperties("HolaChainingCommand").circuitBreakerSleepWindowInMilliseconds().get() == 5000);
	    }


	    private void warmUpAddToCartCircuitBreaker() {
			when(proxy.aloha()).thenReturn(null);
			HolaResource.HolaChainingCommand command = new HolaResource.HolaChainingCommand(proxy);
			command.execute();
	    }


	    
	    public static HystrixCommandProperties getCircuitBreakerCommandProperties(String commandKey) {
	        return HystrixCommandMetrics.getInstance(getCommandKey(commandKey)).getProperties();
	    }

	    
	    private static HystrixCommandKey getCommandKey(String commandKey) {
	        return HystrixCommandKey.Factory.asKey(commandKey);
	    }
}
