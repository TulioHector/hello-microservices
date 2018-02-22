# api-gateway
An API Gateway service for hello microservices using MicroProfile on WildFly Swarm

Deploy the gateway in OpenShift
-----------------------------------

1. Make sure to be logged into OpenShift.
2. Make sure you have created or selected the appropriate OpenShift project. 
3. Execute:

		mvn clean fabric8:deploy
