package seedu.budgetbuddy;

import java.util.ArrayList;

public class RecurringExpensesList {
    protected ArrayList<ExpenseList> recurringExpenses;

    public RecurringExpensesList() {
        this.recurringExpenses = new ArrayList<>();
    }
    public void addNewRecurringList(String listName) {
        ExpenseList expenses = new RecurringExpenseList(listName, new ArrayList<>());

        recurringExpenses.add(expenses);

        System.out.println(recurringExpenses);
    }

}
