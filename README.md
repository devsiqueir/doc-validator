# Doc Validator API

API para consulta e validação de documentos brasileiros (CPF/CNPJ), com integração à BrasilAPI, logs de consulta e arquitetura moderna (Hexagonal/Clean Architecture) em Java 17 + Spring Boot.

## Features
- Consulta pública de CNPJ via BrasilAPI (com fallback)
- Mock para consulta de CPF
- Validação robusta de entrada (DTO/Validator)
- Logging de consultas em banco PostgreSQL
- Exposição de API via REST (Spring Web)
- Documentação automática via Swagger/OpenAPI
- Pronto para Docker e cloud (env vars, profiles)
- Arquitetura modular: Adapter/Port/UseCase/Entity

## Stack
- Java 17, Spring Boot 3.3.x, Spring Cloud OpenFeign, PostgreSQL, JPA, Springdoc OpenAPI

## Como rodar localmente

```bash
# Configure as variáveis de ambiente (exemplo para Unix)
export DB_HOST=localhost
export DB_PORT=5432
export DB_NAME=docvalidator
export DB_USER=seu_usuario
export DB_PASSWORD=sua_senha
export BRASILAPI_URL=https://brasilapi.com.br

# Rode a aplicação
./mvnw spring-boot:run
