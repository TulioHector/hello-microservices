package com.redhat.training.msa.gateway.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@ApplicationScoped
@Health
public class APIGatewayHealth implements HealthCheck {

	@Override
	public HealthCheckResponse call() {
		return HealthCheckResponse.named("API Gateway service")
				.up().build();
	}

}
