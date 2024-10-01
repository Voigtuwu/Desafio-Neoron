
# Desafio Neoron

## Descrição

Este é o **Desafio Neoron**, um sistema de gerenciamento de aeroportos e voos. O projeto oferece endpoints para cadastrar, atualizar, deletar e listar aeroportos e voos. Ele foi desenvolvido utilizando **Spring Boot** com **Java 17** e implementa boas práticas de API RESTful, como o uso do padrão DTO e a integração com um banco de dados relacional (PostgreSQL).

## Funcionalidades

- **Aeroportos**
  - Cadastro de aeroportos.
  - Atualização de aeroportos existentes.
  - Deleção de aeroportos por ID.
  - Listagem de aeroportos por ID ou de todos os aeroportos.

- **Voos**
  - Cadastro de voos.
  - Atualização de voos existentes.
  - Deleção de voos por ID.
  - Listagem de voos por ID ou de todos os voos.

## Tecnologias Utilizadas

### Backend

- **Java 17**
- **Spring Boot 3.3.3**
- **Spring Data JPA**
- **PostgreSQL**
- **Swagger** (documentação da API)
- **Maven** (gerenciamento de dependências)
- **H2 Database** (ambiente de desenvolvimento)

### Frontend

O projeto conta com um **frontend** desenvolvido em **React** para consumir os endpoints da API e exibir os dados de aeroportos e voos de forma amigável ao usuário.

- **React 18**
- **Axios** para realizar as requisições HTTP.
- **Styled Components** para estilização da interface.
- **React Router** para navegação entre as páginas.
  

## Pré-requisitos

Antes de começar, você precisará ter instalado em sua máquina:

- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Node.js](https://nodejs.org/) e [npm](https://www.npmjs.com/) (para rodar o frontend)

## Instalação

1. Clone o repositório:

    ```bash
    git clone https://github.com/Voigtuwu/Desafio-Neoron.git
    ```

2. Navegue até o diretório do projeto:

    ```bash
    cd Desafio-Neoron
    ```

### Backend

3. Configure o banco de dados PostgreSQL no arquivo `application.properties` ou `application.yml`:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    ```

4. Execute o projeto com o Maven:

    ```bash
    mvn spring-boot:run
    ```

### Frontend

5. Navegue até a pasta do frontend:

    ```bash
    cd frontend
    ```

6. Instale as dependências do React:

    ```bash
    npm install
    ```

7. Inicie o frontend:

    ```bash
    npm start
    ```

## Documentação da API

A documentação da API foi gerada com o **Swagger**. Para acessá-la, siga os passos:

1. Suba a aplicação.
2. Acesse a URL no seu navegador: `http://localhost:8080/swagger-ui.html`.

Lá você encontrará todos os endpoints disponíveis, bem como exemplos de requisições e respostas.

## Exemplos de Uso

### Listar todos os aeroportos

```bash
GET /api/aeroportos
```

Resposta:

```json
[
  {
    "id": 1,
    "nome": "Aeroporto Internacional",
    "cep": "12345-678",
    "cidade": "São Paulo",
    "estado": "SP"
  }
]
```

### Criar um novo voo

```bash
POST /api/voos
```

Corpo da requisição:

```json
{
  "origem": "Aeroporto Internacional",
  "destino": "Aeroporto de Brasília",
  "dataPartida": "2024-10-01",
  "dataChegada": "2024-10-01",
  "horarioPartida": "2024-10-01T08:30:00",
  "horarioChegada": "2024-10-01T11:30:00"
}
```

## Estrutura do Projeto

```bash
src
├── main
│   ├── java
│   │   └── br.com.neoron
│   │       ├── controller       # Controladores REST
│   │       ├── entity           # Entidades do banco de dados
│   │       ├── repository       # Interfaces de repositórios (DAO)
│   │       └── service          # Serviços da aplicação
│   └── resources
│       ├── application.properties # Configurações do banco de dados
│       └── data.sql             # Dados iniciais
└── test                         # Testes automatizados
```

## Contribuição

Se você quiser contribuir com o projeto, siga os seguintes passos:

1. **Fork** este repositório.
2. Crie uma **branch** com a sua feature: `git checkout -b minha-feature`.
3. Faça o **commit** das suas alterações: `git commit -m 'Minha nova feature'`.
4. Envie para a sua branch: `git push origin minha-feature`.
5. Abra um **Pull Request**.

## Licença

Este projeto está licenciado sob a licença MIT.
