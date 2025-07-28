# Serviço REST para Contas a Pagar

## Descrição

Este projeto implementa um Serviço REST para gerenciar contas a pagar, aplicando regras de negócio específicas para cálculo de multas e juros em caso de atraso no pagamento.

---

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Maven** 
- **Flyway** (Migração e versionamento do banco de dados)
- **JUnit**
- **Docker** 

---

## Funcionalidades

### 1. Inclusão de Conta a Pagar

Permite cadastrar uma nova conta a pagar com os seguintes dados obrigatórios:

| Campo           | Tipo     | Descrição               |
|-----------------|----------|-------------------------|
| Nome            | Texto    | Nome da conta           |
| Valor Original  | Numeral  | Valor original da conta |
| Data de Vencimento | Data   | Data limite para pagamento |
| Data de Pagamento | Data    | Data em que foi pago    |

Durante a inclusão, o sistema verifica se a conta está em atraso e calcula multa e juros conforme regras descritas abaixo.

---

### 2. Listagem das Contas Cadastradas

Retorna a lista de contas com os seguintes dados:

| Campo                 | Tipo     | Descrição                               |
|-----------------------|----------|---------------------------------------|
| Nome                  | Texto    | Nome da conta                         |
| Valor Original        | Numeral  | Valor original da conta               |
| Valor Corrigido       | Numeral  | Valor final com multa e juros aplicados |
| Quantidade de Dias de Atraso | Numeral  | Dias de atraso no pagamento          |
| Data de Pagamento     | Data     | Data em que a conta foi paga          |

---

## Regras de Negócio

- Todos os campos são obrigatórios.
- O sistema ira calcular uma multa e um Juros caso a conta esteja em atraso, considerando a diferença entre a data de pagamento e a data de vencimento.

| Dias em Atraso | Multa | Juros por Dia |
|----------------|-------|---------------|
| Até 3 dias     | 2%    | 0,1%          |
| Superior a 3 dias | 3%  | 0,2%          |
| Superior a 5 dias | 5%  | 0,3%          |

- Tanto a quantidade de dias, quanto a multa e os juros por dias são persistidos na Tabela Penalty. já os dados de da conta ficam na table Bill.

# Endpoints da API

### POST /bill

**Descrição:**  
Cadastra uma nova conta a pagar,se estiverem atrasadas aplica as regras de multa e juros em caso de atraso.

**Request Body (JSON):**

```json
{
  "nome": "string",
  "valorOriginal": 100.00,
  "dataVencimento": "yyyy-MM-dd'T'HH:mm:ss",
  "dataPagamento": "yyyy-MM-dd'T'HH:mm:ss"
}
```
### GET /bill
**Descrição:**  
Lista todas as Contas que tem, seja com multa ou sem multa.

```json
[
  {
    "nome": "Conta de Luz",
    "valorOriginal": 150.00,
    "valorCorrigido": 155.25,
    "diasAtraso": 5,
    "dataPagamento": "2025-08-19T15:30:00"
  },
  {
    "nome": "Internet",
    "valorOriginal": 100.00,
    "valorCorrigido": null,
    "diasAtraso": null,
    "dataPagamento": "2025-08-19T15:30:00"
  }
]
```
##  Fluxo da Arquitetura

 A arquitetura do projeto segue uma abordagem limpa e organizada, com as responsabilidades definidas em camadas. Essas seriam as principais camadas da aplicação ate a persistencia:
---

### Camadas Principais

| Camada            | Componentes            | Responsabilidade                                                                                 |
|-------------------|-----------------------|------------------------------------------------------------------------------------------------|
| **Application**   | UseCase               | Orquestra a lógica da aplicação. Recebe requisições dos adaptadores de entrada e coordena as operações, aplicando regras de negócio usando a camada de domínio. |
| **Domain**        | Service, Entity       | Contém as regras de negócio e modelos de domínio puros. `Entity` representa os dados e comportamentos principais. `Service` encapsula as regras de negócio da aplicação. |
| **Adapters Input**| REST Controller       | Responsável por receber e traduzir requisições externas (HTTP) para chamadas da camada Application (UseCase). |
| **Adapters Output**| Gateway (interface), GatewayImpl, Repository (interface), RepositoryImpl, JpaRepository | Abstrai a persistência e acesso a dados. Implementa a comunicação com banco de dados e outros sistemas externos. |

---
## Instruções para Execução

### Requesitos

- Java 21
- PostgreSQL
- Maven
- Docker & Docker Compose

### Como executar localmente
- Certifique-se de que as portas `5432` (PostgreSQL) e `8080` (aplicação) estão livres.

1. No terminal, navegue até a raiz do projeto e execute:

    ```sh
    docker-compose up --build
    ```
2. A aplicação estará disponível em:  
   🌐 [http://localhost:8080](http://localhost:8080/swagger-ui/index.html)

3. banco de dados PostgreSQL estará acessível em:  
   🛢️ `localhost:5432`
