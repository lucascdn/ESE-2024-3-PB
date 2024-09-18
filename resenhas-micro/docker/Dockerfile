FROM amazoncorretto:21
LABEL author="lucas.damasceno"
WORKDIR /app
COPY ./target/resenhas-micro-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
