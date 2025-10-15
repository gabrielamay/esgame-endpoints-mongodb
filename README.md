# 🎮 ESGames API -- Sustentabilidade Gamificada

Bem-vindo ao **ESGames**, uma aplicação corporativa que utiliza
gamificação para promover práticas sustentáveis entre colaboradores de
grandes empresas. A plataforma transforma metas de ESG em missões,
rankings, selos e recompensas, criando uma cultura de engajamento
ambiental e social.

## 📘 Contexto Acadêmico

Este projeto foi desenvolvido na **FIAP**, durante o módulo de **DevOps
e Microsserviços**, com o objetivo de aplicar conceitos de:

-   🧩 Arquitetura com Spring Boot e MongoDB\
-   🐳 Containerização com Docker e Docker Compose\
-   🔁 Integração Contínua (CI) e Entrega Contínua (CD) com GitHub
    Actions\
-   ☁️ Deploy e publicação automatizada de imagens Docker

------------------------------------------------------------------------

## ⚙️ Requisitos do Ambiente

Antes de executar o projeto, certifique-se de ter os seguintes recursos
instalados:

-   Java 21 (Temurin ou OpenJDK)
-   Maven 3.9+
-   Docker Desktop
-   Git
-   MongoDB Atlas (Cloud Database)
-   IDE (IntelliJ IDEA ou VS Code)
-   Insomnia (para testar os endpoints da API)

------------------------------------------------------------------------

## 🚀 Etapa DevOps

O projeto **ESGames API** está deployado na nuvem **Microsoft Azure** com pipeline automatizado de CI/CD. Disponibilizamos dois ambientes para consulta e testes das rotas da API:

### 🌐 Ambientes Disponíveis

#### 🟢 **Produção (Production)**
Ambiente estável com a versão mais recente em produção da aplicação.

**Swagger UI - Produção:**  
👉 [https://esgame-prod-fzhbdrc2gkewd9g5.brazilsouth-01.azurewebsites.net/api/swagger-ui/index.html](https://esgame-prod-fzhbdrc2gkewd9g5.brazilsouth-01.azurewebsites.net/api/swagger-ui/index.html#/)

#### 🟡 **Staging (Homologação)**
Ambiente de testes para validação de novas funcionalidades antes do deploy em produção.

**Swagger UI - Staging:**  
👉 [https://esgame-staging-esbjf5fdaxf0enf8.brazilsouth-01.azurewebsites.net/api/swagger-ui/index.html](https://esgame-staging-esbjf5fdaxf0enf8.brazilsouth-01.azurewebsites.net/api/swagger-ui/index.html#/)

### 📝 Como Utilizar

1. **Acesse o link do ambiente desejado** (Produção ou Staging)
2. O **Swagger UI** será aberto diretamente no navegador
3. **Explore os endpoints** disponíveis organizados por tags (Missões, Usuários, Ranking, Selos)
4. **Teste as requisições** diretamente pela interface clicando em "Try it out"
5. Visualize os **schemas**, **parâmetros** e **respostas** de cada endpoint

### ✨ Vantagens do Deploy em Nuvem

✅ **Disponibilidade 24/7** - API acessível a qualquer momento
✅ **Pipeline CI/CD** - Deploy automatizado via GitHub Actions  
✅ **Ambientes isolados** - Staging para testes e Produção para uso real  
✅ **Documentação interativa** - Swagger UI para facilitar integração  

------------------------------------------------------------------------

## 🧩 Arquitetura do Projeto

A aplicação segue uma arquitetura em camadas e utiliza o padrão RESTful
API, com integração ao MongoDB Atlas.

    📦 esgames-endpoints/
    ├── src/
    │   ├── main/java/br/com/fiap/esgames_endpoints/
    │   │   ├── controller/     → Endpoints REST (Missões, Selos, Usuários, Ranking)
    │   │   ├── service/        → Regras de negócio
    │   │   ├── repository/     → Persistência de dados (MongoRepository)
    │   │   └── model/          → Modelos de domínio
    │   └── resources/
    │       └── application.properties
    ├── Dockerfile
    ├── docker-compose.yml
    ├── pom.xml
    └── .github/workflows/github-actions-esgame.yml

------------------------------------------------------------------------

## ☁️ Banco de Dados -- MongoDB Atlas

O projeto utiliza o **MongoDB Atlas**, um banco de dados NoSQL em nuvem,
para garantir escalabilidade e persistência.\
A conexão já está configurada no arquivo `application.properties`:

    spring.data.mongodb.uri=mongodb+srv://gabimay:181114@cluster0.tuwr99s.mongodb.net/esgames_db?retryWrites=true&w=majority
    spring.data.mongodb.database=esgames_db
    spring.data.mongodb.auto-index-creation=true

As coleções principais são:

Coleção                   Descrição
  ------------------------- ----------------------------------------
**usuarios**              Cadastro e autenticação via JWT
**selos**                 Selos de sustentabilidade conquistados
**missoes**               Missões ESG definidas pela empresa
**registros_atividade**   Ações registradas pelos colaboradores
**recompensas**           Benefícios trocados por pontos

------------------------------------------------------------------------

## 🚀 Executando o Projeto Localmente

1️⃣ **Clonar o Repositório**

``` bash
git clone https://github.com/seu-usuario/esgames-endpoints.git
cd esgames-endpoints
```

2️⃣ **Gerar o Build**

``` bash
mvn clean package -DskipTests
```

3️⃣ **Executar a Aplicação**

``` bash
mvn spring-boot:run
```

A aplicação ficará disponível em:\
👉 <http://localhost:8080/api>

------------------------------------------------------------------------

## 📚 Documentação da API -- Swagger/OpenAPI

O projeto possui documentação interativa da API através do **Swagger UI**, que permite:

- 📖 Visualizar todos os endpoints disponíveis
- 🧪 Testar as requisições diretamente pelo navegador
- 📋 Ver os schemas dos DTOs e modelos
- 🔍 Explorar parâmetros, respostas e códigos de status

### Acessando o Swagger

Após iniciar a aplicação, acesse:

**Interface Swagger UI:**\
👉 <http://localhost:8080/api/swagger-ui.html>

**Documentação JSON (OpenAPI):**\
👉 <http://localhost:8080/api/api-docs>

### Endpoints Documentados

A documentação inclui todos os endpoints das seguintes controllers:

- 🎯 **Missões** - CRUD de missões ESG
- 👤 **Usuários** - Gerenciamento de usuários
- 🏆 **Ranking** - Rankings individual e por setor
- 🏅 **Selos** - Gestão de selos e conquistas

------------------------------------------------------------------------

## 🐳 Containerização com Docker

O projeto é totalmente containerizado para rodar em qualquer ambiente
com o Docker instalado.

### 1️⃣ Dockerfile

``` dockerfile
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/esgames-endpoints-0.0.2-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### 2️⃣ docker-compose.yml

``` yaml
version: "3.9"

services:
  mongo:
    image: mongo:7
    container_name: mongo
    restart: always
    ports:
      - "27017:27017"
    volumes:
      - mongo_data:/data/db
    networks:
      - esgames-network

  esgames-api:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: esgames-endpoints
    depends_on:
      - mongo
    ports:
      - "8080:8080"
    environment:
      SPRING_PROFILES_ACTIVE: prod
      SPRING_DATA_MONGODB_URI: mongodb+srv://gabimay:181114@cluster0.tuwr99s.mongodb.net/esgames_db
      JWT_SECRET: esgamesSecretKey2025!@#
    networks:
      - esgames-network

volumes:
  mongo_data:

networks:
  esgames-network:
    driver: bridge
```

### 3️⃣ Subir os containers

``` bash
docker compose up --build
```

Após inicializar, acesse:\
👉 <http://localhost:8080/api>

------------------------------------------------------------------------

## 🧪 Testando no Insomnia

Uma collection do Insomnia está disponível em:\
`src/main/resources/collection`

Ela contém todos os endpoints configurados (usuários, missões, ranking,
selos etc).\
Basta importar no Insomnia e inserir o token JWT retornado no login.

------------------------------------------------------------------------

## ⚡ Pipeline CI/CD -- GitHub Actions + Docker Hub

A automação do ciclo de vida da aplicação é feita pelo **GitHub
Actions**, que:

-   Compila o projeto com Maven\
-   Executa testes automatizados\
-   Faz login no Docker Hub\
-   Constrói e publica a imagem automaticamente

``` yaml
name: 🚀 CI/CD - ESGames API

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  build-and-push:
    runs-on: ubuntu-latest

    steps:
      - name: 📂 Checkout repository
        uses: actions/checkout@v4

      - name: ☕ Setup JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'

      - name: 🧪 Build e Testes
        run: mvn -B clean verify

      - name: 🔐 Login no Docker Hub
        uses: docker/login-action@v3
        with:
          username: ${{ secrets.DOCKERHUB_USERNAME }}
          password: ${{ secrets.DOCKERHUB_TOKEN }}

      - name: 🐳 Build & Push Docker image
        uses: docker/build-push-action@v5
        with:
          context: .
          push: true
          tags: |
            ${{ secrets.DOCKERHUB_USERNAME }}/esgames-api:latest
            ${{ secrets.DOCKERHUB_USERNAME }}/esgames-api:${{ github.run_number }}
```

------------------------------------------------------------------------

## 🧱 Tecnologias Utilizadas

Categoria             Ferramenta
  --------------------- -------------------------
**Linguagem**         Java 21
**Framework**         Spring Boot 3.3
**Banco de Dados**    MongoDB Atlas
**Containerização**   Docker e Docker Compose
**CI/CD**             GitHub Actions
**Deploy**            Docker Hub
**Testes**            JUnit + Mockito
**Autenticação**      JWT
**Build**             Maven

------------------------------------------------------------------------

💡 Dica:\
Para restaurar o ambiente rapidamente, basta executar:

``` bash
docker compose down
docker compose up --build
```

------------------------------------------------------------------------

✨ **Desenvolvido por:**\
- Gabriela May Canarin -- RM 554853\
- Guilherme Marcionilo Pedroso do Rosario Silva -- RM 557115\
- Guilherme Menoti Merli -- RM 556144

📍 **FIAP -- Análise e Desenvolvimento de Sistemas -- Fase 5 (DevOps e
Microsserviços)**\
📅 **Outubro / 2025**
