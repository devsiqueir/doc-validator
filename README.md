![JWT Auth](https://img.shields.io/badge/Auth-JWT-green?style=flat-square)

# Doc Validator API

![GitHub last commit](https://img.shields.io/github/last-commit/rsiqueira011/doc-validator)
![GitHub license](https://img.shields.io/github/license/rsiqueira011/doc-validator)
![GitHub issues](https://img.shields.io/github/issues/rsiqueira011/doc-validator)

> API para consulta e validação de documentos brasileiros (CPF/CNPJ), com integração à BrasilAPI, logs de consulta e arquitetura moderna (Hexagonal/Clean Architecture) em Java 17 + Spring Boot.

---

## ✨ Features

- Consulta pública de **CNPJ** via BrasilAPI (com fallback automático)
- Mock para consulta de **CPF**
- **Validação robusta** de entrada (DTO/Validator + CnpjValidator)
- **Logging** de consultas em banco PostgreSQL (JPA)
- Exposição de API via **REST** (Spring Web)
- **Documentação automática** via Swagger/OpenAPI
- Pronto para Docker, cloud, ambientes com variáveis
- Arquitetura modular: Adapter/Port/UseCase/Entity (Hexagonal)

---

## 🔒 Autenticação JWT

- Para acessar os endpoints protegidos, você deve:
  1. Registrar um usuário via `/api/v1/auth/register`
  2. Fazer login via `/api/v1/auth/login`
  3. Usar o token JWT retornado no header Authorization:
  
```http
Authorization: Bearer {seu_token}

## 🛠️ Stack

- **Java 17**
- **Spring Boot 3.3.x**
- **Spring Cloud OpenFeign**
- **PostgreSQL**, **JPA**
- **Springdoc OpenAPI** (Swagger)

---

## 🚀 Como rodar localmente

```bash
# Configure as variáveis de ambiente (Unix)
export DB_HOST=localhost
export DB_PORT=5432
export DB_NAME=docvalidator
export DB_USER=seu_usuario
export DB_PASSWORD=sua_senha
export BRASILAPI_URL=https://brasilapi.com.br

# Rode a aplicação
./mvnw spring-boot:run

## Melhorias Futuras

- Implementar consulta real de CPF (integração com fonte oficial)
- Autenticação e autorização de usuários
- Deploy automatizado (CI/CD)
- Dockerização completa para ambientes produtivos
- Cobertura de testes > 80% (SonarQube ou Jacoco)
- Monitoramento e métricas de uso
- Versão para ambiente cloud (AWS/Azure)

Contribuições são bem-vindas! Sugira novas features via [issues](https://github.com/rsiqueira011/doc-validator/issues)


