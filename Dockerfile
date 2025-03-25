FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/product-service.jar product-service.jar
ENTRYPOINT ["java", "-jar", "/product-service.jar"]