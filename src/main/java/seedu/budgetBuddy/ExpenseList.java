package seedu.budgetbuddy.data;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.budgetbuddy.task.Expense;

public class ExpenseList {
    protected ArrayList <Expense> expenses;
    protected ArrayList<String> categories;

    public ExpenseList() {
        this.expenses = new ArrayList<>();
        this.categories = new ArrayList<>(Arrays.asList("Housing", 
        "Groceries", "Utility", "Transport", "Entertainment", "Others"));
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void addExpense(String category, String amount, String description) {
        int amountInt = Integer.parseInt(amount); 
        Expense expense = new Expense(category, amountInt, description);
        expenses.add(expense);

        if (!categories.contains(category)) {
            categories.add(category);
        }
    }
}
