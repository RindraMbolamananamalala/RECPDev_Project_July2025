# Étape 1 : Build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .

# Le build Maven va inclure les JARs système grâce à la modif du pom.xml
RUN mvn clean package -DskipTests

# Étape 2 : Runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# On récupère le JAR généré à l'étape précédente
COPY --from=build /app/target/recp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]