Este projeto foi desenvolvido com o objetivo de demonstrar meus conhecimentos práticos em Java e Spring Boot, além de aplicar conceitos de integração com APIs externas, persistência de dados e renderização dinâmica utilizando Thymeleaf.

Este sistema foi proposto para uma apresentação de entrevista, com foco em mostrar o domínio da arquitetura básica de uma aplicação web em Java.

🏗️O projeto segue a arquitetura
  -Controller: Camada responsável por receber as requisições, processar e devolver as respostas adequadas.
  -Entity: Entidades que representam os dados da aplicação e mapeamento com o banco de dados.
  -Service: Contém as regras de negócio, incluindo o consumo da API de cotações.
  -View: Páginas HTML renderizadas com Thymeleaf, responsáveis pela interação com o usuário.
A aplicação também integra o consumo de uma API externa para trazer as moedas disponíveis e realizar as conversões.

## Ferramentas e Tecnologias Utilizadas

- **Java 17**: Linguagem de programação utilizada para o desenvolvimento do backend.
- **Spring Boot 3.4.3**: Framework para desenvolvimento de aplicações Java baseadas em Spring, que facilita a criação de aplicações e microserviços.
- **Thymeleaf**: Motor de templates usado para renderizar as páginas HTML com dados dinâmicos.
- **Spring Data JPA**: Framework para persistência de dados em banco de dados relacionais, utilizado para interagir com o banco de dados H2.
- **H2 Database**: Banco de dados em memória usado para armazenar os registros das conversões.

## Como Rodar o Projeto

🚀 Como Executar
- Clone o repositório
- Configure a API Key da API de cotação no application.properties
- Execute a aplicação via IDE ou mvn spring-boot:run
- Acesse http://localhost:8080/conversao
