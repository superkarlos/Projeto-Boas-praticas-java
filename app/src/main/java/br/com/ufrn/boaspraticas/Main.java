package br.com.ufrn.boaspraticas;

import br.com.ufrn.boaspraticas.model.TipoVenda;
import br.com.ufrn.boaspraticas.repository.ProdutoRepository;
import br.com.ufrn.boaspraticas.service.ProdutoService;
import br.com.ufrn.boaspraticas.service.VendaService;
import br.com.ufrn.boaspraticas.util.CarregarModel;
import br.com.ufrn.boaspraticas.util.InputUtils;
/**
 * ✅ BOAS PRÁTICAS:
 * - Classe única para interação com o usuário.
 * - Fluxo limpo e organizado com switch-case.
 * - Nenhuma regra de negócio implementada aqui (seguindo o princípio MVC).
 */import java.util.Scanner;

import javax.swing.text.Utilities;

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
                case 1 -> produtoService.cadastrarProduto(
                        InputUtils.lerInt(sc, "Código: "),
                        InputUtils.lerString(sc, "Nome: "),
                        InputUtils.lerDouble(sc, "Preço: "));

                case 2 -> produtoService.listarProdutos();

                case 3 -> produtoService.adicionarEstoque(
                        InputUtils.lerInt(sc, "Código: "),
                        InputUtils.lerInt(sc, "Quantidade: "));

                case 4 -> {
                    TipoVenda tipo = TipoVenda.valueOf(
                            InputUtils.lerString(sc, "Tipo de venda (LOJA/WEB): ").toUpperCase());

                    String endereco = tipo == TipoVenda.WEB
                            ? InputUtils.lerString(sc, "Endereço: ")
                            : null;

                    vendaService.registrarVenda(
                            tipo,
                            endereco,
                            InputUtils.lerInt(sc, "Código do produto: "),
                            InputUtils.lerInt(sc, "Quantidade: "));
                }

                case 5 -> vendaService.listarVendas();
                case 6 -> vendaService.gerarRelatorioConsolidado();
                case 7 -> produtoService.relatorioEstoque();

                case 0 -> System.out.println(" Sistema encerrado.");
                default -> System.out.println("Atencao : Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}
