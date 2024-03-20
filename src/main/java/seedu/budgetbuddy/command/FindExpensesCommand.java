package seedu.budgetbuddy.command;

import seedu.budgetbuddy.Expense;
import seedu.budgetbuddy.ExpenseList;
import seedu.budgetbuddy.Ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FindExpensesCommand extends Command {
    private static final Logger LOGGER = Logger.getLogger(FindExpensesCommand.class.getName());
    private ExpenseList expenses;
    private String description;
    private Double minAmount;
    private Double maxAmount;
    private Ui ui;

    public FindExpensesCommand(ExpenseList expenses, String description, Double minAmount, Double maxAmount) {
        if (minAmount != null && maxAmount != null) {
            assert minAmount <= maxAmount : "Minimum amount cannot be larger than Maximum Amount";
        }

        ui = new Ui();
        this.expenses = expenses;

        if(description == null) {
            this.description = "";
        }
        else {
            this.description = description;
        }
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    @Override
    public void execute() {

        LOGGER.log(Level.INFO, "Start processing of Find Command");

        if (minAmount != null && maxAmount != null) {
            assert minAmount <= maxAmount : "Minimum amount cannot be larger than Maximum Amount";
        }

        LOGGER.log(Level.INFO, "Creating filteredExpenses");
        ArrayList<Expense> filteredExpenses = expenses.filterExpenses(description, minAmount, maxAmount);
        ExpenseList filteredExpensesList = new ExpenseList(filteredExpenses);

        if (filteredExpenses.isEmpty()) {
            LOGGER.log(Level.INFO, "filtered expenses is empty, returning no expenses found");

            ui.printDivider();
            System.out.println("No matching expenses found.");
            ui.printDivider();
        } else {
            LOGGER.log(Level.INFO, "Filtered expenses contains items, returning matching expenses");

            ui.printDivider();
            System.out.println("Here are the matching expenses : ");
            filteredExpensesList.listExpenses(null);
            ui.printDivider();
        }
    }
}
