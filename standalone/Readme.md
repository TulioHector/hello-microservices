# hola
hello standalone microservice using MicroProfile on WildFly Swarm

Build and Deploy hello locally
-----------------------------

1. Open a command prompt and navigate to the root directory of this microservice.
2. Type this command to build and execute the application:

        mvn wildfly-swarm:run

3. This will create a uber jar at  `target/hello-swarm.jar` and execute it.
4. The application will be running at the following URL: <http://localhost:8080/api/hello>

Deploy the application in OpenShift
-----------------------------------

1. Make sure to be logged into OpenShift.
2. Make sure you have created or selected the appropriate OpenShift project.
3. Execute:

		mvn clean fabric8:deploy
