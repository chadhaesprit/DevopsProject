# Utilisez une image OpenJDK pour exécuter votre application Spring Boot
FROM openjdk:11-jre-slim

# Définissez le répertoire de travail dans le conteneur
WORKDIR /app

# Copiez le jar de votre application dans le conteneur
COPY target/DevOps_Project-1.0.jar app.jar

# Commande pour exécuter l'application Spring Boot
CMD ["java", "-jar", "app.jar"]
