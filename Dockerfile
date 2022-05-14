FROM maven:3.8.5-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17
RUN mkdir -p /usr/src/complex-java-project
COPY --from=build /home/app/target/complex-java-project-0.0.1-SNAPSHOT.jar /usr/src/complex-java-project
WORKDIR /usr/src/complex-java-project
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "complex-java-project-0.0.1-SNAPSHOT.jar"]