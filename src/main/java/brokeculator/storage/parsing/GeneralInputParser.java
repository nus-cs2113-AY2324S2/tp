package brokeculator.storage.parsing;
import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;

public class GeneralInputParser {
    public GeneralInputParser() {
        // TODO
    }
    public Command getCommandFromUserInput(String userInput) {
        //TODO
        switch (userInput) {
        case ("exit"):
            return ExitParser.parseInput(userInput);
        default:
            return new InvalidCommand("invalid command");
        }
    }
}
