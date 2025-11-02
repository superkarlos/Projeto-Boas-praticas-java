## Autores
 - ANTONIONNE COELHO PAULINO       - 20250041045
 - JOSE CARLOS DA SILVA NASCIMENTO -  20220031001 

# Sistema de Vendas - Projeto Java

Este projeto implementa um sistema simples de gerenciamento de vendas e estoque de produtos em Java, utilizando princípios de **Clean Code** e **boas práticas de programação orientada a objetos**.  
O sistema permite cadastrar produtos, gerenciar estoque, registrar vendas, e gerar relatórios consolidados e de estoque.

---

##  Estrutura do Projeto


---

##  Descrição das Classes

### 1. `Produto`
Representa um produto com atributos como código, nome, preço e quantidade em estoque.  
Contém métodos de controle de estoque e exibição de informações.  
**Boas práticas aplicadas:**
- Encapsulamento (atributos `private` e acesso via métodos públicos).
- Métodos com nomes descritivos (`adicionarEstoque`, `removerEstoque`).
- Sobrescrita de `toString()` para facilitar a leitura nas listagens.

---

### 2. `ProdutoRepository`
Responsável por armazenar e gerenciar os produtos cadastrados.  
Implementa operações CRUD básicas, como `salvar`, `buscarPorCodigo` e `listarTodos`.

**Boas práticas:**
- Separação de responsabilidades: o repositório lida apenas com dados.
- Uso de `Map<Integer, Produto>` para garantir acesso rápido e único por código.
- Métodos curtos e coesos, com uma única responsabilidade.

---

### 3. `ProdutoService`
Camada de serviço responsável pela lógica de negócios relacionada aos produtos.  
Inclui o cadastro de produtos, listagem e controle de estoque.

**Boas práticas:**
- Validação de duplicidade antes do cadastro.
- Mensagens claras ao usuário.
- Delegação da persistência ao `ProdutoRepository`.
- Método `relatorioEstoque()` para apresentar informações de estoque de forma organizada.

---

### 4. `VendaService`
Gerencia o processo de venda e geração de relatórios.  
Verifica disponibilidade em estoque, registra vendas e calcula totais.

**Boas práticas:**
- Validação de estoque antes da venda.
- Lógica de cálculo de vendas centralizada na classe de serviço.
- Método `gerarRelatorioConsolidado()` mantém separação entre exibição e lógica de negócio.

---

### 5. `CarregarModel`
Classe utilitária usada para carregar produtos iniciais no sistema.  
Facilita testes e demonstrações.

**Boas práticas:**
- Reaproveitamento de código.
- Facilidade de manutenção e testes.
- Clareza na função do método `carregarProdutosPadrao()`.

---

### 6. `InputUtils`
Responsável pela leitura segura de dados via terminal.  
Garante que o programa não quebre em caso de entrada inválida.

**Boas práticas e Clean Code:**
- Tratamento de exceções com `try/catch`.
- Laço `while(true)` para garantir entrada válida.
- Métodos genéricos reutilizáveis (`lerInt`, `lerDouble`, `lerString`).
- Mensagens de erro claras e amigáveis.

---

### 7. `Main`
Classe principal que integra todas as partes do sistema.  
Apresenta o menu de opções e coordena as chamadas aos serviços.

**Boas práticas:**
- Estrutura de menu organizada e legível.
- Uso de `switch` com *arrow syntax* (`->`) para clareza.
- Separação das camadas de entrada, lógica e dados.
- Loop de execução controlado e mensagens padronizadas.

---

##  Princípios de Clean Code Aplicados

- **SRP (Single Responsibility Principle):** cada classe tem uma responsabilidade clara.  
- **DRY (Don't Repeat Yourself):** reutilização de métodos e centralização de lógica.  
- **Coesão:** classes e métodos fazem apenas o que seu nome indica.  
- **Encapsulamento:** acesso controlado a dados e operações.  
- **Nomes Significativos:** classes, métodos e variáveis nomeados de forma clara e objetiva.  
- **Tratamento de Erros:** validações e mensagens de erro explícitas.  
- **Código Legível:** indentação, espaçamento e estruturação padronizada.

---

##  Funcionalidades

1. **Cadastrar Produto**  
   Adiciona novos produtos ao repositório, com código, nome e preço.

2. **Listar Produtos**  
   Exibe todos os produtos cadastrados.

3. **Adicionar Estoque**  
   Permite atualizar a quantidade de produtos no estoque.

4. **Registrar Venda**  
   Registra uma nova venda (LOJA ou WEB) e atualiza o estoque.

5. **Listar Vendas**  
   Mostra todas as vendas registradas.

6. **Relatório Consolidado**  
   Apresenta o total de vendas e o valor movimentado.

7. **Relatório de Estoque**  
   Exibe o estoque atual de cada produto.

---

##  Como Executar

1. Compile o projeto:
   ```bash
   javac -d bin src/**/*.java
## Execute o sistema:
    java -cp bin br.com.ufrn.boaspraticas.Main 
## Tecnologias Utilizadas

- Java 17+
- Paradigma de Programação Orientada a Objetos (POO)
- Boas práticas e Clean Code

