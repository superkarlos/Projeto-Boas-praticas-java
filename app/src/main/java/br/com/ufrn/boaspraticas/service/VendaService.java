package br.com.ufrn.boaspraticas.service;

import java.util.ArrayList;
import java.util.List;
import br.com.ufrn.boaspraticas.model.ItemVenda;
import br.com.ufrn.boaspraticas.model.Produto;
import br.com.ufrn.boaspraticas.model.TipoVenda;
import br.com.ufrn.boaspraticas.model.Venda;
import br.com.ufrn.boaspraticas.repository.ProdutoRepository;

public class VendaService {
    private List<Venda> vendas = new ArrayList<>();
    private ProdutoRepository produtoRepository;
    private int contadorVendas = 1;

    public VendaService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void registrarVenda(TipoVenda tipo, String endereco, int codigo, int quantidade) {
        Produto produto = produtoRepository.buscarPorCodigo(codigo);
        if (produto == null) {
            System.out.println("Atenção : Produto não encontrado!");
            return;
        }

        if (produto.getQuantidadeEstoque() < quantidade) {
            System.out.println("Atenção : Estoque insuficiente!");
            return;
        }

        Venda venda = new Venda(contadorVendas++, tipo);
        if (tipo == TipoVenda.WEB) venda.setEnderecoEntrega(endereco);

        produto.removerEstoque(quantidade);
        venda.adicionarItem(new ItemVenda(produto, quantidade));
        vendas.add(venda);

        System.out.println(" Venda registrada com sucesso!");
    }

    public void listarVendas() {
        vendas.forEach(System.out::println);
    }

    public void gerarRelatorioConsolidado() {
        System.out.println("\n=== RELATÓRIO CONSOLIDADO ===");
        vendas.stream()
            .flatMap(v -> v.getItens().stream())
            .collect(java.util.stream.Collectors.groupingBy(
                item -> item.getProduto().getNome(),
                java.util.stream.Collectors.summingInt(ItemVenda::getQuantidade)
            ))
            .entrySet().stream()
            .sorted((a, b) -> b.getValue() - a.getValue())
            .forEach(entry ->
                System.out.println(entry.getKey() + " - " + entry.getValue() + " unidades")
            );
    }
}