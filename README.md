
## 🚀 Visão Geral

Este sistema permite registrar, consultar e visualizar créditos, incluindo buscas por número de NFSe ou número do crédito, com uma API RESTful segura e uma interface amigável.

---

## 🛠️ Tecnologias Utilizadas

### Backend:
- Java 21
- Spring Boot 3.5.3
- Spring Data JPA
- PostgreSQL (ou H2 para testes)
- Spring Web
- Spring Security (se aplicável)
- Docker e Docker Compose (opcional)
- JUnit + Mockito para testes

### Frontend:
- Angular 20.0.5
- TypeScript
- SCSS
- Angular CLI
- Karma + Jasmine para testes

---

## 📋 Pré-requisitos

- Java 21 instalado
- Maven instalado
- Node.js e npm (versão compatível com Angular 20.0.5)
- Docker (opcional, para rodar banco e serviços adicionais)
- IDE de sua preferência (VSCode, IntelliJ, etc.)

---

## 🗂️ Estrutura do Projeto

```
repository-raiz/
│
├── backend/               # Backend em Spring Boot
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile (opcional)
│
├── frontend/              # Frontend em Angular
│   ├── src/
│   ├── angular.json
│   ├── package.json
│   └── ...
```

---

## 🚀 Como Executar

### 1. Clonar o repositório

```bash
git clone https://github.com/SEU_USUARIO/REPOSITORIO.git
cd REPOSITORIO
```

### 2. Rodar o backend

- Navegue até a pasta do backend:

```bash
cd backend
```

- Instale dependências e compile com Maven:

```bash
mvn clean install
```

- Rode a aplicação:

```bash
mvn spring-boot:run
```

ou, se preferir, construa o jar:

```bash
mvn clean package
java -jar target/credit-management-0.0.1-SNAPSHOT.jar
```

### 3. Rodar o frontend

- Navegue até a pasta do frontend:

```bash
cd ../frontend
```

- Instale as dependências:

```bash
npm install
```

- Execute o servidor de desenvolvimento:

```bash
ng serve
```

- Abra o navegador em:

```
http://localhost:4200
```

---

## 📡 Endpoints da API

### Criar Crédito (POST)

```http
POST http://localhost:8080/api/creditos
Content-Type: application/json

{
  "numero": "123",
  "valor": 1000.00
}
```

### Listar créditos (GET)

```http
GET http://localhost:8080/api/creditos
```

### Buscar por NFS-e (GET)

```http
GET http://localhost:8080/api/creditos/nfse/{numeroNfse}
```

### Buscar por número do crédito (GET)

```http
GET http://localhost:8080/api/creditos/credito/{numeroCredito}
```

---

## 💾 Configuração do Banco de Dados

Por padrão, está configurado para usar o **PostgreSQL**:

- Host: localhost
- Porta: 5432
- Banco: creditodb
- Usuário: postgres
- Senha: admin123

Para usar o banco H2 (modo memória):

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

Adicione essa configuração no `application.properties`.

---

## 🧪 Testes

### Backend

```bash
cd backend
mvn test
```

### Frontend

```bash
cd ../frontend
ng test
```

---

## 🛠️ Build

### Backend

```bash
mvn clean package
```

### Frontend

```bash
ng build --prod
```

---

## 🔍 Monitoramento

- Logs do backend:

```bash
docker logs nome_do_container (se usar Docker)
```

- Status dos serviços Docker-compose:

```bash
docker-compose ps
```

---

## 🤝 Contribuição

1. Faça o fork do projeto
2. Crie uma branch para sua feature (`git checkout -b minha-feature`)
3. Commit suas mudanças (`git commit -am 'feat: nova funcionalidade'`)
4. Push para sua branch (`git push origin minha-feature`)
5. Crie um Pull Request

---

