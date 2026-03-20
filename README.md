Production Optimizer
Sistema web para gerenciamento de insumos e otimização de produção industrial. A aplicação permite controlar o estoque de matérias-primas e calcular automaticamente quais produtos fabricar para obter o maior valor total de venda com os insumos disponíveis.
🔗 Demo: https://production-optimizer-2-0.onrender.com

Tecnologias

Back-end: Java 15 + Spring Boot 2.7.18
Front-end: Vue.js 3 + Vite
Banco de Dados: PostgreSQL
ORM: Hibernate / JPA
HTTP Client: Axios


Funcionalidades

Cadastro, edição e listagem de Matérias-Primas
Cadastro, edição e listagem de Produtos
Composição de produtos (quais insumos e quantidades são necessários)
Cálculo de produção otimizado: analisa o estoque atual e sugere quais produtos fabricar para maximizar o valor total de venda


Como rodar localmente
Pré-requisitos

Java 15+
Maven 3.8+
Node.js 20+
PostgreSQL rodando localmente

1. Clonar o repositório
bashgit clone https://github.com/AnyNoT-23/production-optimizer-2.0.git
cd production-optimizer-2.0
2. Configurar o banco de dados
Crie um banco no PostgreSQL:
sqlCREATE DATABASE "production-optimizer";
3. Configurar as credenciais locais
Crie o arquivo src/main/resources/application-local.properties:
propertiesspring.datasource.url=jdbc:postgresql://localhost:5432/production-optimizer
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
4. Rodar o back-end
bashmvn spring-boot:run -Dspring-boot.run.profiles=local
O servidor sobe em http://localhost:8080
5. Rodar o front-end (desenvolvimento)
bashcd src/main/java/frontend/production-optimizer-frontend
npm install
npm run dev
O front sobe em http://localhost:5173

Em produção o front é servido diretamente pelo Spring Boot no mesmo endereço.


Estrutura do projeto
src/
├── main/
│   ├── java/
│   │   ├── com/mycompany/production/optimizer/backend/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── frontend/
│   │       └── production-optimizer-frontend/  (Vue.js)
│   └── resources/
│       └── application.properties

Deploy
A aplicação está hospedada no Render com banco PostgreSQL integrado.
Cada push na branch main dispara um novo deploy automaticamente via Docker.