# Sistema FitLife — Programação Orientada a Objetos

Sistema desenvolvido em Java para gerenciar clientes e planos de uma academia fictícia chamada FitLife, aplicando conceitos de Programação Orientada a Objetos (POO), persistência de dados e testes de software.

## Objetivo

O projeto tem como objetivo aplicar, na prática, os principais conceitos de POO estudados na disciplina, desenvolvendo uma estrutura organizada, reutilizável e de fácil manutenção.

A implementação contempla o gerenciamento de clientes, a modelagem de diferentes tipos de planos e a lógica de cálculo de mensalidades.

## Tecnologias utilizadas

- **Java 26** — linguagem de programação utilizada no projeto.
- **Quarkus 3.40.1.1** — framework para desenvolvimento da aplicação.
- **Hibernate ORM com Panache** — simplificação do mapeamento objeto-relacional e das operações de persistência.
- **Maven** — gerenciamento de dependências e execução dos testes.

## Conceitos de POO aplicados

### Encapsulamento

Os atributos das classes são protegidos por meio de modificadores de acesso, getters e setters. As regras de validação são utilizadas para impedir a atribuição de dados inválidos, como nomes vazios e idades abaixo do limite estabelecido.

### Herança

A classe abstrata `Plano` serve como base para diferentes tipos de planos, permitindo compartilhar atributos e comportamentos comuns.

Entre as classes da hierarquia estão:

- `PlanoMensal`
- `PlanoPremium`
- `PlanoPersonalizado`

### Abstração

A classe `Plano` representa as características comuns dos planos e define operações abstratas, como `calcularMensalidade()` e `getMeses()`, que devem ser implementadas pelas subclasses.

### Polimorfismo

O método `calcularMensalidade()` permite que cada tipo de plano implemente sua própria regra de cálculo, mantendo uma interface comum para o restante da aplicação.

### Relacionamento entre classes

A classe `Cliente` possui um relacionamento com `Plano` por meio de `@ManyToOne`, permitindo associar clientes aos planos cadastrados. A associação é opcional, conforme a configuração `nullable = true`.

## Estrutura do projeto

A estrutura relevante para as classes discutidas é organizada da seguinte forma:

```text
SistemaFitLife_OrientadaObjetos/
├── src/
│   ├── main/
│   │   ├── docker/
│   │   │   ├── Dockerfile.jvm
│   │   │   ├── Dockerfile.legacy-jar
│   │   │   ├── Dockerfile.native
│   │   │   └── Dockerfile.native-micro
│   │   ├── java/
│   │   │   └── org/acme/
│   │   │       ├── modules/
│   │   │       │   ├── models
│   │   │       │   │   ├── Plano.java
│   │   │       │   │   ├── PlanoMensal.java
│   │   │       │   │   ├── PlanoPremium.java
│   │   │       │   │   ├── PlanoPersonalizado.java
│   │   │       │   │   ├── Cliente.java
│   │   │       │   │   └── Treino.java
│   │   │       │   ├── service
│   │   │       │   │   ├── ClienteService.java
│   │   │       │   │   ├── PagamentoService.java
│   │   │       │   │   ├── PlanoSeed.java
│   │   │       │   │   ├── PlanoService.java
│   │   │       │   │   └── TreinoService.java
│   │   │       └── FitLifeApp.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── org/acme/modules/
│               ├── ClienteTest.java
│               ├── PlanoTest.java
│               ├── ServiceTest.java
│               └── TreinoTest.java
├── README.md
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## Funcionalidades e regras de negócio

### Clientes

A classe `Cliente` representa os clientes da academia e contém os seguintes atributos:

- Identificador único.
- Nome.
- E-mail.
- Idade.
- Plano associado, que pode ser nulo.

As validações implementadas exigem nome e e-mail preenchidos e idade mínima de 15 anos.

### Planos

A classe abstrata `Plano` concentra os atributos comuns dos planos:

- Nome.
- Valor-base.

A classe utiliza `PanacheEntity` para integrar a entidade aos recursos de persistência do Quarkus.

### Pagamentos

A classe `PagamentoService` é utilizada para centralizar operações relacionadas ao processamento de pagamentos. O cálculo do valor deve considerar o método `calcularMensalidade()` do plano associado, respeitando a implementação específica de cada tipo de plano.

## Execução

### Pré-requisitos

- JDK compatível com a versão Java configurada no projeto.
- Docker Desktop

### Executar em modo de desenvolvimento

Na pasta raiz do projeto, execute:

```powershell
./mvnw quarkus:dev
```

## Persistência de dados

O projeto utiliza Hibernate ORM com Panache para facilitar o mapeamento entre entidades Java e tabelas do banco de dados.

As entidades utilizam anotações Jakarta Persistence, como `@Entity`, `@ManyToOne`, `@JoinColumn` e `@Inheritance`.

## Contexto acadêmico

Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos**, com foco na aplicação prática de encapsulamento, herança, abstração, polimorfismo, relacionamentos entre classes, organização do código e testes automatizados.

---

**Projeto:** SistemaFitLife  
**Linguagem:** Java  
**Framework:** Quarkus  
