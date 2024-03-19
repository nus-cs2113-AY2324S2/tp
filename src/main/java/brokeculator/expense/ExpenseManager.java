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

    public double summariseExpenses(int beginIndex, int endIndex) {
        double total = 0;
        ArrayList<Expense> expensesToList = new ArrayList<>(expenses.subList(beginIndex, endIndex));
        for (Expense expense : expensesToList) {
            total += expense.getAmount();
        }
        return total;
    }

    public ArrayList<Expense> listExpenses(int amountToList) {
        if (amountToList == -1) {
            return expenses;
        } else {
            return new ArrayList<>(expenses.subList(0, Math.min(amountToList, expenses.size())));
        }
    }

    public String getExpensesStringRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expenses.size(); i++) {
            sb.append(expenses.get(i).getStringRepresentation());
        }
        return sb.toString();
    }
}
