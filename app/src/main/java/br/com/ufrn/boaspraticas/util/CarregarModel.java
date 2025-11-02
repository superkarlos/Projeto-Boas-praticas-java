package br.com.ufrn.boaspraticas.util;

import br.com.ufrn.boaspraticas.service.ProdutoService;


public class CarregarModel {

    private ProdutoService produtoService;

    public CarregarModel(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public void carregarProdutosPadrao() {
        produtoService.cadastrarProduto(101, "Arroz 5kg", 25.90);
        produtoService.cadastrarProduto(102, "Feijão Carioca 1kg", 8.50);
        produtoService.cadastrarProduto(103, "Açúcar Refinado 1kg", 4.30);
    }
}