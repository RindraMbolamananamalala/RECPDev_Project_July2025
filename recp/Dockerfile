# Author : Rindra Mbolamananamalala

# Step 1 : Preparations 1
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Step 2 : Copying source code and resources
COPY . .

# Step 2 : Building the application
RUN mvn clean package -DskipTests

# Step 3 : Preparations 2
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Step 4 : Copying the built jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Step 5 : Copying the SQLite database file (already filled with actual patterns)
COPY --from=build /app/patterndb.db .

# Step 6 : Copying required data directories for pattern search
COPY --from=build /app/PatternsRECPLevelData ./PatternsRECPLevelData
COPY --from=build /app/PatternsRECPLevelData/PatternsDiagramImages/MockRECP/ ./PatternsDiagramImages/

# Step 7 : Exposing the application port (matching the port used in the Spring Boot application)
EXPOSE 8090

# Launch the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]