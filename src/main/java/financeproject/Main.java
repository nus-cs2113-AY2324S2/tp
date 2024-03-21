package financeproject;

import command.BaseCommand;
//import financialtransactions.Inflow;
//import financialtransactions.Outflow;
import financialtransactions.TransactionManager;
import parser.Parser;
import storage.Storage;
import user.Authentication;
import user.BaseUser;
import userinterface.UI;

public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage("./data");
        TransactionManager manager = new TransactionManager();
        manager = storage.loadFile();

        UI ui = new UI();
        ui.printMessage("Welcome. Enter your username and password to login.");
        
        Parser parser = new Parser();
        BaseCommand baseCommand = null;
        String response = "";

        BaseUser tempUser = new BaseUser("Bob", ui);
        Authentication auth = tempUser.getAuthentication();
        if (!auth.authenticate()) {
            ui.printMessage("Authentication error");
            ui.closeScanner();
            return;
        } else{
            ui.printMessage("Password is correct. You are now logged in");
        }
        do {
            String command = ui.readInput();
            try {
                baseCommand = parser.parseCommand(command, manager);
                response = baseCommand.execute(manager);
                ui.printMessage(response);
            } catch (Exception e) {
                ui.printMessage(e.getMessage());
            }
            storage.saveFile(manager);
        } while (!baseCommand.isExit());
        ui.closeScanner();
        /*B
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
