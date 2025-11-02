package br.com.ufrn.boaspraticas.model;

public class Produto {

    private int codigo;
    private String nome;
    private double preco;
    private int quantidadeEstoque;

    public Produto(int codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = 0;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void adicionarEstoque(int quantidade) {
        if (quantidade > 0)
            this.quantidadeEstoque += quantidade;
    }

    public void removerEstoque(int quantidade) {
        if (quantidade > 0 && quantidade <= quantidadeEstoque)
            this.quantidadeEstoque -= quantidade;
    }

    @Override
    public String toString() {
        return String.format("Código: %d | Nome: %s | Preço: R$ %.2f | Estoque: %d",
                codigo, nome, preco, quantidadeEstoque);
    }
}
