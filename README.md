# Challenge API - GpsMottu

Este é um projeto de API em desenvolvimento com **Java** utilizando o framework **Spring Boot**. 
A aplicação é um sistema geral de análise de motos, incluindo rastreamento, associação entre dispositivos qrcode e relatórios.

<img width="929" height="1309" alt="image" src="https://github.com/user-attachments/assets/f8f3572d-fdc5-492c-9b54-a42dbbf1d74d" />


[Challenge Api - Vìdeo demonstrativo](https://www.youtube.com/watch?v=INf0R-hfaD0)

### Equipe


- Gustavo Dias da Silva Cruz - RM556448

- Júlia Medeiros Angelozi - RM556364

- Felipe Ribeiro Tardochi da Silva - RM555100

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Maven**
- **SQL SERVER** 
- **Spring Data JPA**
- **Spring Cache**

Este README descreve passo-a-passo como executar o projeto localmente, utilizando Docker/docker-compose, criar a imagem Docker e também informações sobre o script de deploy para Azure.  
Vou destacar variáveis editáveis importantes (por exemplo: "nomegrupo") encontradas em `deploy.example.sh` e outras configurações.

## Sumário

- Requisitos
- Executar localmente (com SQL Server em Docker)
- Build e execução com JAR
- Build e execução com Docker
- Executando via docker-compose (SQL Server)
- Deploy Azure (script `deploy.example.sh`) — onde alterar `nomegrupo`
- Endpoints úteis

## Requisitos

- Java 21 (recomendado) — o projeto é compilado com Java 21
- Maven (ou use o wrapper `./mvnw` incluído)
- Docker (opcional, para rodar SQL Server e/ou a imagem da API)
- docker-compose (opcional)

## Variáveis de ambiente importantes

O arquivo `src/main/resources/application.properties` usa variáveis de ambiente para a conexão ao banco:

- SPRING_DATASOURCE_URL - JDBC URL (ex.: jdbc:sqlserver://localhost:1433;database=nome-db)
- SPRING_DATASOURCE_USERNAME - usuário do banco
- SPRING_DATASOURCE_PASSWORD - senha do banco

Sem essas variáveis, a aplicação tentará usar Flyway (configurado) e pode falhar na inicialização se não houver um datasource válido.

## Executar localmente (com SQL Server via Docker)

1) Inicie o banco SQL Server usando o `docker-compose.yaml` já presente no repositório:

```bash
# inicia apenas o serviço de banco (mcr.microsoft.com/mssql/server:latest)
docker compose up -d sqlserver
```

O `docker-compose.yaml` do projeto expõe a porta 1433 e já possui uma senha de exemplo. Para conectar a partir da aplicação, defina as variáveis de ambiente necessárias.

2) Exportar variáveis de ambiente (exemplo):

```bash
export SPRING_DATASOURCE_URL="jdbc:sqlserver://localhost:1433;database=javaapidb"
export SPRING_DATASOURCE_USERNAME="sa"
export SPRING_DATASOURCE_PASSWORD="verYs3cret"
```

3) Executar a aplicação com Maven (usar o wrapper `./mvnw`):

```bash
./mvnw spring-boot:run
```

Ou construir o JAR e executar diretamente:

```bash
./mvnw -DskipTests package
java -jar target/java-api-0.0.1-SNAPSHOT.jar
```

Observação: se preferir usar outra base (MySQL, Azure SQL, etc.), ajuste `SPRING_DATASOURCE_URL` e credenciais conforme necessário.

## Build e execução com Docker (imagem da aplicação)

1) Build da imagem (usa `Dockerfile` do projeto):

```bash
docker build -t gp-moto/java-api:latest .
```

2) Executar a imagem e passar as variáveis de ambiente necessárias para a conexão com o banco:

```bash
docker run --rm -d -p 8080:8080 \
  -e SPRING_DATASOURCE_URL="jdbc:sqlserver://host.docker.internal:1433;database=javaapidb" \
  -e SPRING_DATASOURCE_USERNAME=sa \
  -e SPRING_DATASOURCE_PASSWORD=verYs3cret \
  --name api-java gp-moto/java-api:latest
```

Nota: em Linux, `host.docker.internal` pode não resolver; use a rede adequada do Docker ou conecte o container da API à rede do container do SQL Server.

## Executar com docker-compose (API + SQL Server) — exemplo manual

O repositório contém apenas um `docker-compose.yaml` com o serviço `sqlserver`. Para orquestrar API + banco, você pode criar um `docker-compose.override.yml` simples ou rodar o SQL Server com o `docker-compose.yaml` e depois executar a imagem da API ligada à mesma rede.

Exemplo rápido (iniciar SQL Server e, em seguida, executar o JAR localmente):

```bash
docker compose up -d sqlserver
./mvnw -DskipTests package
SPRING_DATASOURCE_URL="jdbc:sqlserver://localhost:1433;database=javaapidb" \
SPRING_DATASOURCE_USERNAME=sa \
SPRING_DATASOURCE_PASSWORD=verYs3cret \
java -jar target/java-api-0.0.1-SNAPSHOT.jar
```

## Deploy no Azure — o que editar (variável "nomegrupo")

O script `deploy.example.sh` contém várias variáveis no topo que **devem ser ajustadas** antes do uso. O projeto usa o placeholder "nomegrupo" em várias variáveis; troque por um identificador do seu time/projeto.

Principais variáveis em `deploy.example.sh` (exemplo):

```bash
RESOURCE_GROUP_NAME="rg-nomegrupo"        # RG para a aplicação web
WEBAPP_NAME="nomegrupo-api"               # nome do WebApp (aplicação)
APP_SERVICE_PLAN="nomegrupo-service-plan" # plano de App Service
RG_DB_NAME="rg-sql-nomegrupo"            # RG do SQL
DB_USERNAME="nomegrupo-user"              # usuário SQL admin (você pode alterar)
DB_NAME="nomegrupo-db"                    # nome do banco
SERVER_NAME="sql-server-nomegrupo-eastus2"# nome do servidor SQL
APP_INSIGHTS_NAME="ai-nomegrupo-adicional"# Application Insights
```

Destaque: a string `nomegrupo` é apenas um exemplo e **deve ser trocada** por um identificador apropriado (por exemplo: `rg-acme`, `acme-api`, etc.). Além disso, ajuste `GITHUB_REPO_NAME` e `BRANCH` para seu repositório/branch reais.

O script também configura as Application Settings do Azure WebApp com as variáveis:

- SPRING_DATASOURCE_URL
- SPRING_DATASOURCE_USERNAME
- SPRING_DATASOURCE_PASSWORD

Como o script cria recursos e configura o deploy via GitHub Actions, revise as variáveis e segredos antes de executar em uma conta/assinatura real.

## Endpoints úteis

- Aplicação Thymeleaf: http://localhost:8080/
- API Root: http://localhost:8080/
- Swagger (OpenAPI): http://localhost:8080/swagger-ui.html

Observação: dependendo da configuração de `application.properties` e das variáveis de ambiente, o H2 pode não ser usado em execução real com SQL Server.

## Dicas rápidas e troubleshooting

- Se a aplicação não subir, verifique os logs (maven ou container) para problemas de conexão com o banco e Flyway.
- Para logs do Docker container:

```bash
docker logs -f api-java
```

- Se usar o SQL Server em container, garanta que a senha atenda às políticas do SQL Server (complexidade e comprimento).
