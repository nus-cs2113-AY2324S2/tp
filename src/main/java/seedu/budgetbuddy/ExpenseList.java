package seedu.budgetbuddy;

import java.util.ArrayList;
import java.util.List;

public class ExpenseList {
    protected List<Expense> expenses;
    protected List<String> categories;

    public ExpenseList() {
        this.expenses = new ArrayList<>();
        this.categories = new ArrayList<>();

        // Initialize categories
        categories.add("Housing");
        categories.add("Groceries");
        categories.add("Utility");
        categories.add("Transport");
        categories.add("Entertainment");
        categories.add("Others");
    }

    // Methods to add, edit, delete expenses

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

    public int calculateTotalExpenses() {
        int totalExpenses = 0;
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

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
    public List<Expense> getExpenses() {
        return expenses;
    }
}
