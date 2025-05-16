FROM maven:3.9.9-eclipse-temurin-21-jammy

WORKDIR /app

COPY . .

RUN mvn clean install

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]