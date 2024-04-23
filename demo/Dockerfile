# # Build stage
# FROM maven:3.8.3-openjdk-17 AS builder
# RUN mkdir /build
# COPY . /build
# WORKDIR /build
# RUN mvn clean package -DskipTests
#
# # Package stage
# FROM adoptopenjdk/openjdk11:alpine-jre
# RUN mkdir /app
# COPY --from=builder /build/target/service-1.0-SNAPSHOT.jar /app/service.jar
# WORKDIR /app
# CMD ["java","-jar","service.jar"]
# Use the base Maven image
# Use the official Maven image as the builder stage
# Use the official Maven image as the builder stage
# Use the official Maven image as the builder stage
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Copy the project files into the container
COPY . /app
WORKDIR /app

# Build the project using Maven
RUN mvn clean package -DskipTests

# Use a lightweight base image for the final stage
FROM openjdk:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage to the final stage
COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar /app/demo.jar

# Expose the port that the application runs on
EXPOSE 8080

# Specify the command to run the application
CMD ["java", "-jar", "demo.jar"]

