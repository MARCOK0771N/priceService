FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/price-service.jar price-service.jar
ENTRYPOINT ["java", "-jar", "/price-service.jar"]