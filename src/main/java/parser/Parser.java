package parser;

import command.AddInflowCommand;
import command.AddOutflowCommand;
import command.BaseCommand;
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
        case "add-inflow":
            return new AddInflowCommand(commandParts);
        case "add-outflow":
            return new AddOutflowCommand(commandParts);
        case "delete-inflow":
        case "delete-outflow":
            String index = commandParts[1];
            manager.removeTransaction(Integer.parseInt(index));
            break;
        case "view-history":
            return new ViewHistoryCommand(commandParts);
        case "quit":
            return new ExitCommand(commandParts);
        default:
            System.out.println("Invalid command");
            break;
        }
        throw new Exception("Error parsing");
    }
}
