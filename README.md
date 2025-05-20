# Challenge API - GpsMottu

Este é um projeto de API em desenvolvimento com **Java** utilizando o framework **Spring Boot**. 
A aplicação é um sistema geral de análise de motos, incluindo rastreamento, associação entre dispositivos uwb e relatórios.

### Equipe


- Gustavo Dias da Silva Cruz - RM556448

- Júlia Medeiros Angelozi - RM556364

- Felipe Ribeiro Tardochi da Silva - RM555100

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Maven**
- **H2 Database** (banco de dados em memória)
- **Spring Data JPA**
- **Spring Cache**
- **OpenAPI/Swagger** (documentação da API)
- **Mapstruct**

## Configuração do Ambiente

---

## 1. Criando a VM na Azure

Você pode executar com

```bash

chmod +x ./create-vm-azure.sh
./create-vm-azure.sh

```

Ou execute os comandos abaixo: 

```bash
# Crie o grupo de recursos
az group create --location brazilsouth --resource-group rg-gpsmottu

# Crie a VM Ubuntu 22.04
az vm create \
  --resource-group rg-gpsmottu \
  --name vm-ubuntu \
  --image Canonical:0001-com-ubuntu-server-jammy:22_04-lts-gen2:latest \
  --size Standard_B2s \
  --vnet-name nnet-linux \
  --nsg nsgsr-linux \
  --public-ip-address pip-ubuntu \
  --authentication-type password \
  --admin-username admlnx \
  --admin-password Gps@mottu329

# Libere a porta 8080 para acesso externo
az network nsg rule create \
  --resource-group rg-gpsmottu \
  --nsg-name nsgsr-linux \
  --name port_8080 \
  --protocol tcp \
  --priority 1100 \
  --destination-port-range 8080
```

---

## 2. Conectando via SSH

Pegue o IP público da VM no portal Azure ou com:

```bash
az vm show -d -g rg-gpsmottu -n vm-ubuntu --query publicIps -o tsv
```

Conecte:

```bash
ssh admlnx@<IP_DA_VM>
```

---

## 3. Baixando o Projeto do GitHub

```bash
git clone https://github.com/GPMoto/java-api
cd java-api
```

---

## 4. Configurando Docker na VM

Execute com os seguintes comandos:

```bash
chmod +x ./configure-docker.sh
./configure-docker.sh

```

Ou execute esses comandos:

```bash
# Atualize os pacotes
sudo apt-get update

# Instale dependências e Docker
sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "${UBUNTU_CODENAME:-$VERSION_CODENAME}") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y

# Habilite e inicie o serviço Docker
sudo systemctl enable docker
sudo systemctl start docker

# Adicione seu usuário ao grupo docker (relogin necessário)
sudo usermod -aG docker $USER

#Execute para que já faça os efeitos necessários sem sair do terminal
newgrp docker
```



---

## 5. Buildando a Imagem Docker

```bash
docker build -t api-gpsmottu .
```

---

## 6. Executando a API em Container

```bash
docker container run --rm -d -p 8080:8080 --name api-java api-gpsmottu
```

Acesse a API em:  
http://<IP_DA_VM>:8080

Acesse o console H2 em:  
http://<IP_DA_VM>:8080/h2-console

---

## 7. Documentação da API

Acesse:  
http://<IP_DA_VM>:8080/swagger-ui.html


### Informações adicionais

*Ps: Caso queira deletar tudo, execute o seguinte comando na vm:

```bash
az group delete --name rg-gpsmottu --yes --no-wait
```

### Equipe 
- Gustavo Dias da Silva Cruz - RM556448
- Júlia Medeiros Angelozi - RM556364
- Felipe Ribeiro Tardochi da Silva - RM555100


