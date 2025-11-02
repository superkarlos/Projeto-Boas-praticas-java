package br.com.ufrn.boaspraticas;

import br.com.ufrn.boaspraticas.model.TipoVenda;
import br.com.ufrn.boaspraticas.repository.ProdutoRepository;
import br.com.ufrn.boaspraticas.service.ProdutoService;
import br.com.ufrn.boaspraticas.service.VendaService;
import br.com.ufrn.boaspraticas.util.CarregarModel;
import br.com.ufrn.boaspraticas.util.InputUtils;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ProdutoRepository produtoRepo = new ProdutoRepository();
        ProdutoService produtoService = new ProdutoService(produtoRepo);
        VendaService vendaService = new VendaService(produtoRepo);
        CarregarModel carregador = new CarregarModel(produtoService);

        Scanner sc = new Scanner(System.in);
        int opcao = -1;
        carregador.carregarProdutosPadrao();

        do {
            InputUtils.carregarMenuOpcaoes();
            opcao = InputUtils.lerInt(sc, "Escolha: ");

            switch (opcao) {
                case 1 -> {
                    int codigo = InputUtils.lerInt(sc, "Código: ");
                    if (produtoService.buscarPorCodigoProduto(codigo) != null) {
                        System.out.println("Atenção: já existe um produto com o código " + codigo + "!");
                        break;
                    }
                    String nome = InputUtils.lerString(sc, "Nome: ");
                    double preco = InputUtils.lerDouble(sc, "Preço: ");
                    produtoService.cadastrarProduto(codigo, nome, preco);
                }

                case 2 -> produtoService.listarProdutos();

                case 3 -> {
                    int codigo = InputUtils.lerInt(sc, "Código: ");
                    if (produtoService.buscarPorCodigoProduto(codigo) == null) {
                        System.out.println("Produto não encontrado!");
                        break;
                    }
                    int quantidade = InputUtils.lerInt(sc, "Quantidade: ");
                    produtoService.adicionarEstoque(codigo, quantidade);
                }

                case 4 -> {
                    TipoVenda tipo = TipoVenda.valueOf(
                            InputUtils.lerString(sc, "Tipo de venda (LOJA/WEB): ").toUpperCase());

                    String endereco = tipo == TipoVenda.WEB
                            ? InputUtils.lerString(sc, "Endereço: ")
                            : null;

                    int codigoProduto = InputUtils.lerInt(sc, "Código do produto: ");
                    if (produtoService.buscarPorCodigoProduto(codigoProduto) == null) {
                        System.out.println("Produto não encontrado!");
                        break;
                    }

                    int quantidade = InputUtils.lerInt(sc, "Quantidade: ");
                    vendaService.registrarVenda(tipo, endereco, codigoProduto, quantidade);
                }

                case 5 -> vendaService.listarVendas();
                case 6 -> vendaService.gerarRelatorioConsolidado();
                case 7 -> produtoService.relatorioEstoque();

                case 0 -> System.out.println("Sistema encerrado.");
                default -> System.out.println("Atenção: opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}
