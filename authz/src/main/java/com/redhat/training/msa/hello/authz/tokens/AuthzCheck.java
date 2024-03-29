/*
 * Copyright(c) 2017 IBM, Red Hat, and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.training.msa.hello.authz.tokens;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

/**
 * The health check for the session application
 * Scope is application wide
 */
@Health
@ApplicationScoped
public class AuthzCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        // create a response named authz-check
        // service is always up
        return HealthCheckResponse.named("authz-check")
            .up()
            .build();
    }
}
