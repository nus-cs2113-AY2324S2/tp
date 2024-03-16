package seedu.budgetbuddy.command;

import seedu.budgetbuddy.Expense;
import seedu.budgetbuddy.ExpenseList;
import seedu.budgetbuddy.Ui;

import java.util.ArrayList;

public class FindExpensesCommand extends Command {
    private ExpenseList expenses;
    private String description;
    private Double minAmount;
    private Double maxAmount;
    private Ui ui;

    public FindExpensesCommand(ExpenseList expenses, String description, Double minAmount, Double maxAmount) {
        ui = new Ui();
        this.expenses = expenses;
        this.description = description;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    @Override
    public void execute() {
        ArrayList<Expense> filteredExpenses = expenses.filterExpenses(description, minAmount, maxAmount);
        ExpenseList filteredExpensesList = new ExpenseList(filteredExpenses);

        if (filteredExpenses.isEmpty()) {
            ui.printDivider();
            System.out.println("No matching expenses found.");
            ui.printDivider();
        } else {
            ui.printDivider();
            System.out.println("Here are the matching expenses : ");
            filteredExpensesList.listExpenses(null);
            ui.printDivider();
        }
    }
}
