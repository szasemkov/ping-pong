FROM openjdk:17 AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package

FROM openjdk:17
COPY --from=build /app/target/pong-service*.jar /usr/local/lib/pong-service.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/pong-service.jar"]