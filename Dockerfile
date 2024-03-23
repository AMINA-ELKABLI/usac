FROM eclipse-temurin:17-jdk-alpine
MAINTAINER "aminaelkabli"
ADD target/USAC_LAST-0.0.1-SNAPSHOT.jar USAC_LAST_V1.jar
ENTRYPOINT ["java", "-jar", "USAC_LAST_v1.jar" , "--spring.profiles.active=prod"]
