package br.com.moneyiteasy;

import br.com.moneyiteasy.service.transaction.RevenueManager;
import br.com.moneyiteasy.service.transaction.ExpenseManager;
import br.com.moneyiteasy.service.user.Login;
import br.com.moneyiteasy.service.user.UserManager;

import java.util.Scanner;

public class FintechApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        Login login = new Login(userManager.getUsers());
        RevenueManager revenueManager = new RevenueManager();
        ExpenseManager expenseManager = new ExpenseManager();
        int op;

        do {
            System.out.println("\nSejam bem-vindo(a) ao Money It Easy! Escolha uma opção: ");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Logar Usuário");
            System.out.println("0 - Sair");
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    userManager.addUser(scanner);
                    break;
                case 2:
                    System.out.println("Digite seu username:");
                    String username = scanner.nextLine();
                    System.out.println("Digite sua senha:");
                    String senha = scanner.nextLine();
                    if (login.validateLogin(username, senha)) {
                        int internalOp;
                        do {
                            System.out.println("1 - Cadastrar Receita");
                            System.out.println("2 - Exibir Receita");
                            System.out.println("3 - Cadastrar Despesa");
                            System.out.println("4 - Exibir Despesa");
                            System.out.println("0 - Sair");
                            internalOp = scanner.nextInt();
                            scanner.nextLine();

                            switch (internalOp) {
                                case 1:
                                    revenueManager.addTransaction(scanner);
                                    break;
                                case 2:
                                    revenueManager.displayTransactions();
                                    break;
                                case 3:
                                    expenseManager.addTransaction(scanner);
                                    break;
                                case 4:
                                    expenseManager.displayTransactions();
                                    break;
                                case 0:
                                    System.out.println("Finalizando a sessão...");
                                    break;
                                default:
                                    System.out.println("Opção inválida!");
                                    break;
                            }
                        } while (internalOp != 0);
                    } else {
                        System.out.println("Usuário ou senha inválidos");
                    }
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
