package br.com.ufrn.boaspraticas;

import java.math.BigDecimal;
import java.util.*;
import br.com.ufrn.boaspraticas.model.Product;
import br.com.ufrn.boaspraticas.model.Sale;
import br.com.ufrn.boaspraticas.repository.ProductRepository;
import br.com.ufrn.boaspraticas.service.ProductService;
import br.com.ufrn.boaspraticas.service.SaleService;
import br.com.ufrn.boaspraticas.util.Console;

public class App {

    private static final ProductRepository productRepository = new ProductRepository();
    private static final ProductService productService = new ProductService(productRepository);
    private static final SaleService saleService = new SaleService(productRepository);

    public static void main(String[] args) {
        seedData();
        showMainMenu();
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=== SISTEMA PDV ===");
            System.out.println("1) Cadastrar produto");
            System.out.println("2) Listar produtos   ");
            System.out.println("3) Entrada no estoque");
            System.out.println("4) Registrar venda   ");
            System.out.println("5) Listar vendas");
            System.out.println("6) Relatório consolidado");
            System.out.println("7) Relatório de estoque");
            System.out.println("0) Sair");

            int option = Console.readInt("Escolha uma opção: ");
            try {
                switch (option) {
                    case 1 -> cadastrarProduto();
                    case 2 -> listarProdutos();
                    case 3 -> entradaEstoque();
                    case 4 -> registrarVenda();
                    case 5 -> listarVendas();
                    case 6 -> relatorioConsolidado();
                    case 7 -> relatorioEstoque();
                    case 0 -> sair();
                    default -> System.out.println("⚠ Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println(" Erro: " + e.getMessage());
            }
        }
    }


    private static void cadastrarProduto() {
        String code = Console.readLine("Código do produto: ");
        String name = Console.readLine("Nome do produto: ");
        BigDecimal price = new BigDecimal(Console.readLine("Preço unitário: "));
        productService.registerProduct(code, name, price);
        System.out.println(" Produto cadastrado com sucesso!");
    }

    private static void listarProdutos() {
        List<Product> produtos = productService.listProducts();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        produtos.forEach(p ->
                System.out.printf("%-6s | %-15s | Preço: %-6s | Estoque: %d%n",
                        p.getCode(), p.getName(), p.getPrice(), p.getQuantity())
        );
    }

    private static void entradaEstoque() {
        String code = Console.readLine("Código do produto: ");
        int quantidade = Console.readInt("Quantidade a adicionar: ");
        productService.addStock(code, quantidade);
        System.out.println(" Estoque atualizado com sucesso!");
    }

    private static void registrarVenda() {
        Map<String, Integer> itens = new HashMap<>();

        System.out.println("\n--- REGISTRO DE VENDA ---");
        while (true) {
            String code = Console.readLine("Código do produto (ou ENTER para finalizar): ");
            if (code.isBlank()) break;
            int qty = Console.readInt("Quantidade: ");
            itens.merge(code, qty, Integer::sum);
        }

        if (itens.isEmpty()) {
            System.out.println("Nenhum item informado. Venda cancelada.");
            return;
        }

        Sale sale = saleService.registerSale(itens);
        System.out.println(" Venda registrada!");
        System.out.println("ID: " + sale.getId());
        System.out.println("Total: R$ " + sale.getTotal());
    }

    private static void listarVendas() {
        List<Sale> vendas = saleService.listSales();
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }
        System.out.println("\n--- LISTA DE VENDAS ---");
        vendas.forEach(v ->
                System.out.printf("Venda #%d | Data: %s | Total: R$ %s%n",
                        v.getId(), v.getCreatedAt(), v.getTotal())
        );
    }

    private static void relatorioConsolidado() {
        var report = saleService.consolidatedReport();
        System.out.println("\n--- RELATÓRIO CONSOLIDADO ---");
        report.getLines().forEach(l ->
                System.out.printf("%s | %-15s | qtd=%d | total=%s%n",
                        l.getCode(), l.getName(), l.getQuantity(), l.getTotal())
        );
        System.out.println("---------------------------------");
        System.out.printf("Itens vendidos: %d | Valor total: %s%n",
                report.getTotalItems(), report.getTotalValue());
    }

    private static void relatorioEstoque() {
        List<Product> produtos = productService.listProducts();
        System.out.println("\n--- RELATÓRIO DE ESTOQUE ---");
        produtos.forEach(p ->
                System.out.printf("%s | %-15s | Estoque: %d%n",
                        p.getCode(), p.getName(), p.getQuantity())
        );
    }

    private static void sair() {
        System.out.println(" Encerrando o sistema. Até logo!");
        System.exit(0);
    }


    private static void seedData() {
        try {
            productService.registerProduct("P001", "Caneta", new BigDecimal("2.50"));
            productService.registerProduct("P002", "Caderno", new BigDecimal("15.00"));
            productService.registerProduct("P003", "Borracha", new BigDecimal("1.75"));

            productService.addStock("P001", 100);
            productService.addStock("P002", 50);
            productService.addStock("P003", 200);
        } catch (Exception ignored) {
        }
    }
}
