package brokeculator.parser;

import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;

public class GeneralInputParser {
    private AddParser addParser;
    private DeleteParser deleteParser;
    private ListParser listParser;
    private SummariseParser summariseParser;

    public GeneralInputParser(AddParser addParser, DeleteParser deleteParser, ListParser listParser,
                              SummariseParser summariseParser) {
        this.addParser = addParser;
        this.deleteParser = deleteParser;
        this.listParser = listParser;
        this.summariseParser = summariseParser;
    }

    public Command getCommandFromUserInput(String userInput) {
        Command commandToExecute;
        String commandKeyword = userInput.split(" ")[0];
        switch (commandKeyword) {
        case "add":
            commandToExecute = addParser.parseInput(userInput);
            break;
        case "delete":
            commandToExecute = deleteParser.parseInput(userInput);
            break;
        case "list":
            commandToExecute = listParser.parseInput(userInput);
            break;
        case "summarise":
            commandToExecute = summariseParser.parseInput(userInput);
            break;
        default:
            // received invalid command
            commandToExecute = new InvalidCommand();
        }
        return commandToExecute;
    }
}
