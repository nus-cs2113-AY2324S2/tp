package brokeculator.parser;

import brokeculator.command.Command;
import brokeculator.command.ViewEventsCommand;

public class ViewEventsParser {
    public static Command parseInput(String userInput) {
        return new ViewEventsCommand();
    }
}
