package financeproject;

import java.lang.reflect.InaccessibleObjectException;

import customexceptions.InactivityTimeoutException;
//import financialtransactions.Inflow;
//import financialtransactions.Outflow;
import customexceptions.IncompletePromptException;
import financialtransactions.TransactionManager;
import parser.Parser;
import storage.Storage;
import user.InactivityTimer;
//import user.Authentication;
//import user.BaseUser;
import userinteraction.UI;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage("./data");
        UI ui = new UI();
        ui.printMessage("Welcome. Inorder to login, type your command in the format:\nlogin u/USERNAME p/PASSWORD");
        //TransactionManager manager = storage.loadFile();
        TransactionManager manager = new TransactionManager();

        Parser parser = new Parser();
        InactivityTimer inactivityTimer = new InactivityTimer();
        while (parser.getIsContinue()) {
            try {
                inactivityTimer.checkTimeElapsed();
            } catch (InactivityTimeoutException e) {
                if (e.isTimeOut()) {
                    parser.setIsContinue(false);
                } else if (e.isGracePeriod()) {
                    ui.printMessage("Some time has passed. Would you still like to continue: ");
                    String wantToContinue = ui.readInput();
                    if (wantToContinue.equalsIgnoreCase("y") ||
                            wantToContinue.equalsIgnoreCase("yes")) {
                        inactivityTimer.resetTimer();
                    } else if (wantToContinue.equalsIgnoreCase("n") ||
                            wantToContinue.equalsIgnoreCase("no")) {
                        parser.setIsContinue(false);
                    }
                }
            }
            String command = ui.readInput();
            try {
                parser.parseCommand(command, manager);
            } catch (IncompletePromptException e) {
                if (e.getIsTypo()) {
                    System.out.println("Sorry, your prompt appears incomplete. Could you finish your sentence?");
                } else {
                    System.out.println("Sorry, unknown prompt detected.");
                }
            }
            storage.saveFile(manager);
        }
        ui.closeScanner();
        /*
        Inflow income = new Inflow("Salary payment", 400.00, "23/05/2022 1900");
        income.setCategory(Inflow.Category.INCOME);
        manager.addTransaction(income);

        Inflow investment = new Inflow("Investment", 500.00, "23/05/2022 1900");
        investment.setCategory(Inflow.Category.INVESTMENT);
        manager.addTransaction(investment);

        Inflow loan = new Inflow("Loan payment", 400.00, "23/05/2022 1900");
        loan.setCategory(Inflow.Category.LOAN);
        manager.addTransaction(loan);

        Outflow rent = new Outflow("Rent", 50000, "23/05/2022 1900");
        rent.setCategory(Outflow.Category.RENT);
        manager.addTransaction(rent);

        Outflow shopping = new Outflow("Shopping", 200, "23/05/2022 2000");
        shopping.setCategory(Outflow.Category.SHOPPING);
        manager.addTransaction(shopping);


        ui.printMessage(manager.toString()); */
    }
}
