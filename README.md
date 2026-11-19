# Acalento ONG (Java)

Aplicação desktop em JavaFX da PUCPR (2025) para a ONG Acalento. Neste repositório dá para cadastrar, editar, listar e excluir:

- **Eventos**: nome, data, horário, assentamento, capacidade máxima e descrição.
- **Assentamentos**: nome, número de famílias e endereço.
- **Usuários**: dados pessoais, contato e endereço.

Os dados ficam salvos localmente por serialização Java, sem banco de dados: `events.dat`, `settlement.dat` e `user.dat`.

## Contexto

O Projeto Acalento (PUCPR, 2025) apoia a ONG Acalento. A versão principal é a aplicação web em PHP, MySQL e Bootstrap: [Acalento-PUCPR](https://github.com/otavioaugustosw/Acalento-PUCPR). Este repositório é a versão desktop e implementa o CRUD de eventos, assentamentos e usuários.

## Equipe

- Anna Quézia dos Santos
- Laura Rodrigues de Souza de Camargo
- Otávio Augusto Fermino da Silva

## Tecnologias

- Java 24
- JavaFX 21
- Maven, com wrapper incluso
- JUnit 5

## Estrutura

```
src/main/java/com/littlecats/acalentoong/
├── Main.java      # ponto de entrada (JavaFX Application)
├── models/        # entidades (Event, Settlement, User) e persistência em arquivo (*FileHandler)
└── view/          # telas: Home, listagens e formulários de criação/edição
```

## Como rodar

Requisitos: JDK 24.

A classe principal é `com.littlecats.acalentoong.Main`. O `pom.xml` ainda aponta `javafx:run` para `HelloApplication`, que não existe neste código, então informe a classe na linha de comando:

```sh
./mvnw clean javafx:run -Djavafx.mainClass=com.littlecats.acalentoong/com.littlecats.acalentoong.Main
```

No Windows, troque `./mvnw` por `mvnw.cmd`.
