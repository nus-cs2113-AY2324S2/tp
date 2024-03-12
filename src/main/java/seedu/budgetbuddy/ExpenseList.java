package seedu.budgetbuddy;

import java.util.ArrayList;
import java.util.Arrays;

public class ExpenseList {
    protected ArrayList <Expense> expenses;
    protected ArrayList<String> categories;

    public ExpenseList() {
        this.expenses = new ArrayList<>();
        this.categories = new ArrayList<>(Arrays.asList("Housing", 
        "Groceries", "Utility", "Transport", "Entertainment", "Others"));
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
