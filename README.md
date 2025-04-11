# 💰 API de Finanças – Spring Boot

API REST desenvolvida com **Spring Boot** para gerenciamento de contas financeiras, oferecendo funcionalidades completas de **CRUD**, integração com serviços externos e arquitetura robusta baseada em boas práticas de engenharia de software. O projeto é inteiramente conteinerizado com Docker e inclui cobertura de testes automatizados com JUnit.

Este projeto integra:
- **Banco de dados relacional (PostgreSQL)** para persistência principal
- **Banco de dados NoSQL (MongoDB)** para armazenamento de logs e eventos
- **Mensageria com RabbitMQ** para processamento assíncrono via filas
- **Envio de e-mails transacionais** via Mailhog (SMTP fake para testes)
- **Arquitetura baseada em DDD (Domain-Driven Design)** para separação clara de responsabilidades

---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3+**
- **Spring Data JPA**
- **Spring Mail**
- **RabbitMQ**
- **MongoDB**
- **JUnit 5**
- **Docker & Docker Compose**
- **Maven**
- **Mailhog (testes de e-mail)**
- **PostgreSQL**

---

## 📁 Estrutura do Projeto

```
apiFinancas/
├── src/
│   ├── main/
│   │   ├── java/           # Código-fonte (controladores, serviços, entidades, repositórios)
│   │   └── resources/      # Configurações, application.properties
│   └── test/java/          # Testes unitários com JUnit
├── Dockerfile              # Container da aplicação
├── docker-compose.yml      # Orquestração com banco de dados
├── pom.xml                 # Dependências e build
└── mvnw                    # Wrapper Maven
```

---

## 🧠 Boas Práticas Aplicadas

### ✅ Princípios SOLID

- **S**ingle Responsibility: cada classe tem uma responsabilidade clara.
- **O**pen/Closed: a estrutura permite novas funcionalidades sem alterar o código base.
- **L**iskov Substitution: os contratos das interfaces são respeitados.
- **I**nterface Segregation: interfaces específicas para o necessário.
- **D**ependency Inversion: uso de injeção de dependência via Spring.

### ✅ Design Patterns

- **Repository Pattern**: abstração de acesso a dados com Spring Data JPA.
- **Service Layer**: encapsula a lógica de negócios.

---

## ⚙️ Como Executar

### ▶️ Com Docker (recomendado)

```bash
git clone https://github.com/renansantanax/apiFinancas.git
cd apiFinancas
docker-compose up --build
```

A API estará disponível em: `http://localhost:8083`

---

## 📌 Endpoints Padrão

| Método | Rota                      | Ação                        |
|--------|---------------------------|-----------------------------|
| GET    | `/tipos/consultar`        | Lista todas as tipos        |
| GET    | `/tipos/obter/{id}`       | Busca tipo por ID           |
| POST   | `/tipos/criar`            | Cria uma nova tipo          |
| PUT    | `/tipos/alterar/{id}`     | Atualiza dados da tipo      |
| DELETE | `/tipos/excluir/{id}`     | Remove um tipo              |



| Método | Rota                        | Ação                        |
|--------|-----------------------------|-----------------------------|
| GET    | `/contas/consultar`         | Lista todas as contas       |
| GET    | `/contas/obter/{id}`        | Busca conta por ID          |
| POST   | `/contas/criar`             | Cria uma nova conta         |
| PUT    | `/contas/alterar/{id}`      | Atualiza dados da conta     |
| DELETE | `/contas/excluir/{id}`      | Remove uma conta            |

---

Os testes cobrem os principais fluxos de negócio e endpoints da aplicação.

---

## 🛠️ Utilidades

- ✅ Validação de dados com @Valid
- ✅ Paginação e filtros dinâmicos
- ✅ Log de operações persistido em MongoDB
- ✅ Mensageria com RabbitMQ
- ✅ Envio de e-mails com Mailhog
- ✅ Integração com base DDD para escalabilidade e organização

---

> Desenvolvido com 💻 por [Renan Santana](https://github.com/renansantanax)

