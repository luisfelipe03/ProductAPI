# API de Produtos

## Visão Geral

Esta é uma API REST simples usando Spring Boot para gerenciar produtos. A API oferece suporte a operações CRUD básicas (Criar, Ler, Atualizar, Excluir) para produtos. Ela utiliza o Spring Data JPA para persistência de dados e HATEOAS para fornecer links de hipermídia.

## Controller

### `ProdutcController`

#### `GET /product`

- **Descrição:** Recupera uma lista de todos os produtos.
- **Resposta:**
  - Status: 200 OK
  - Corpo: Lista de produtos com links de hipermídia.

#### `GET /product/{id}`

- **Descrição:** Recupera detalhes de um produto específico por ID.
- **Parâmetros do Caminho:**
  - `id` (Long): ID do produto.
- **Resposta:**
  - Status 200 OK: Detalhes do produto.
  - Status 404 NOT FOUND: Se o produto com o ID fornecido não for encontrado.

#### `POST /product`

- **Descrição:** Cria um novo produto.
- **Corpo da Solicitação:** Dados do produto no formato `ProductRecordDto`.
- **Resposta:**
  - Status: 201 CREATED
  - Corpo: Detalhes do produto criado.

#### `PUT /product/{id}`

- **Descrição:** Atualiza um produto existente por ID.
- **Parâmetros do Caminho:**
  - `id` (Long): ID do produto.
- **Corpo da Solicitação:** Dados atualizados do produto no formato `ProductRecordDto`.
- **Resposta:**
  - Status: 200 OK
  - Corpo: Detalhes do produto atualizado.
  - Status 404 NOT FOUND: Se o produto com o ID fornecido não for encontrado.

#### `DELETE /product/{id}`

- **Descrição:** Exclui um produto por ID.
- **Parâmetros do Caminho:**
  - `id` (Long): ID do produto.
- **Resposta:**
  - Status: 204 NO CONTENT
  - Status 404 NOT FOUND: Se o produto com o ID fornecido não for encontrado.

## Modelo

### `Product`

```java
@Entity
@Table(name = "product")
public class Product extends RepresentationModel<Product> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal value;
}
```

- Atributos:
  - id (Long): Identificador único do produto.
  - name (String): Nome do produto.
  - value (BigDecimal): Valor do produto.
 

## Dependências
- Spring Boot
- Spring Data JPA
- HATEOAS
- Jakarta Validation
  
Como usar:
- Clone o repositório.
- Configure as configurações do seu banco de dados em application.yml.
- Execute a aplicação.
- Acesse a API em http://localhost:8080/product.
  
Sinta-se à vontade para explorar e testar a API usando sua ferramenta preferida (por exemplo, Postman).
