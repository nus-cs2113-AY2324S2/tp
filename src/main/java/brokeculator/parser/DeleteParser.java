package brokeculator.parser;

import brokeculator.command.Command;
import brokeculator.command.DeleteCommand;
import brokeculator.command.InvalidCommand;

public class DeleteParser {
    public static Command parseInput(String userInput) {
        String[] userInputAsArray = userInput.trim().split("\\s+");
        int indexToDelete = 0;
        if (userInputAsArray.length == 1) {
            return new InvalidCommand("Delete index not specified");
        }
        try {
            indexToDelete = Integer.parseInt(userInputAsArray[1]) - 1;
        } catch (NumberFormatException e) {
            return new InvalidCommand("Delete index cannot be non-integer");
        }
        if (indexToDelete < 0) {
            return new InvalidCommand("Delete index must be one or greater");
        }
        return new DeleteCommand(indexToDelete);
    }
}
