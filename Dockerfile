FROM maven:3.9-eclipse-temurin-21-jammy
WORKDIR /app

# Copia o código-fonte e o arquivo pom.xml
COPY . .

# Realiza o build diretamente na imagem
RUN mvn clean package

EXPOSE 8080

# Define o comando para executar o .jar gerado
CMD ["java", "-jar", "target/java-api-0.0.1-SNAPSHOT.jar"]