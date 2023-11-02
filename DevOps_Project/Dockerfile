# Utilisez l'image OpenJDK 11 depuis Docker Hub
FROM openjdk:11

# Exposez le port de votre application Spring Boot (par exemple, 8080)
EXPOSE 8082

# Ajoutez le fichier JAR généré dans le répertoire target
ADD target DevOps_Project-2.1.jar
# Définissez le point d'entrée pour exécuter l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/DevOps_Project-2.1.jar"]
