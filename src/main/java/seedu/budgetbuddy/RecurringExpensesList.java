package seedu.budgetbuddy;

import java.util.ArrayList;

public class RecurringExpensesList {
    protected ArrayList<ExpenseList> recurringExpenses;

    Ui ui = new Ui();
    public RecurringExpensesList() {
        this.recurringExpenses = new ArrayList<>();
    }
    public void addNewRecurringList(String listName) {
        ExpenseList expenses = new RecurringExpenseList(listName, new ArrayList<>());

        recurringExpenses.add(expenses);

    }

    public void printAllRecurringLists() {

        int counter = 1;

        ui.printDivider();
        System.out.println("These are your lists of Recurring Expenses");

        for (ExpenseList expenses : recurringExpenses) {
            String listName = expenses.getName();
            System.out.println(counter + ". " + listName);
            counter += 1;
        }

        ui.printDivider();
    }

}
