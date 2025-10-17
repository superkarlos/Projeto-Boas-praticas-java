package br.com.ufrn.boaspraticas.util;

import java.util.Scanner;

public final class Console {
    private static final Scanner SC = new Scanner(System.in);

    private Console() {
    }

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return SC.nextLine().trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = SC.nextLine().trim();
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }
}
