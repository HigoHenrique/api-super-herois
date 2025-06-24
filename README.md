# API de Super-Heróis

API RESTful desenvolvida como parte de um desafio técnico. A aplicação consiste em um CRUD para gerenciar uma base de dados de super-heróis, construída com Java, Spring Boot e as melhores práticas de mercado para garantir qualidade.

---

## Índice

- [Funcionalidades](#funcionalidades)
- [Boas Práticas e Arquitetura](#boas-práticas-e-arquitetura)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Como Executar o Projeto](#como-executar-o-projeto)
- [Endpoints da API](#endpoints-da-api)

---

## Funcionalidades

- ✅ **Cadastro** de um novo super-herói (`POST /api/herois`)
- ✅ **Listagem** de todos os super-heróis cadastrados (`GET /api/herois`)
- ✅ **Consulta** de um super-herói por ID (`GET /api/herois/{id}`)
- ✅ **Atualização** de informações de um super-herói por ID (`PUT /api/herois/{id}`)
- ✅ **Exclusão** de um super-herói por ID (`DELETE /api/herois/{id}`)
- ✅ **Documentação** interativa e descritiva da API com Swagger/OpenAPI 3.

---

## Boas Práticas e Arquitetura

Este projeto foi desenvolvido com foco na qualidade do código, aplicando os seguintes conceitos profissionais:

* **Arquitetura em Camadas:** O projeto é claramente dividido em `Controller` (camada de API), `Service` (camada de regras de negócio) e `Repository` (camada de acesso a dados), promovendo a separação de responsabilidades e facilitando a manutenção.
* **Padrão DTO (Data Transfer Object):** A comunicação com a API é feita através de DTOs (`HeroiRequestDTO`, `HeroiResponseDTO`, etc.), garantindo que a API não exponha detalhes internos das entidades do banco de dados e permitindo validações de entrada robustas e customizadas.
* **Gerenciamento de Migrações com Flyway:** O schema do banco de dados é versionado e criado automaticamente pelo Flyway na inicialização da aplicação. Isso garante um ambiente de banco de dados consistente e automatizado, eliminando a necessidade de configuração manual por parte de quem for executar o projeto.
* **Tratamento de Exceções Centralizado:** Um `@RestControllerAdvice` (`GlobalExceptionHandler`) captura exceções de negócio e de sistema, retornando respostas de erro HTTP padronizadas, limpas e informativas para o cliente, cobrindo cenários de validação (400), conflito (409), não encontrado (404) e formato de dados inválido.
* **Validação de Dados de Entrada:** Utilização do `jakarta.validation` (`@Valid`) para garantir a integridade dos dados recebidos antes do processamento pela camada de negócio.
* **Segurança de Credenciais:** As credenciais de acesso ao banco de dados não são armazenadas no código-fonte. A aplicação é configurada para lê-las de variáveis de ambiente, uma prática de segurança essencial em qualquer ambiente.

---

## Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.3.1**
* **Spring Web:** Para a construção dos endpoints REST.
* **Spring Data JPA:** Para a persistência de dados.
* **Hibernate:** Como implementação da JPA.
* **MySQL:** Banco de dados relacional.
* **Flyway:** Para o versionamento e migração do banco de dados.
* **Maven:** Para o gerenciamento de dependências e build do projeto.
* **Lombok:** Para reduzir código boilerplate nas entidades e DTOs.
* **Springdoc OpenAPI (Swagger):** Para a documentação da API.

---

## Como Executar o Projeto

Siga os passos abaixo para configurar e executar a aplicação localmente.

#### Pré-requisitos
Antes de começar, você vai precisar ter instalado em sua máquina:
* [Java (JDK) 21](https://www.oracle.com/java/technologies/downloads/#java21)
* [Maven 3.8+](https://maven.apache.org/download.cgi)
* [MySQL Server 8.0+](https://dev.mysql.com/downloads/mysql/)

#### 1. Clone o Repositório
```bash
git clone https://github.com/HigoHenrique/api-super-herois
cd api-super-herois
```

#### 2. Configuração do Ambiente
A aplicação precisa de credenciais para acessar o banco de dados. Existem duas maneiras de configurar isso:

**Opção 1: Usando Variáveis de Ambiente (Abordagem Padrão)**

Esta é a abordagem mais segura e recomendada. Defina as seguintes variáveis no seu sistema operacional ou na configuração de execução da sua IDE:

* `DB_URL`: A URL de conexão JDBC completa.
* `DB_USER`: Seu nome de usuário do MySQL.
* `DB_PASS`: Sua senha do MySQL.

**Exemplo de URL de Conexão para `DB_URL`:**
```
jdbc:mysql://localhost:3306/superheroes_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
```
* `createDatabaseIfNotExist=true`: Cria o banco de dados na primeira conexão, caso ele não exista.
* `useSSL=false`: Desabilita a exigência de uma conexão segura com SSL para facilitar a configuração local.
* `serverTimezone=UTC`: Evita problemas comuns de fuso horário entre a aplicação e o banco.
* `allowPublicKeyRetrieval=true`: Necessário para o driver do MySQL 8+ se autenticar corretamente quando o SSL está desabilitado.

**Opção 2: Edição Direta do `application.properties` (Alternativa para Teste Rápido)**

Para um teste rápido sem configurar variáveis de ambiente, você pode editar os valores padrão diretamente no arquivo `src/main/resources/application.properties`. O Spring usará esses valores se as variáveis de ambiente não forem encontradas.

Basta editar os valores padrão para corresponderem à sua configuração local:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/superheroes_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=seu_usuario_aqui
spring.datasource.password=sua_senha_aqui
```

#### 3. Execute a Aplicação
Com o ambiente configurado, use o Maven para compilar e iniciar a aplicação:

```bash
mvn spring-boot:run
```
A aplicação iniciará e o **Flyway executará automaticamente as migrações** para criar as tabelas e inserir os dados iniciais. A API estará disponível em `http://localhost:8080`.

---

## Endpoints da API

A documentação completa e interativa de todos os endpoints está disponível via Swagger UI. Após iniciar a aplicação, acesse o link abaixo no seu navegador:

- **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**