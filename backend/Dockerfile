# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
# Force cache invalidation
RUN echo "Build timestamp: $(date)" > /tmp/build_timestamp
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests && rm -rf /root/.m2/repository

# Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
