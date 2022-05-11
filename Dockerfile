FROM openjdk:17
RUN mkdir -p /usr/src/complex-java-project
COPY /target/complex-java-project-0.0.1-SNAPSHOT.jar /usr/src/complex-java-project
WORKDIR /usr/src/complex-java-project
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "complex-java-project-0.0.1-SNAPSHOT.jar"]