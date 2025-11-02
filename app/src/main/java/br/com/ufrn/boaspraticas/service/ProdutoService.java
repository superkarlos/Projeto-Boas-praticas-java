package br.com.ufrn.boaspraticas.service;

import br.com.ufrn.boaspraticas.model.Produto;
import br.com.ufrn.boaspraticas.repository.ProdutoRepository;

public class ProdutoService {

    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public void cadastrarProduto(int codigo, String nome, double preco) {
        if (repository.buscarPorCodigo(codigo) != null) {
            System.out.println("Código já existe!");
            return;
        }
        repository.salvar(new Produto(codigo, nome, preco));
    }

    public void listarProdutos() {
        repository.listarTodos().forEach(System.out::println);
    }

    public void adicionarEstoque(int codigo, int quantidade) {
        Produto p = repository.buscarPorCodigo(codigo);
        if (p != null)
            p.adicionarEstoque(quantidade);
        else
            System.out.println("Produto não encontrado!");
    }

    public void relatorioEstoque() {
        System.out.println("\n=== RELATÓRIO DE ESTOQUE ===");
        if (repository.listarTodos().isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto produto : repository.listarTodos()) {
            System.out.printf("Código: %d | Nome: %-25s | Quantidade em estoque: %d%n",
                    produto.getCodigo(), produto.getNome(), produto.getQuantidadeEstoque());
        }
    }
}
