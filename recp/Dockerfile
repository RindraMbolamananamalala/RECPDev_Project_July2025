# Step 1 : General Build of the application
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .

# Step 2 : Build the application
RUN mvn clean package -DskipTests

# Step 3 : Run the application
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Step 4 : Copy the built jar file from the build stage
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "app.jar"]