FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/jewelx-backend.jar jewelx-backend.jar
EXPOSE 8080
CMD ["java","-jar","jewelx-backend.jar"]