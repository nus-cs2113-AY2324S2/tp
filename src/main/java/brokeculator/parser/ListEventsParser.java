package brokeculator.parser;

import brokeculator.command.Command;
import brokeculator.command.ListEventsCommand;

public class ListEventsParser {
    public static Command parseInput(String userInput) {
        return new ListEventsCommand();
    }
}
