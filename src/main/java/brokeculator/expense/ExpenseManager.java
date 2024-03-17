package brokeculator.expense;

import java.util.ArrayList;

public class ExpenseManager {
    private final ArrayList<Expense> expenses;

    public ExpenseManager() {
        expenses = new ArrayList<>();
    }

    public ExpenseManager(ArrayList<Expense> expenses) {
        this.expenses = expenses;
    }

    public void add(Expense expense) {
        expenses.add(expense);
    }

    public void delete(int index) {
        expenses.remove(index);
    }

    public double summariseExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    public String getExpensesStringRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expenses.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, expenses.get(i).getStringRepresentation()));
        }
        return sb.toString();
    }
}
