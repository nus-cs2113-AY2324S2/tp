package brokeculator.parser;

import brokeculator.command.Command;
import brokeculator.command.HelpCommand;

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
        case "exit":
            commandToExecute = ExitParser.parseInput(userInput);
            break;
        case "event":
            commandToExecute = EventParser.parseInput(userInput);
            break;
        case "category":
            commandToExecute = CategoryParser.parseInput(userInput);
            break;
        case "viewEvents":
            commandToExecute = ViewEventsParser.parseInput(userInput);
            break;
        case "deleteEvent":
            commandToExecute = DeleteEventParser.parseInput(userInput);
            break;
       case "addExpenseToEvent":
           commandToExecute = AddExpenseToEventParser.parseInput(userInput);
           break;
        default:
            commandToExecute = new HelpCommand();
        }
        return commandToExecute;
    }
}
