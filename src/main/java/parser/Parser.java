package parser;

import command.AddInflowCommand;
import command.AddOutflowCommand;
import command.BaseCommand;
import command.DeleteInflowCommand;
import command.DeleteOutflowCommand;
import command.EditInflowCommand;
import command.EditOutflowCommand;
import command.ExitCommand;
import command.ViewHistoryCommand;
import command.HelpCommand;
import customexceptions.IncompletePromptException;
import financialtransactions.TransactionManager;
import userinterface.UI;

public class Parser {
    public boolean isContinue;
    UI ui;

    public Parser() {
        this.ui = new UI();
        this.isContinue = true;
    }

    public BaseCommand parseCommand(String command, TransactionManager manager) throws Exception {
        String[] commandParts = command.split("\\s+");
        String action = commandParts[0];
        switch (action) {
        case "help":
            return new HelpCommand(commandParts);
        case "add-inflow":
            if (commandParts.length < 6) {
                throw new IncompletePromptException(command);
            }
            return new AddInflowCommand(commandParts);
        case "add-outflow":
            if (commandParts.length < 6) {
                throw new IncompletePromptException(command);
            }
            return new AddOutflowCommand(commandParts);
        case "delete-inflow":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            return new DeleteInflowCommand(commandParts);
        case "delete-outflow":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            return new DeleteOutflowCommand(commandParts);
        case "edit-inflow":
            if (commandParts.length < 7) {
                throw new IncompletePromptException(command);
            }
            return new EditInflowCommand(commandParts);
        case "edit-outflow":
            if (commandParts.length < 7) {
                throw new IncompletePromptException(command);
            }
            return new EditOutflowCommand(commandParts);
        case "view-history":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            return new ViewHistoryCommand(commandParts);
        case "quit":
            this.isContinue = false;
            return new ExitCommand(commandParts);
        default:
            throw new Exception("Invalid command");
        }
    }

}
