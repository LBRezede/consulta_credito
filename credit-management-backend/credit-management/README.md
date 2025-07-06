# Sistema de Gestão de Créditos

API RESTful para consulta de créditos constituídos, desenvolvida com Spring Boot e integrada com PostgreSQL e Kafka para mensageria.

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.5.3
- Spring Data JPA
- PostgreSQL
- Apache Kafka
- Docker e Docker Compose
- JUnit e Mockito para testes

## Pré-requisitos

- Docker e Docker Compose instalados
- Java 21 (para desenvolvimento)
- Maven (para desenvolvimento)

## Estrutura do Projeto

```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/credito/creditmanagement/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
└── pom.xml
```

## Endpoints da API

### Consultar créditos por número de NFS-e
```http
GET /api/creditos/{numeroNfse}
```

Resposta:
```json
[
  {
    "numeroCredito": "123456",
    "numeroNfse": "7891011",
    "dataConstituicao": "2024-02-25",
    "valorIssqn": 1500.75,
    "tipoCredito": "ISSQN",
    "simplesNacional": true,
    "aliquota": 5.0,
    "valorFaturado": 30000.00,
    "valorDeducao": 5000.00,
    "baseCalculo": 25000.00
  }
]
```

### Consultar crédito por número do crédito
```http
GET /api/creditos/credito/{numeroCredito}
```

Resposta:
```json
{
  "numeroCredito": "123456",
  "numeroNfse": "7891011",
  "dataConstituicao": "2024-02-25",
  "valorIssqn": 1500.75,
  "tipoCredito": "ISSQN",
  "simplesNacional": true,
  "aliquota": 5.0,
  "valorFaturado": 30000.00,
  "valorDeducao": 5000.00,
  "baseCalculo": 25000.00
}
```

## Executando o Projeto

1. Clone o repositório:
```bash
git clone <url-do-repositorio>
cd credit-management
```

2. Execute o projeto com Docker Compose:
```bash
docker-compose up -d
```

A aplicação estará disponível em `http://localhost:8080`

## Desenvolvimento

1. Para executar os testes:
```bash
mvn test
```

2. Para construir o projeto:
```bash
mvn clean package
```

## Mensageria com Kafka

O sistema utiliza o Apache Kafka para registrar eventos de consulta. Cada consulta realizada (seja por número de NFS-e ou número de crédito) gera uma mensagem no tópico `consulta-creditos`.

## Banco de Dados

O PostgreSQL é utilizado como banco de dados principal. A estrutura do banco é criada automaticamente através do Hibernate/JPA.

### Acessando o PostgreSQL

- Host: localhost
- Porta: 5432
- Banco: creditodb
- Usuário: postgres
- Senha: postgres

## Contribuição

1. Faça o fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Crie um Pull Request