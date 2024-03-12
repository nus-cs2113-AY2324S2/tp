package seedu.budgetbuddy;

import seedu.budgetbuddy.data.ExpenseList;
import seedu.budgetbuddy.data.SavingList;


public class TaskManager {
    private static ExpenseList expenses = new ExpenseList();
    private static SavingList savings = new SavingList();

    public static void addExpense(String category, String amount, String description) {
        expenses.addExpense(category, amount, description);
    }

    public static void addSaving(String category, String amount) {
        savings.addSaving(category, amount);
    }
}

