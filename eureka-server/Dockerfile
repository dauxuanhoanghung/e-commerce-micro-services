FROM openjdk:17-jdk-slim as build
WORKDIR /eureka-server

# Copy whole the need files, folders to run
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Runner
FROM eclipse-temurin:17-jre as runner
WORKDIR /app
COPY --from=build /eureka-server/target/*.jar eureka-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]