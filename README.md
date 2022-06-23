# Gerenciamento de Porto

### Resumo
Esta API gerencia contêineres, clientes e as movimentações dos contêineres.
Cada conteiner está associado a um cliente e pode possuir diversas movimentações. 
Ao final a API traz um relatório agrupado por tipo de movimentação e clientes, além do sumário de quantos contêineres são de importações e quantos são de exportações.
As dúvidas e solicitações devem ser enviadas para o e-mail gmtb.tec@gmail.com

## Tecnologias utilizadas
* Java
* Spring Boot
* JPA/Hibernate
* Mysql
* Flyway
* ModelMapper
* Rest Assured

## Recursos disponíveis para acesso via API:
* [**Clientes**]
* [**Contêiner**]
* [**Movimentação**]

## Métodos
Requisições para a API devem seguir os padrões:
| Método | Descrição |
|---|---|
| GET | Retorna informações de um ou mais registros |
| POST | Utilizado para criar um novo registro |
| PUT | Atualiza dados de um registro ou altera sua situação |
| DEL | Remove um registro do sistema |

## Respostas 
| Código | Descrição |
|---|---|
| 200 | Requisição executada com sucesso |
| 201 | Incluído no Banco de Dados com sucesso |
| 400 | Erros de validação |
| 404 | Registro pesquisado não encontrado |
| 409 | O registro não pode ser deletado pois viola regra de negócio |
| 500 | Erro de Sistema. Contactar administrador do sistema |

## Requisições

### Cliente
 * Get por cliente http://localhost:8080/cliente/{clienteId}
 * Get (listar todos) http://localhost:8080/cliente
 * Post http://localhost:8080/cliente
 * Del http://localhost:8080/cliente/{clienteId}
 * Put http://localhost:8080/cliente/{clienteId}

### Contêiner
* Get por conteiner http://localhost:8080/conteiner/{conteinerId}
* Get (listar todos) http://localhost:8080/conteiner
* Post http://localhost:8080/conteiner
* Del http://localhost:8080/conteiner/{conteinerId}
* Put http://localhost:8080/conteiner/{conteinerId}

### Movimentação
* Get por movimentacao http://localhost:8080/movimentacao/{movimentacaoId}
* Get (listar todos) http://localhost:8080/movimentacao
* Post http://localhost:8080/movimentacao
* Del http://localhost:8080/movimentacao/{movimentacaoId}
* Put http://localhost:8080/movimentacao/{movimentacaoId}
* Put (Finalizar Movimentação) http://localhost:8080/movimentacao/{movimentacaoId}

### Relatórios
* http://localhost:8080/relatorios/resumomovimentacao

## Testes automatizados implementados

### Cliente
* Retorno de status http 200 quando consultar clientes
* Retorno de status http 404 quando cliente não encontrado
* Retorno de status http 201 quando cliente cadastrado

### Contêiner
* Retorno de status http 200 quando consultar contêineres
* Retorno de status http 404 quando contêiner não encontrado
* Retorno de status http 400 quando tentar gravar conteiner com identificação em descacordo
* Retorno de status http 201 quando tentar gravar conteiner com identificação de acordo

### Movimentação
* Retorno de status http 200 quando consultar movimentações
* Retorno de status http 404 quando movimentação não encontrada
* Retorno de status http 201 quando movimentação cadastrada
* Retorno de status http 400 quando tentar gravar movimentação com tipo de movimentação em desacordo


