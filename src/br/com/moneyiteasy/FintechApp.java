package br.com.moneyiteasy;

import br.com.moneyiteasy.model.User;
import br.com.moneyiteasy.service.RevenueManager;
import br.com.moneyiteasy.service.ExpenseManager;

import java.util.Scanner;

public class FintechApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        User usuario = null;
        RevenueManager revenueManager = new RevenueManager();
        ExpenseManager expenseManager = new ExpenseManager();
        int op;

        do {
            System.out.println("\nSejam bem-vindo(a) ao Money It Easy! Escolha uma opção: ");
            System.out.println("1-Cadastrar Usuário: ");
            System.out.println("2-Exibir Usuário: ");
            System.out.println("3-Cadastrar Receita: ");
            System.out.println("4-Exibir Receita: ");
            System.out.println("5-Cadastrar Despesa: ");
            System.out.println("6-Exibir Despesa: ");
            System.out.println("0-Sair");
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    if (usuario == null) {
                        System.out.println("Cadastro de Usuário.");
                        usuario = new User();
                        usuario.createUser(scanner);
                    } else {
                        System.out.println("Usuário já cadastrado.");
                    }
                    break;
                case 2:
                    if (usuario != null) {
                        usuario.displayUser();
                    } else {
                        System.out.println("Nenhum usuário cadastrado!");
                    }
                    break;
                case 3:
                    revenueManager.addRevenue(scanner);
                    break;
                case 4:
                    revenueManager.displayRevenues();
                    break;
                case 5:
                    expenseManager.addExpense(scanner);
                    break;
                case 6:
                    expenseManager.displayExpenses();
                    break;
                case 0:
                    System.out.println("Finalizando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (op != 0);

        scanner.close();
    }
}