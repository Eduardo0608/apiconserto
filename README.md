## Autenticação

### Endpoint de login (necessário se autenticar para fazer requisições):

```makefile
POST http://localhost:8080/auth/login
```

### Corpo da requisição:

```json
{
  "login": "admin",
  "senha": "123456"
}
```

### Resposta esperada:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
```

## Requisições GET:

#### Listar todos consertos: http://localhost:8080/consertos
#### Conserto específico (com ID, caso ele esteja ativo): http://localhost:8080/consertos/{id}
#### Listar resumo dos consertos: http://localhost:8080/consertos/resumo

## Requisição POST:

#### Cadastrar um conserto: http://localhost:8080/consertos

### Corpo da requisição:

```json
{
    "dataEntrada": "01/01/2025",
    "dataSaida": "12/01/2025",
    "mecanico": {
        "nome": "Han Solo",
        "anosDeExperiencia": "9"
    },
    "veiculo": {
        "marca": "Star Wars",
        "modelo": "Millennium Falcon",
        "ano": "1977",
        "cor": "Cinza"
    },
    "ativo": true
}
```

## Requisição PUT:

#### Atualizar um conserto: http://localhost:8080/consertos/{id}

```json
{
    "dataEntrada": "01/01/2025",
    "dataSaida": "12/01/2025",
    "mecanico": {
        "nome": "Han Solo",
        "anosDeExperiencia": "9"
    },
    "veiculo": {
        "marca": "Star Wars",
        "modelo": "X Wing",
        "ano": "1977",
        "cor": "Preta"
    },
    "ativo": true
}
```

## Requisição DELETE:

#### Apagar um conserto: http://localhost:8080/consertos/{id}
