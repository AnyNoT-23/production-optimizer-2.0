FROM node:20-alpine AS frontend-build
WORKDIR /frontend
COPY src/main/java/frontend/production-optimizer-frontend/package*.json ./
RUN npm install
COPY src/main/java/frontend/production-optimizer-frontend/ ./
RUN npm run build
 
FROM maven:3.9.6-eclipse-temurin-17 AS backend-build
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline -q
COPY src ./src
COPY --from=frontend-build /frontend/dist ./src/main/resources/static
RUN mvn clean package -DskipTests -Dmaven.compiler.source=15 -Dmaven.compiler.target=15 -q
 
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=backend-build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]