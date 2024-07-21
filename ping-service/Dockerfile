FROM openjdk:17 AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package

FROM openjdk:17
COPY --from=build /app/target/ping-service*.jar /usr/local/lib/ping-service.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/ping-service.jar"]