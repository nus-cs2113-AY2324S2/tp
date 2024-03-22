package seedu.budgetbuddy;

import java.util.ArrayList;

public class RecurringExpenseList extends ExpenseList{
    String name;

    public RecurringExpenseList(String name, ArrayList<Expense> expenses) {
        this.name = name;
        super.expenses = expenses;
    }
}
