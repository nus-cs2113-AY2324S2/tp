package parser;

import financialtransactions.Inflow;
import financialtransactions.Outflow;
import userinteraction.UI;
import financialtransactions.TransactionManager;
import user.Authentication;
import user.BaseUser;

public class Parser {
    public boolean isContinue;
    UI ui;

    public Parser() {
        this.isContinue = true;
        this.ui = new UI();
    }

    public String parseCommand(String command, TransactionManager manager) {
        String[] commandParts = command.split("\\s+");
        String action = commandParts[0];
        
        switch (action) {
        case "login":
            String username = "";
            String password = null;
            for (String part : commandParts) {
                if (part.startsWith("u/")) {
                    username = part.substring(2);
                } else if (part.startsWith("p/")) {
                    password = part.substring(2);
                }
            }

            BaseUser user = new BaseUser(username);
            Authentication auth = user.getAuthentication();
            if (auth.checkPassword(user.getName(), password)) {
                ui.printMessage("Password is correct. You are now logged in");
            } else {
                ui.printMessage("Password is incorrect");
            }
            break;
        case "add-inflow":
            String inflowName = null;
            double inflowAmount = 0;
            String inflowDate = null;
            String inflowTime = null;

            for (String part : commandParts) {
                if (part.startsWith("n/")) {
                    inflowName = part.substring(2);
                } else if (part.startsWith("a/")) {
                    inflowAmount = Double.parseDouble(part.substring(2));
                } else if (part.startsWith("d/")) {
                    inflowDate = part.substring(2);
                } else if (part.startsWith("t/")) {
                    inflowTime = part.substring(2);
                }
            }
            String inflowDateTime = inflowDate + " " + inflowTime;

            Inflow inflow = new Inflow(inflowName, inflowAmount, inflowDateTime);
            manager.addTransaction(inflow);
            ui.printMessage("Ok. Added inflow");
            return "Ok. Added inflow";
        case "add-outflow":
            String outflowName = null;
            double outflowAmount = 0;
            String outflowDate = null;
            String outflowTime = null;

            for (String part : commandParts) {
                if (part.startsWith("n/")) {
                    outflowName = part.substring(2);
                } else if (part.startsWith("a/")) {
                    outflowAmount = Double.parseDouble(part.substring(2));
                } else if (part.startsWith("d/")) {
                    outflowDate = part.substring(2);
                } else if (part.startsWith("t/")) {
                    outflowTime = part.substring(2);
                }
            }
            String outflowDateTime = outflowDate + " " + outflowTime;

            Outflow outflow = new Outflow(outflowName, outflowAmount, outflowDateTime);
            manager.addTransaction(outflow);
            ui.printMessage("Ok. Added outflow");
            return "Ok. Added outflow";
        case "delete-inflow":
        case "delete-outflow":
            String index = commandParts[1];
            manager.removeTransaction(Integer.parseInt(index));
            //manager.removeTransaction(1, true);
            //break;
            //manager.removeTransaction(1, false);
            break;
        case "view-history":
            int numTransactions = Integer.parseInt(commandParts[1].substring(2));
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

    public boolean getIsContinue(){
        return this.isContinue;
    }
}

