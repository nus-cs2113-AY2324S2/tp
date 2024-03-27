package brokeculator.expense;

import brokeculator.storage.parsing.FileKeyword;
import brokeculator.storage.parsing.SaveableType;

import java.time.LocalDateTime;

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

    public double summariseExpenses(String description, LocalDateTime date, String category,
                                    int beginIndex, int endIndex) {
        double total = 0;
        if (endIndex == -1 || endIndex >= expenses.size()) {
            endIndex = expenses.size() - 1;
        }
        boolean isDescriptionNull = (description == null);
        ArrayList<Expense> expensesToSummarise = new ArrayList<Expense>();
        for (Expense expense : expenses.subList(beginIndex, endIndex + 1)) {
            if (!isDescriptionNull && !expense.getDescription().contains(description)) {
                continue;
            }
            // TODO implement date processing
            // TODO implement category processing
            expensesToSummarise.add(expense);
        }
        for (Expense expense : expensesToSummarise) {
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

    public String getExpensesListString(int amountToList) {
        assert !this.expenses.isEmpty();

        int lastIdxToPrint;
        if (amountToList <= 0 || amountToList > this.expenses.size()) {
            lastIdxToPrint = this.expenses.size();
        } else {
            lastIdxToPrint = amountToList;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lastIdxToPrint; i++) {
            sb.append(i + 1).append(". ").append(expenses.get(i)).append(System.lineSeparator());
        }

        return sb.toString();
    }

    public String getExpensesStringRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (Expense expense : expenses) {
            String currentExpenseString = expense.getStringRepresentation();
            String finalExpenseString = FileKeyword.formatWithKeyword(SaveableType.EXPENSE, currentExpenseString);
            sb.append(finalExpenseString);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getExpensesListString(expenses.size());
    }
    
    public int getNumberOfExpensesTracked() {
        return expenses.size();
    }
}
