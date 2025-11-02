package br.com.ufrn.boaspraticas.util;

import java.util.Scanner;

public class InputUtils {

    public static int lerInt(Scanner sc, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String entrada = sc.nextLine().trim();
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println(" Valor inválido. Digite um número inteiro.");
            }
        }
    }

    public static double lerDouble(Scanner sc, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String entrada = sc.nextLine().trim().replace(",", ".");
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número decimal (ex: 10.50).");
            }
        }
    }

    public static String lerString(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        return sc.nextLine().trim();
    }

    public static void carregarMenuOpcaoes(){
        System.out.println("\n=== SISTEMA DE VENDAS ===");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Adicionar Estoque");
            System.out.println("4 - Registrar Venda");
            System.out.println("5 - Listar Vendas");
            System.out.println("6 - Relatório Consolidado");
            System.out.println("7 - Relatório de Estoque");
            System.out.println("0 - Sair");
    }
}
