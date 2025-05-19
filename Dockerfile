# Etapa 1: build (com cache eficiente)
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app

# Copia apenas arquivos necessários para resolver dependências primeiro
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o restante (isso invalida o cache apenas se houver mudança no código)
COPY src ./src
RUN mvn package -DskipTests

# Etapa 2: imagem final mínima (só com o .jar)
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
