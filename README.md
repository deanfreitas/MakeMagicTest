# MakeMagicTest

## Iniciando o projeto

Entrar na pasta do projeto e executar os comandos

- mvn clean install (Precisa ter o Maven instalado)
- docker compose up -d (Precisa ter o docker instalado)

## Endpoints

### Retornar dados dos Personagens

Retornar todos os dados

- GET - http://{host}:8080/api/v1/character

Retornar dados por query parameter

- GET - http://{host}:8080/api/v1/character?house=:house&role=:role

Retornar dados da collection por id

- GET - http://{host}:8080/api/v1/character/:id

### Inserir Personagem

- POST - http://{host}:8080/api/v1/character

{
"name": "Harry Potter",
"role": "student",
"school": "Hogwarts School of Witchcraft and Wizardry",
"house": "1760529f-6d51-4cb1-bcb1-25087fce5bde",
"patronus": "stag"
}

### Atualizar algum Personagem

- PUT - http://{host}:8080/api/v1/character/:id

{
"name": "Harry Potter",
"role": "student",
"school": "Hogwarts School of Witchcraft and Wizardry",
"house": "1760529f-6d51-4cb1-bcb1-25087fce5bde",
"patronus": "stag"
}

#### Deletar algum Personagem

- DELETE - http://{host}:8080/api/v1/character/:id

#### Filtros

- School - "Hogwarts School of Witchcraft and Wizardry"
- house
  - ["1760529f-6d51-4cb1-bcb1-25087fce5bde", "542b28e2-9904-4008-b038-034ab312ad7e", "56cabe3a-9bce-4b83-ba63-dcd156e9be45", "df01bd60-e3ed-478c-b760-cdbd9afe51fc"]
