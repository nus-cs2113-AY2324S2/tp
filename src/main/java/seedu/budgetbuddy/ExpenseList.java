package seedu.budgetbuddy;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class ExpenseList {
    protected ArrayList <Expense> expenses;
    protected ArrayList<String> categories;

    public ExpenseList() {
        this.expenses = new ArrayList<>();
        this.categories = new ArrayList<>(Arrays.asList("Housing", 
        "Groceries", "Utility", "Transport", "Entertainment", "Others"));
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void listExpenses(String filterCategory) {
        System.out.println("Expenses:");
        for (int i = 0; i < expenses.size(); i++) {
            Expense expense = expenses.get(i);
            if (filterCategory == null || expense.getCategory().equalsIgnoreCase(filterCategory)) {
                System.out.print(i+1 + " | ");
                System.out.print("Date: " + expense.getDateAdded() + " | ");
                System.out.print("Category: " + expense.getCategory() + " | ");
                System.out.print("Amount: $" + expense.getAmount() + " | ");
                System.out.println("Description: " + expense.getDescription() + " | ");
            }
        }
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Total Expenses: $" + calculateTotalExpenses());

    }

    public double calculateTotalExpenses() {
        double totalExpenses = 0;
        for (Expense expense: expenses) {
            if (expense.getAmount() < 0) {
                try {
                    throw new Exception("Expenses should not be negative");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                totalExpenses += expense.getAmount();
            }
        }
        return totalExpenses;
    }
    public void addExpense(String category, String amount, String description) {
        int amountInt = Integer.parseInt(amount); 
        Expense expense = new Expense(category, amountInt, description);
        expenses.add(expense);

        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    public void editExpense(String category, int index, double amount, String description) {
        int categoryIndex = categories.indexOf(category);
        if (categoryIndex != -1 && index > 0 && index <= expenses.size()) {
            Expense expenseToEdit = expenses.get(index - 1);
            expenseToEdit.setCategory(category);
            expenseToEdit.setAmount(amount);
            expenseToEdit.setDescription(description);
            System.out.println("Expense edited successfully.");
        } else {
            System.out.println("Invalid category or index.");
        }
    }
    public void deleteExpense(int index){
        if (index >= 0 && index < expenses.size()){
            expenses.remove(index);
        } else {
            System.out.println("Invalid expense index.");
        }
    }
}
