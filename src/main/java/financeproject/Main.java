package financeproject;

import customexceptions.InactivityTimeoutException;
import command.BaseCommand;
import customexceptions.IncompletePromptException;
import financialtransactions.TransactionManager;
import parser.Parser;
import storage.Storage;
import user.InactivityTimer;
import user.Authentication;
import user.BaseUser;
import userinterface.UI;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage("./data");
        TransactionManager manager = new TransactionManager();
        manager = storage.loadFile();

        UI ui = new UI();
        ui.printMessage("Welcome. In order to login, type your command in the format:\nlogin u/USERNAME p/PASSWORD");

        Parser parser = new Parser();
        BaseCommand baseCommand = null;
        String response = "";

        BaseUser tempUser = new BaseUser("Bob", ui);
        Authentication auth = tempUser.getAuthentication();
        InactivityTimer inactivityTimer = new InactivityTimer();

        if (!auth.authenticate()) {
            ui.printMessage("Authentication error");
            ui.closeScanner();
            return;
        } else {
            ui.printMessage("Password is correct. You are now logged in");
        }

        do {
            //System.out.println("Welcome to FinTrack! How can we help?");
            try {
                inactivityTimer.checkTimeElapsed();
            } catch (InactivityTimeoutException e) {
                if (e.isTimeOut()) {
                    //parser.setIsContinue(false);
                    assert baseCommand != null;
                    baseCommand.setIsExit(true);
                } else if (e.isGracePeriod()) {
                    ui.printMessage("Some time has passed. Would you still like to continue: ");
                    String wantToContinue = ui.readInput();
                    if (wantToContinue.equalsIgnoreCase("y") ||
                            wantToContinue.equalsIgnoreCase("yes")) {
                        inactivityTimer.resetTimer();
                    } else if (wantToContinue.equalsIgnoreCase("n") ||
                            wantToContinue.equalsIgnoreCase("no")) {
                        //parser.setIsContinue(false);
                        assert baseCommand != null;
                        baseCommand.setIsExit(true);
                    }
                }
            }

            String command = ui.readInput();
            try {
                baseCommand = parser.parseCommand(command, manager);
                response = baseCommand.execute(manager);
                ui.printMessage(response);
            } catch (IncompletePromptException e) {
                if (e.getIsTypo()) {
                    System.out.println("Sorry, your prompt appears incomplete. Could you finish your sentence?");
                } else {
                    System.out.println("Sorry, unknown prompt detected.");
                }
            } catch (Exception e) {
                System.out.println("Uh-oh, something went wrong: " + e.getMessage());
            }
            storage.saveFile(manager);
        } while (!baseCommand.isExit());
        ui.closeScanner();

        /*
         * Inflow income = new Inflow("Salary payment", 400.00, "23/05/2022 1900");
         * income.setCategory(Inflow.Category.INCOME);
         * manager.addTransaction(income);
         *
         * Inflow investment = new Inflow("Investment", 500.00, "23/05/2022 1900");
         * investment.setCategory(Inflow.Category.INVESTMENT);
         * manager.addTransaction(investment);
         *
         * Inflow loan = new Inflow("Loan payment", 400.00, "23/05/2022 1900");
         * loan.setCategory(Inflow.Category.LOAN);
         * manager.addTransaction(loan);
         *
         * Outflow rent = new Outflow("Rent", 50000, "23/05/2022 1900");
         * rent.setCategory(Outflow.Category.RENT);
         * manager.addTransaction(rent);
         *
         * Outflow shopping = new Outflow("Shopping", 200, "23/05/2022 2000");
         * shopping.setCategory(Outflow.Category.SHOPPING);
         * manager.addTransaction(shopping);
         *
         *
         * ui.printMessage(manager.toString());
         */
    }
}

