package brokeculator.parser;

import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import brokeculator.command.SummariseCommand;

public class SummariseParser {
    private static final String INVALID_FORMAT_MESSAGE = "Invalid input format for summarise command. "
            + "Please use the format: summarise /from <start> /to <end>";

    public static Command parseInput(String userInput) {
        String[] userInputAsArray = userInput.trim().split("\\s+");
        int beginIndex = 0;
        int endIndex = -1;
        if (userInputAsArray.length == 1) {
            return new SummariseCommand(beginIndex, endIndex);
        }
        if (userInputAsArray.length == 3) {
            if (userInputAsArray[1].equals("/from")) {
                try {
                    beginIndex = Integer.parseInt(userInputAsArray[2]) - 1;
                } catch (NumberFormatException e) {
                    return new InvalidCommand("Start index cannot be non-integer");
                }
                if (beginIndex < 0) {
                    return new InvalidCommand("Start index must be one or greater");
                }
                // endIndex == -1;
                return new SummariseCommand(beginIndex, endIndex);
            } else if (userInputAsArray[1].equals("/to")) {
                // beginIndex = 0;
                try {
                    endIndex = Integer.parseInt(userInputAsArray[2]) - 1;
                } catch (NumberFormatException e) {
                    return new InvalidCommand("End index cannot be non-integer");
                }
                if (endIndex < 0) {
                    return new InvalidCommand("End index must be one or greater");
                }
                return new SummariseCommand(beginIndex, endIndex);
            }
            return new InvalidCommand(INVALID_FORMAT_MESSAGE);
        }
        if (userInputAsArray.length == 5) {
            if (!(userInputAsArray[1].equals("/from")) || !(userInputAsArray[3].equals("/to"))) {
                return new InvalidCommand(INVALID_FORMAT_MESSAGE);
            }
            try {
                beginIndex = Integer.parseInt(userInputAsArray[2]) - 1;
                endIndex = Integer.parseInt(userInputAsArray[4]) - 1;
            } catch (NumberFormatException e) {
                return new InvalidCommand("Start and end index cannot be non-integer");
            }
            if (beginIndex < 0 || endIndex < 0) {
                return new InvalidCommand("Start and end index must be one or greater");
            }
            if (beginIndex > endIndex) {
                return new InvalidCommand("Start index cannot be greater than end index");
            }
            return new SummariseCommand(beginIndex, endIndex);
        }
        return new InvalidCommand(INVALID_FORMAT_MESSAGE);
    }
}
