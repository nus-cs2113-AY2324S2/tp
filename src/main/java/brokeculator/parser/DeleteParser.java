package brokeculator.parser;

import brokeculator.command.Command;
import brokeculator.command.DeleteCommand;
import brokeculator.command.InvalidCommand;

public class DeleteParser {
    public static Command parseInput(String userInput) {
        String[] userInputAsArray = userInput.trim().split("\\s+");
        int indexToDelete = 0;
        if (userInputAsArray.length == 1) {
            return new InvalidCommand("Please specify an index to delete." + System.lineSeparator()
                    + "Format: delete <index>");
        }
        try {
            indexToDelete = Integer.parseInt(userInputAsArray[1]);
        } catch (NumberFormatException e) {
            return new InvalidCommand("The delete index must be an integer.");
        }
        if (indexToDelete < 0) {
            return new InvalidCommand("The delete index cannot be negative integer");
        }
        return new DeleteCommand(indexToDelete);
    }
}
