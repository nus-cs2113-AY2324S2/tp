package seedu.budgetbuddy.commandcreator;

import seedu.budgetbuddy.ExpenseList;
import seedu.budgetbuddy.command.Command;
import seedu.budgetbuddy.command.SetBudgetCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SetBudgetCommandCreator extends CommandCreator {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ExpenseList expenses;
    public String input;
    public ArrayList<String> expenseCategories;


    public SetBudgetCommandCreator(ExpenseList expenses, String input){
        this.expenses = expenses;
        this.input = input;
        this.expenseCategories = new ArrayList<>(Arrays.asList("Housing", "Groceries", "Utility", "Transport",
                                                                "Entertainment", "Others"));
    }

    private boolean isValidExpenseCategory(String category) {

        assert category != null : "Category should not be null";
        assert !category.isEmpty() : "Category should not be empty";

        for (String validCategory : expenseCategories) {
            if (validCategory.equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }

    public Command handleSetBudgetCommand(ExpenseList expenses, String input) {
        LOGGER.log(Level.INFO, "Entering handleSetBudgetCommand with input: " + input);
        String[] parts = input.split(" ");
        String category = null;
        double budget = -1;

        for (String part : parts) {
            if (part.startsWith("c/")) {
                category = part.substring(2);
                LOGGER.log(Level.INFO, "Category extracted: " + category);
            } else if (part.startsWith("b/")) {
                try {
                    budget = Double.parseDouble(part.substring(2));
                    LOGGER.log(Level.INFO, "Budget extracted: " + budget);
                } catch (NumberFormatException e) {
                    LOGGER.log(Level.SEVERE, "Invalid budget format. Budget should be a number.", e);
                    System.out.println("Invalid budget format. Budget should be a number");
                    return null;
                }
            }
        }

        if (category == null || budget == -1) {
            LOGGER.log(Level.WARNING, "Invalid command format or missing values for category/budget");
            System.out.println("Invalid command format.");
            System.out.println("Expected format: set budget c/<category> b/<budget>");
            return null;
        }

        boolean isValidCategory = isValidExpenseCategory(category);
        if (!isValidCategory) {
            LOGGER.log(Level.WARNING, "Invalid category: " + category);
            System.out.println("Invalid category: " + category);
            System.out.println("Valid categories: Housing, Groceries, Utility, Transport, Entertainment, Others");
            return null;
        }

        LOGGER.log(Level.INFO, "Exiting handleSetBudgetCommand. Command ready for execution.");
        return new SetBudgetCommand(expenses, category, budget);
    }

    @Override
    public Command createCommand(){
        return handleSetBudgetCommand(expenses, input);
    }
}
