package brokeculator.parser;

import brokeculator.command.AddEventCommand;
import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import brokeculator.parser.util.Keyword;
import brokeculator.parser.util.OrderParser;

public class EventParser {
    private static final Keyword[] KEYWORDS = {
        new Keyword(" /n ", "Event name", false),
        new Keyword(" /d ", "Event description", false)
    };
    public static Command parseInput(String userInput) {
        String[] userInputs;
        try {
            userInputs = OrderParser.parseOrder(userInput, KEYWORDS);
        } catch (Exception e) {
            return new InvalidCommand(e.getMessage());
        }
        String eventName = userInputs[0];
        String eventDescription = userInputs[1];
        return new AddEventCommand(eventName, eventDescription);
    }
}
