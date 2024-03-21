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

    public BaseCommand parseCommand(String command, TransactionManager manager) throws
            IncompletePromptException, Exception {
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
            /*String inflowName = commandParts[1];
            double inflowAmount = Double.parseDouble(commandParts[2]);
            String inflowDate = commandParts[3] + " " + commandParts[4];

            Inflow inflow = new Inflow(inflowName, inflowAmount, inflowDate);
            manager.addTransaction(inflow);
            ui.printMessage("Ok. Added inflow");*/
            //return "Ok. Added inflow";*/
        case "add-outflow":
            if (commandParts.length < 3) {
                throw new IncompletePromptException(command);
            }
            return new AddOutflowCommand(commandParts);
            /*String outflowName = commandParts[1];
            double outflowAmount = Double.parseDouble(commandParts[2]);
            String outflowDate = commandParts[3] + " " + commandParts[4];

            Outflow outflow = new Outflow(outflowName, outflowAmount, outflowDate);
            manager.addTransaction(outflow);
            ui.printMessage("Ok. Added outflow");*/
            //return "Ok. Added outflow";
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
            String index = commandParts[1];
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
