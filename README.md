# MakeMagicTest

## Fazer o Download e instalar as feramentas

- Git
- Java
- Maven
- Docker

## Iniciando o projeto

### Fazer o Download do Projeto

- git clone https://github.com/deanfreitas/MakeMagicTest.git

### Entrar na pasta do projeto e executar os comandos

- mvn clean install
- docker compose up -d

## Endpoints

### Retornar dados dos Personagens

#### Retornar todos os Personagens

- GET - http://{host}:8080/api/v1/character

#### Retornar Personagens por query parameter

- GET - http://{host}:8080/api/v1/character?house={house}&role={role}&school={school}&house={house}&patronus={patronus}

Não Precisa usar todos os parametros que estão no exemplo

#### Retornar Personagem por id

- GET - http://{host}:8080/api/v1/character/{id}

### Inserir Personagem

- POST - http://{host}:8080/api/v1/character

{
"name": "Harry Potter",
"role": "student",
"school": "Hogwarts School of Witchcraft and Wizardry",
"house": "1760529f-6d51-4cb1-bcb1-25087fce5bde",
"patronus": "stag"
}

Todos os Parametros do exemplo são obrigatorios

### Atualizar Personagem

- PUT - http://{host}:8080/api/v1/character/{id}

{
"name": "Harry Potter",
"role": "student",
"school": "Hogwarts School of Witchcraft and Wizardry",
"house": "1760529f-6d51-4cb1-bcb1-25087fce5bde",
"patronus": "stag"
}

Todos os Parametros do exemplo são obrigatorios

#### Deletar Personagem

- DELETE - http://{host}:8080/api/v1/character/{id}

#### Filtros

Essas opções são baseadas no retorno da API - http://us-central1-rh-challenges.cloudfunctions.net/potterApi/houses

- School
  - "Hogwarts School of Witchcraft and Wizardry"
- house
  - "1760529f-6d51-4cb1-bcb1-25087fce5bde"
  - "542b28e2-9904-4008-b038-034ab312ad7e"
  - "56cabe3a-9bce-4b83-ba63-dcd156e9be45"
  - "df01bd60-e3ed-478c-b760-cdbd9afe51fc"

#### Swagger

No navegador chamar a URL

http://{host}:8080/swagger-ui.html
