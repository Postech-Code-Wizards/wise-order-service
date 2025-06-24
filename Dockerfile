# Etapa 1: build
FROM maven:3.9.9-amazoncorretto-21-alpine AS build

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Etapa 2: execução
FROM amazoncorretto:21.0.5-al2023-headless

WORKDIR /app

COPY --from=build /app/target/*-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]