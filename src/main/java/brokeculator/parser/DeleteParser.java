package brokeculator.parser;

import brokeculator.command.DeleteCommand;

public class DeleteParser {
    public static DeleteCommand parseInput(String userInput) {
        String[] userInputAsArray = userInput.trim().split("\\s+");
        int indexToDelete = 0;
        if (userInputAsArray.length == 1) {
            //TODO proper error handling when delete command has no index
            return new DeleteCommand(0);
        } else {
            indexToDelete = Integer.parseInt(userInputAsArray[1]);
        }

        return new DeleteCommand(indexToDelete);
    }
}
