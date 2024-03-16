package brokeculator.parser;

import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;

public class GeneralInputParser {
    private AddParser addParser;

    public GeneralInputParser(AddParser addParser) {
        this.addParser = addParser;
    }

    public void parseInput(String userInput) {
        Command commandToExecute;
        String commandKeyword = userInput.split(" ")[0];
        switch (commandKeyword) {
        case "add":
            commandToExecute = addParser.parseInput(userInput);
            break;
        default:
            // received invalid command
            commandToExecute = new InvalidCommand();
        }
        commandToExecute.execute();
    }
}
