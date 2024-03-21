package parser;

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
    UI ui;

    public Parser() {
        this.ui = new UI();
    }

    public BaseCommand parseCommand(String command, TransactionManager manager) throws Exception {
        String[] commandParts = command.split("\\s+");
        String action = commandParts[0];
        switch (action) {
        case "help":
            //implement help command
            break;
        case "add-inflow":
            return new AddInflowCommand(commandParts);
        case "add-outflow":
            return new AddOutflowCommand(commandParts);
        case "delete-inflow":
            return new DeleteInflowCommand(commandParts);
        case "delete-outflow":
            return new DeleteOutflowCommand(commandParts);
        case "edit-inflow":
        case "edit-outflow":
            // implement edit transaction command
            break;
        case "view-history":
            return new ViewHistoryCommand(commandParts);
        case "quit":
            return new ExitCommand(commandParts);
        default:
            throw new Exception("Invalid command");
        }
        return null;
    }
}
