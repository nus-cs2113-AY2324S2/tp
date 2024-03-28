package seedu.budgetbuddy.commandcreator;

import seedu.budgetbuddy.ExpenseList;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.DeleteExpenseCommand;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteExpenseCommandCreator extends CommandCreator{
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private ExpenseList expenseList;
    private String input;

    public DeleteExpenseCommandCreator(ExpenseList expenseList, String input){
        this.expenseList = expenseList;
        this.input = input;
    }

    public Command handleDeleteExpenseCommand(ExpenseList expenses, String input) {
        LOGGER.log(Level.INFO, "Processing handleDeleteExpenseCommand");

        assert expenses != null : "Expense list cannot be null";
        assert input != null : "Input string cannot be null";

        String[] parts = input.split("i/", 2);
        // Check if the input format is correct (i.e., contains "i/")
        if (parts.length < 2) {
            LOGGER.log(Level.WARNING, "Invalid command format. Expected format: <command> i/<index>");
            System.out.println("Error: Invalid command format. Expected format: <command> i/<index>");
            return null;
        }

        try {
            int index = Integer.parseInt(parts[1].trim()) - 1;
            // Check if the index is within the bounds of the expense list.
            if (index < 0 || index >= expenses.size()) {
                LOGGER.log(Level.WARNING, "Index is out of bounds.");
                System.out.println("Error: Index is out of bounds.");
                return null;
            }
            LOGGER.log(Level.INFO, "Successfully processed DeleteExpenseCommand");
            // If the index is valid, return a new DeleteExpenseCommand.
            return new DeleteExpenseCommand(expenses, index);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "Index is not a valid number.");
            // Catch the NumberFormatException if the part after "i/" isn't a valid integer.
            System.out.println("Error: Index is not a valid number.");
            return null;
        }
    }

    @Override
    public Command createCommand(){
        return handleDeleteExpenseCommand(expenseList, input);
    }
}
