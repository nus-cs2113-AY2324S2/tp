package parser;

import customexceptions.IncompletePromptException;
import command.AddInflowCommand;
import command.AddOutflowCommand;
import command.BaseCommand;
import command.ExitCommand;
import command.ViewHistoryCommand;
import financialtransactions.TransactionManager;
import userinterface.UI;

public class Parser {
    public boolean isContinue;
    UI ui;

    public Parser() {
        this.ui = new UI();
    }

    public BaseCommand parseCommand(String command, TransactionManager manager) throws
            Exception {
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
            String index = commandParts[1];
            manager.removeTransaction(Integer.parseInt(index));
            break;
        case "view-history":
            return new ViewHistoryCommand(commandParts);
        case "quit":
            return new ExitCommand(commandParts);
        default:
            throw new IncompletePromptException(command);
        }
        throw new Exception("Error parsing");
    }

    public void setIsContinue(boolean isContinue) {
        this.isContinue = isContinue;
    }
}
