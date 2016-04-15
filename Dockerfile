FROM java:openjdk-8-jdk

ADD swarm-app/target/brms-demo-swarm.jar /opt/wildfly-swarm.jar


EXPOSE 8080
CMD ["java", "-jar", "/opt/wildfly-swarm.jar"]
