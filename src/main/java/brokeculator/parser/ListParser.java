package brokeculator.parser;

import brokeculator.command.ListCommand;
import seedu.expense.ExpenseManager;

public class ListParser {
    private ExpenseManager expenseManager;

    public ListParser(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
    }

    public ListCommand parseInput(String userInput) {
        String[] userInputAsArray = userInput.split(" ");
        int amountToList = 0;
        if (userInputAsArray.length == 1) {
            // amountToList set to a value that indicates all expenses should be printed
            amountToList = -1;
        } else {
            amountToList = Integer.parseInt(userInputAsArray[1]);
        }

        return new ListCommand(amountToList, expenseManager);
    }
}
