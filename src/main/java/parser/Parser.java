package parser;

import customexceptions.IncompletePromptException;
import command.AddInflowCommand;
import command.AddOutflowCommand;
import command.BaseCommand;
import command.DeleteOutflowCommand;
import command.ExitCommand;
import command.ViewHistoryCommand;
import userinterface.UI;

public class Parser {
    UI ui;

    public Parser(UI ui) {
        this.ui = ui;
    }

    public BaseCommand parseCommand(String command) throws IncompletePromptException, Exception {
        String[] commandParts = command.split("\\s+");
        String action = commandParts[0];
        switch (action) {
        case "add-inflow":
            if (commandParts.length < 3) {
                throw new IncompletePromptException(command);
            }
            return new AddInflowCommand(commandParts);
        case "add-outflow":
            if (commandParts.length < 3) {
                throw new IncompletePromptException(command);
            }
            return new AddOutflowCommand(commandParts);
        case "delete-inflow":
        case "delete-outflow":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            return new DeleteOutflowCommand(commandParts);
        case "view-history":
            return new ViewHistoryCommand(commandParts);
        case "quit":
            return new ExitCommand(commandParts);
        default:
            throw new IncompletePromptException(command);
        }
    }
}
