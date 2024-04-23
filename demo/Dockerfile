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
FROM adoptopenjdk/openjdk17:alpine AS builder

# Set up Maven
ENV MAVEN_VERSION 3.9.5
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_URL https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/${MAVEN_VERSION}/apache-maven-${MAVEN_VERSION}-bin.zip

# Install necessary tools
RUN apk --no-cache add curl && \
    curl -fsSL -o /tmp/apache-maven.zip ${MAVEN_URL} && \
    unzip -d /usr/share/maven /tmp/apache-maven.zip && \
    ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn && \
    rm -f /tmp/apache-maven.zip

# Copy the project files and build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Package stage
FROM adoptopenjdk/openjdk17:alpine-jre
COPY --from=builder /app/target/service-1.0-SNAPSHOT.jar /app/service.jar
WORKDIR /app
CMD ["java", "-jar", "service.jar"]
