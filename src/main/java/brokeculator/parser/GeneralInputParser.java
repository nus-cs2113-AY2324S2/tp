package brokeculator.parser;

import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;

public class GeneralInputParser {
    public static Command getCommandFromUserInput(String userInput) {
        Command commandToExecute;
        String commandKeyword = userInput.split(" ")[0];
        switch (commandKeyword) {
        case "add":
            commandToExecute = AddParser.parseInput(userInput);
            break;
        case "delete":
            commandToExecute = DeleteParser.parseInput(userInput);
            break;
        case "list":
            commandToExecute = ListParser.parseInput(userInput);
            break;
        case "summarise":
            commandToExecute = SummariseParser.parseInput(userInput);
            break;
        case ("exit"):
            commandToExecute = ExitParser.parseInput(userInput);
            break;
        default:
            // received invalid command
            commandToExecute = new InvalidCommand();
        }
        return commandToExecute;
    }
}
