# Challenge API - GpsMottu

Este é um projeto de API em desenvolvimento com **Java** utilizando o framework **Spring Boot**. 
A aplicação é um sistema geral de análise de motos, incluindo rastreamento, associação entre dispositivos uwb e relatórios.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Maven**
- **H2 Database** (banco de dados em memória)
- **Spring Data JPA**
- **Spring Cache**
- **OpenAPI/Swagger** (documentação da API)
- **Spring Security**

## Configuração do Ambiente

### Pré-requisitos

- **Java 21**
- **Maven** instalado

### Configuração do Banco de Dados

O projeto utiliza o banco de dados em memória **H2**. As configurações estão no arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:gpmoto
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Acesse o console do H2 em: http://localhost:8080/h2-console.

### Como Executar o Projeto
1. Clone o repositório:

```shell
git clone https://github.com/GPMoto/java-api.git
cd challenge-api
```

2. Compile e execute o projeto:

```shell
mvn spring-boot:run
```

A API estará disponível em: http://localhost:8080.


### Endpoints Principais
- Uwb
  - GET /uwb - Lista todos os Uwb.
  - GET /uwb/{id} - Busca um Uwb pelo ID.
  - POST /uwb - Cria um novo Uwb.
  - PUT /uwb/{id}/associar/{idMoto} - Associa um Moto a um Uwb.
  - PUT /uwb/{id}/desassociar - Desassocia um Moto de um Uwb.
  - DELETE /uwb/{id} - Deleta um Uwb.
  
- Moto
  - GET /moto - Lista todas as Moto.
  - GET /moto/{id} - Busca uma Moto pelo ID.
  
### Documentação da API
A documentação da API está disponível em: http://localhost:8080/swagger-ui.html.


### Estrutura do Projeto
- `src/main/java/gp/moto/challenge_api` - Código-fonte principal.
- `src/main/resources` - Arquivos de configuração.
- `src/test` - Testes automatizados.

