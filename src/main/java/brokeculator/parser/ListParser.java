package brokeculator.parser;

import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import brokeculator.command.ListCommand;

public class ListParser {
    private static final int LIST_ALL_EXPENSES = -1;
    public static Command parseInput(String userInput) {
        String[] userInputAsArray = userInput.split(" ");
        int amountToList = 0;
        if (userInputAsArray.length == 1) {
            // amountToList set to a value that indicates all expenses should be printed
            amountToList = LIST_ALL_EXPENSES;
        } else {
            try {
                amountToList = Integer.parseInt(userInputAsArray[1]);
            } catch (NumberFormatException e) {
                // amountToList set to a value that indicates all expenses should be printed
                return new InvalidCommand("Invalid integer for list command.");
            }
        }

        return new ListCommand(amountToList);
    }
}
