package parser;

import customexceptions.IncompletePromptException;
import command.AddInflowCommand;
import command.AddOutflowCommand;
import command.BaseCommand;
import command.DeleteInflowCommand;
import command.DeleteOutflowCommand;
import command.ExitCommand;
import command.ViewHistoryCommand;
import financialtransactions.TransactionManager;
import userinterface.UI;

public class Parser {
    public boolean isContinue;
    UI ui;

    public Parser() {
        this.ui = new UI();
        this.isContinue = true;
    }

    public BaseCommand parseCommand(String command, TransactionManager manager) throws IncompletePromptException, Exception {
        String[] commandParts = command.split("\\s+");
        String action = commandParts[0];
        switch (action) {
        case "help":
            //implement help command
            break;
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
        case "edit-outflow":
            // implement edit function
            break;
        case "view-history":
            return new ViewHistoryCommand(commandParts);
        case "quit":
            this.isContinue = false;
            return new ExitCommand(commandParts);
        default:
            throw new Exception("Invalid command");
            //throw new IncompletePromptException(command);
        }
        return null;
    }

    public void setIsContinue(boolean isContinue) {
        this.isContinue = isContinue;
    }
}
