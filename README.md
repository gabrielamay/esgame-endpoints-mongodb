# **ESGame** 🎮🍃

Bem-vindo ao **ESGame**, um sistema desenvolvido para grandes empresas que desejam incentivar seus colaboradores a adotarem práticas sustentáveis por meio da gamificação. A proposta é criar uma competição saudável entre os setores e colaboradores dos ambientes corporativos.

---
Este projeto faz parte das atividades do capítulo 8 sobre Microsserviços com Spring Boot. 

## **Instruções para Rodar o Projeto**

Siga os passos abaixo para configurar e rodar o projeto:

1. **Requisitos do Ambiente**:
    - **IDE IntelliJ**
    - **Docker**
    - **Insomnia**
    - **Java 17**
    - **Oracle Database**


## 🔌 Configuração do Banco de Dados

A aplicação **já está** configurada para se conectar ao banco de dados Oracle. Caso precise das credenciais para revisão, seguem abaixo:

- **Usuário:** `RM556144`  
- **Senha:** `090604`

---

## 🐳 Containerizando a Aplicação com Docker

### 1. Verifique o Dockerfile

Antes de iniciar, certifique-se de que o arquivo `Dockerfile` está presente no diretório raiz do projeto. Execute o comando abaixo para listar os arquivos:

```bash
# No Windows
dir

# No Linux/macOS
ls
```

2. Construa a imagem Docker
Com o Docker aberto em sua máquina, execute o seguinte comando para criar a imagem da aplicação:
```bash
docker build -t esgamess:spring-docker .
```

4. Verifique a imagem criada
Para confirmar que a imagem foi criada corretamente, utilize:
```bash
docker image ls
```
Você deverá ver a imagem esgames:spring-docker listada.
![image](https://github.com/user-attachments/assets/29942024-fcd3-48b3-ad85-b2fee078397d)


5. Crie e execute o container
Com a imagem pronta, agora crie um container executando o comando abaixo:
```bash
docker container run --name esgames-endpoints-container -d -p 8080:8080 esgames:spring-docker
```



5. Verifique se o container está em execução
Utilize o seguinte comando para verificar o status do container:
```bash
docker container ls
```
![image](https://github.com/user-attachments/assets/0b20dc98-c011-4ae9-9189-95a20241103f)


Você deverá ver o container esgames-endpoints-container em execução.

---

## 🧩 Flyway, Migrations e Dados Mock

### ⚠️ Limpeza Automática do Banco

Este projeto utiliza o **Flyway** para controle de versões e estruturação do banco de dados.  
No arquivo `FlywayConfig.java`, o método `clean` está ativado, isso significa que:

> **Sempre que a aplicação for iniciada, o banco de dados será limpo (`clean`) e recriado.**

---

## 🧪 Mocks Padrão de Inicialização

Durante a inicialização da aplicação, uma migration executa comandos SQL para popular o banco com **dados simulados (mocks)**. Esses registros já garantem um ambiente funcional para testes.

Abaixo estão os dados criados automaticamente:

### 👤 Usuários Criados

| Nome           | E-mail                | Senha                        | Tipo  | Setor       |
|----------------|------------------------|------------------------------|--------|--------------|
| Administrador  | admin@esgame.com       | `123456` (criptografada)     | ADMIN | FINANCEIRO  |
| Usuário Comum  | usuario@esgame.com     | `123456` (criptografada) | USER  | RH           |

> 💡 A senha exibida no banco está criptografada com BCrypt. Use a senha 123456 nos testes de autenticação via login.

### 🎯 Missão Criada

- **Nome:** Missão Reciclagem
- **Descrição:** Coleta de materiais recicláveis no setor de TI
- **Período:** 01/05/2025 a 31/05/2025
- **Pontos Base:** 10
- **Tipo de Material:** Papel

### ♻️ Material Cadastrado

- **Nome:** Plástico
- **Unidade:** kg
- **Pontos por Unidade:** 10

### 🧑 Representante Cadastrado

- **Nome:** João Silva
- **Email:** joao.silva@email.com
- **Telefone:** (11) 99999-8888
- **Data de Cadastro:** Data atual no momento da criação (SYSDATE)

Esses dados simulam um cenário mínimo para uso dos endpoints da API.  
Caso utilize a rota `POST /ranking/registro-atividade`, você poderá referenciar esses dados diretamente para os testes.


## 🔁 Uso da Rota `/ranking/registro-atividade`

Para utilizar a rota de registro de atividades:

```http
POST /ranking/registro-atividade
```
Certifique-se de que já existam no banco:

Um usuário

Uma missão

Esses dados são exigidos para o correto funcionamento da rota, e já estão incluídos nos mocks carregados automaticamente pelas migrations.

---

## 📂 Collection do Insomnia

Para facilitar os testes dos endpoints da API, o projeto inclui uma **collection do Insomnia** com todas as requisições configuradas.

Você pode acessá-la de duas formas:

1. **Pela plataforma onde foi feito a entrega do projeto plataforma FIAP**
2. **Diretamente no repositório local**, no seguinte caminho:

```bash
/src/main/resources/collection
```