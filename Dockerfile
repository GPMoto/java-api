FROM maven:3.9.11-eclipse-temurin-21-noble

WORKDIR /app

# Copia tudo para dentro do container
COPY . .

# Comando para rodar o jar gerado
CMD ["mvn", "spring-boot:run"]
