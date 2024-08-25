package br.com.moneyiteasy.service;

import br.com.moneyiteasy.model.Expense;
import br.com.moneyiteasy.model.Revenue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpenseManager {
    List<Expense> expenses = new ArrayList<>();

    public void addExpense(Scanner scanner) {
        System.out.println("Digite a categoria da receita: ");
        String category = scanner.nextLine();
        System.out.println("Digite o valor da receita: ");
        double value = scanner.nextDouble();
        scanner.nextLine();

        Expense expense = new Expense(category, value);
        expenses.add(expense);
    }

    public double getTotalExpense() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getValue();
        }
        return total;
    }

    public void displayExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("Nenhuma receita cadastrada.");
        } else {
            System.out.println("Receitas cadastradas:");
            for (Expense expense : expenses) {
                System.out.printf("Categoria: %s | Valor: R$ %.2f\n", expense.getCategory(), expense.getValue());
            }
            System.out.printf("Total de Receitas: R$ %.2f\n", getTotalExpense());
        }
    }
}

