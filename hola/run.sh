mvn clean package -DskipTests
java -jar target/hola-swarm.jar -Dswarm.http.port=6060 -Dswarm.management.http.disable=true -DalohaHostname=localhost =DalohaPort=7070
