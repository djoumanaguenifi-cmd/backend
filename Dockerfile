# Utiliser une image Java officielle
FROM eclipse-temurin:17-jdk

# Définir le répertoire de travail
WORKDIR /app

# Copier le code source
COPY . .

# Builder avec Maven Wrapper
RUN ./mvnw -DskipTests clean package

# Exposer le port
EXPOSE 8080

# Lancer l'application
CMD ["java", "-jar", "target/*.jar"]
