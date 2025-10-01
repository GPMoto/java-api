# Challenge API - GpsMottu


<img width="929" height="1309" alt="image" src="https://github.com/user-attachments/assets/f8f3572d-fdc5-492c-9b54-a42dbbf1d74d" />


[Challenge Api - Vìdeo de deploy na AZURE - DEVOPS](https://www.youtube.com/watch?v=iPmjPbi-ZJg)

[Challenge Api - Vìdeo demonstrativo - JAVA ADVANCED](https://youtu.be/ir8tQdRJ4yk)

## Descrição da Solução:
O Java API - Gestão de Motos é uma aplicação desenvolvida em Java com foco em auxiliar operadores e gestores de filiais no controle e monitoramento de motos, funcionários e setores da empresa.
O sistema oferece funcionalidades para:
-	Gestão de motos: cadastro, atualização de status e rastreamento em diferentes filiais.
-	Controle via QR Code: associação de motos a QR Codes, permitindo rápida identificação e verificação no momento de entrada, saída ou movimentação.
-	Mapeamento de setores e filiais: estruturação de áreas internas da empresa para facilitar a localização e organização.
-	Gestão de funcionários: registro de colaboradores vinculados a cada filial, integrando informações relevantes para a operação.

## Benefícios para o Negócio
Com essas funcionalidades, a empresa consegue implementar um controle mais eficiente sobre as motos e sua localização, alcançando objetivos estratégicos como:
-	Redução de perdas e prejuízos: evitar que motos sejam extraviadas ou deixem de ser monitoradas.
-	Melhor comunicação interna: facilitar a troca de informações sobre a frota entre funcionários e gestores.
-	Maior rastreabilidade: garantir que cada moto esteja sempre associada a uma filial, setor e responsável.


### Equipe


- Gustavo Dias da Silva Cruz - RM556448 - 2TDSPH

- Júlia Medeiros Angelozi - RM556364 - 2TDSPH

- Felipe Ribeiro Tardochi da Silva - RM555100 - 2TDSPH

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Maven**
- **Flyway SQLSERVER**
- **Thymeleaf**
- **SQL SERVER** 
- **Spring Data JPA**
- **Spring Cache**

Este README descreve passo-a-passo como executar o projeto localmente, utilizando Docker/docker-compose, criar a imagem Docker e também informações sobre o script de deploy para Azure.  


## Sumário

- Requisitos
- Executar localmente (com SQL Server em Docker)
- Build e execução com JAR
- Build e execução com Docker
- Executando via docker-compose (SQL Server)
- Deploy Azure (script `deploy.sh`)
- Endpoints úteis

## DevOps

1. Baixe o arquivo deploy.sh

2. Mande para o CloudShell da Azure

3. Execute `chmod +x ./deploy.sh`

4. Execute o script `./deploy.sh`

5. Ao aparecer o link da verificação do Github, faça o processo de verificação com código

6. Vá em Settings do projeto > Secrets > Actions e adicione as seguintes credenciais

```bash
SPRING_DATASOURCE_USERNAME=gpsmottu-admin
SPRING_DATASOURCE_PASSWORD=Gps@M0ttu!#
SPRING_DATASOURCE_URL=(capture o valor do azure sql server)

```
Os valores de username e password estarão no script como `gpsmottu-admin` e `Gps@M0ttu!#`, já a url deve ser captura à partir do banco na azure.

7. Edite o yaml(workflow) gerado pela azure para colocar as seguintes variáveis de ambiente:

```
env: 
  SPRING_DATASOURCE_URL: ${{ secrets.SPRING_DATASOURCE_URL }}
  SPRING_DATASOURCE_USERNAME: ${{ secrets.SPRING_DATASOURCE_USERNAME }}
  SPRING_DATASOURCE_PASSWORD: ${{ secrets.SPRING_DATASOURCE_PASSWORD }}
```

8. Dê commit e push das alterações

9. Ao término das operações acima, deve-se realizar os seguintes testes com CURL ou Swagger (abaixo seguem-se testes com curl).

Autenticação 
```bash
curl -X GET "http://gpsmottu-api.azurewebsites.net/api/autenticacao/login?username=Admin%20SP&password=admin123"
```

Pegue o token e insira-o nas seguintes requisições.

```bash
curl -X POST "http://gpsmottu-api.azurewebsites.net/api/moto" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN_AQUI>" \
  -d '{
    "status": "Disponível",
    "condicoesManutencao": "Saudável",
    "idTipoMoto": 1,
    "idSecaoFilial": 1,
    "identificador": "LADF2345"
  }'
```
Teste no banco

```sql
SELECT * FROM [dbo].[t_gp_mottu_moto];
```

Get by id

```bash
curl -X GET "http://gpsmottu-api.azurewebsites.net/api/moto/{ID_DA_MOTO}" \
  -H "Authorization: Bearer <TOKEN_AQUI>"
```

Teste no banco

```sql
SELECT * FROM [dbo].[t_gp_mottu_moto];
```

Update

```bash
curl -X PUT "http://gpsmottu-api.azurewebsites.net/api/moto/" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN_AQUI>" \
  -d '{
    "idMoto": <ID_DA_MOTO>,
    "status": "Manutenção",
    "condicoesManutencao": "Em manutenção",
    "idTipoMoto": 1,
    "idSecaoFilial": 1,
    "identificador": "LADF2345"
  }'
```
Teste no banco

```sql
SELECT * FROM [dbo].[t_gp_mottu_moto];
```


Delete

```bash
curl -X DELETE "http://gpsmottu-api.azurewebsites.net/api/moto/{ID_DA_MOTO}" \
  -H "Authorization: Bearer <TOKEN_AQUI>"
```
Teste no banco

```sql
SELECT * FROM [dbo].[t_gp_mottu_moto];
```


Adicione um país

```bash
curl -X POST "http://gpsmottu-api.azurewebsites.net/api/pais/" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN_AQUI>" \
  -d '{
    "nmPais": "Austrália"
  }'
```

Teste no banco

```sql
SELECT * FROM [dbo].[t_gp_mottu_pais];
```

Get by id

```bash
curl -X GET "http://gpsmottu-api.azurewebsites.net/api/pais/{ID_DA_MOTO}" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN_AQUI>" \
```
Teste no banco

```sql
SELECT * FROM [dbo].[t_gp_mottu_pais];
```

Alterar um país


```bash
curl -X POST "http://gpsmottu-api.azurewebsites.net/api/pais/" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN_AQUI>" \
  -d '{
    "idPais": <ID_PAIS>,
    "nmPais": "Austrália"
  }'
```

Teste no banco

```sql
SELECT * FROM [dbo].[t_gp_mottu_pais];
```

```bash
curl -X DELETE "http://gpsmottu-api.azurewebsites.net/api/pais/{ID_MOTO}" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN_AQUI>" \
```

Teste no banco

```sql
SELECT * FROM [dbo].[t_gp_mottu_pais];
```


## Executar localmente (com SQL Server via Docker)

1) Inicie o banco SQL Server usando o `docker-compose.yaml` já presente no repositório:

```bash
# inicia apenas o serviço de banco (mcr.microsoft.com/mssql/server:latest)
docker compose up -d sqlserver
```

O `docker-compose.yaml` do projeto expõe a porta 1433 e já possui uma senha de exemplo. Para conectar a partir da aplicação, defina as variáveis de ambiente necessárias.

2) Exportar variáveis de ambiente (exemplo):

```bash
export SPRING_DATASOURCE_URL="jdbc:sqlserver://localhost:1433;database=master"
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

