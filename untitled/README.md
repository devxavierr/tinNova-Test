# TinNova - Teste Técnico

Este projeto contém as soluções para o teste técnico da TinNova, dividido em dois módulos principais.

## 📁 Estrutura do Projeto

```
untitled/
├── atividade/          # Questões 2, 3 e 4 (algoritmos)
└── cadastro-veiculos/  # Questão 5 (API RESTful)
```

## 🚀 Como Executar

### Atividades (Questões 2, 3 e 4)

Para rodar as atividades, abra o módulo **"atividade"** e execute a classe `Main`.


### API de Cadastro de Veículos (Questão 5)

Para executar a API de cadastro de veículos:

1. **Primeiro**, rode o `docker-compose.yml` para subir o banco PostgreSQL:

2. **Depois**, rode a aplicação Spring Boot:

## 📖 Documentação da API

Swagger da aplicação disponível em: **http://localhost:8080/swagger-ui.html**

### Endpoints Disponíveis

- `POST /veiculos` - Criar veículo
- `GET /veiculos` - Listar veículos (com filtros opcionais)
- `GET /veiculos/{id}` - Buscar veículo por ID
- `PUT /veiculos/{id}` - Atualizar veículo
- `PATCH /veiculos/{id}` - Atualização parcial (ex: marcar como vendido)
- `DELETE /veiculos/{id}` - Deletar veículo

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.2.0**
- **PostgreSQL**
- **Docker**
- **Swagger/OpenAPI**
- **Arquitetura Hexagonal**