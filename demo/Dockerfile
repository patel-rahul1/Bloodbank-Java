# Build stage
FROM maven:3.8.3-openjdk-17 AS builder
RUN mkdir /build
COPY . /build
WORKDIR /build
RUN mvn clean package -DskipTests

# Package stage
FROM adoptopenjdk/openjdk17:alpine-jre
RUN mkdir /app
COPY --from=builder /build/target/service-1.0-SNAPSHOT.jar /app/service.jar
WORKDIR /app
CMD ["java","-jar","service.jar"]
