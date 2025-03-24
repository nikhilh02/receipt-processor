FROM openjdk:17-jdk-slim as build

WORKDIR /app

COPY pom.xml .

RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/receipt-processor-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
