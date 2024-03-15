package parser;

import financialtransactions.Inflow;
import financialtransactions.Outflow;
import userinteraction.UI;
import financialtransactions.TransactionManager;
import user.Authentication;
import user.BaseUser;

public class Parser {
    protected static boolean isContinue = true;
    public boolean isContinue;
    UI ui;

    public Parser() {
        this.isContinue = true;
        this.ui = new UI();
    }

    public String parseCommand(String command, TransactionManager manager) {
        String[] commandParts = command.split(" ");
        String action = commandParts[0];

        switch (action) {
        case "login":
            BaseUser user = new BaseUser(commandParts[1]);
            String password = commandParts[2];
            Authentication auth = user.getAuthentication();
            if (auth.checkPassword(user.getName(), password)) {
                ui.printMessage("Password is correct. You are now logged in");
            } else {
                ui.printMessage("Password is incorrect");
            }
            break;
        case "add-inflow":
            String inflowName = commandParts[1];
            double inflowAmount = Double.parseDouble(commandParts[2]);
            String inflowDate = commandParts[3] + " " + commandParts[4];

            Inflow inflow = new Inflow(inflowName, inflowAmount, inflowDate);
            manager.addTransaction(inflow);
            ui.printMessage("Ok. Added inflow");
            return "Ok. Added inflow";
        case "add-outflow":
            String outflowName = commandParts[1];
            double outflowAmount = Double.parseDouble(commandParts[2]);
            String outflowDate = commandParts[3] + " " + commandParts[4];

            Outflow outflow = new Outflow(outflowName, outflowAmount, outflowDate);
            manager.addTransaction(outflow);
            ui.printMessage("Ok. Added outflow");
            return "Ok. Added outflow";
        case "delete-inflow":
            //manager.removeTransaction(1, true);
            break;
        case "delete-outflow":
            //manager.removeTransaction(1, false);
            break;
        case "view-history":
            int numTransactions = Integer.parseInt(commandParts[1].trim());
            manager.showLastNTransactions(numTransactions);
            break;
        case "quit":
            isContinue = false;
            break;
        default:
            System.out.println("Invalid command");
            break;
        }
        return null;
    }
}

