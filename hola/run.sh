mvn clean package -DskipTests
java -jar target/hola-swarm.jar -Dswarm.management.http.disable=true -DalohaHostname=localhost -DalohaPort=7070
