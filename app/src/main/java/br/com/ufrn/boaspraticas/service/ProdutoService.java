package br.com.ufrn.boaspraticas.service;

import br.com.ufrn.boaspraticas.model.Produto;
import br.com.ufrn.boaspraticas.repository.ProdutoRepository;

public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto buscarPorCodigoProduto(int codigo) {
        return repository.buscarPorCodigo(codigo);
    }

    public void cadastrarProduto(int codigo, String nome, double preco) {
        Produto produtoExistente = buscarPorCodigoProduto(codigo);

        if (produtoExistente != null) {
            System.out.println("Atenção: já existe um produto com o código " + codigo + "!");
            return;
        }

        Produto novoProduto = new Produto(codigo, nome, preco);
        repository.salvar(novoProduto);
        System.out.println("Produto cadastrado com sucesso: " + nome);
    }

    public void listarProdutos() {
        if (repository.listarTodos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        repository.listarTodos().forEach(System.out::println);
    }

    public void adicionarEstoque(int codigo, int quantidade) {
        Produto produto = repository.buscarPorCodigo(codigo);

        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        produto.adicionarEstoque(quantidade);
        System.out.println("Estoque atualizado para o produto: " + produto.getNome());
    }

    public void relatorioEstoque() {
        System.out.println("\n=== RELATÓRIO DE ESTOQUE ===");

        if (repository.listarTodos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto produto : repository.listarTodos()) {
            System.out.printf("Código: %d | Nome: %-25s | Quantidade: %d%n",
                    produto.getCodigo(), produto.getNome(), produto.getQuantidadeEstoque());
        }
    }
}
