## Autenticação

### Endpoint de login

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

## Requisições GET disponíveis:

#### Listar todos consertos: http://localhost:8080/consertos
#### Conserto específico (com ID, caso ele esteja ativo): http://localhost:8080/consertos/1
#### Listar resumo dos consertos: http://localhost:8080/consertos/resumo
