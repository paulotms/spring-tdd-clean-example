# Visão geral

O projeto é uma aplicação back-end feita em  [Spring Boot](https://projects.spring.io/spring-boot) com objetivo de demonstrar e exemplificar a Clean Architecture criada por Uncle Bob, onde foi primeiramente apresentada em um blog em 2012. Outra metodologia utilizada foi o Test Driven Development (TDD) que é uma técnica de desenvolvimento onde o caso de teste é feito antes da implementação da regra de negócio via código.

## Tecnologias

- [Spring Boot](https://projects.spring.io/spring-boot) é um framework open-source que auxilia e simplifica a configuração de applicações Java, utilizando dependências auto-configuráveis e um servidor Web já embutido (Tomcat), ajudando e muito na produtividade de criação de software.
 
- [Junit](https://junit.org/junit5/) é um framework open-source que suporta a criação de testes automatizados em Java.

- [Mockito](https://site.mockito.org/) é um framework open-source que suporta a criação de dublês (mocks) nos testes automatizados.

 
# Setup da aplicação (local)

## Pré-requisito

É necessário garantir que as seguintes dependências estejam corretamente instaladas:
```
Java 11
Maven 3.8.7
```

## Instalação da aplicação

1- Faça o clone do repositório:
```
https://github.com/paulotms/spring-tdd-clean-example.git
```
2- Acesse o projeto:
```
cd spring-tdd-clean-example
```
3- Compile o código e baixe as dependências do projeto:
```
mvn clean package
```
4- Inicie a aplicação:
```
mvn spring-boot:run
```
Feito. A aplicação estará disponível em http://localhost:8080
```
Tomcat started on port(s): 8080 (http)
Started SpringTddCleanExampleApplication in xxxx seconds (JVM running for xxxx)
```
