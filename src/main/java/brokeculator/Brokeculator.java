package brokeculator;
import brokeculator.storage.FileManager;
import brokeculator.expense.ExpenseManager;

public class Brokeculator {
    private static final String GREETING = "Hello! I'm Brokeculator!"
            + System.lineSeparator()
            + "What can I do for you?";
    public static void main(String[] args){
        GeneralInputParser mainParser = new GeneralInputParser();
        ExpenseManager expenseManager = new ExpenseManager();
        FileManager fileManager = new FileManager();
        Logic driverLogic = new Logic(mainParser, fileManager, expenseManager);
        UI.print(GREETING);
        driverLogic.run();
    }
}
