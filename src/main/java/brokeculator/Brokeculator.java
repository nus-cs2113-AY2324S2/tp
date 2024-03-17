package brokeculator;
import brokeculator.frontend.UI;
import brokeculator.storage.FileManager;
import brokeculator.expense.ExpenseManager;

public class Brokeculator {
    private static final String GREETING = "Hello! I'm Brokeculator!"
            + System.lineSeparator()
            + "What can I do for you?";
    public static void main(String[] args) {
        ExpenseManager expenseManager = new ExpenseManager();
        FileManager fileManager = new FileManager();
        Logic driverLogic = new Logic(fileManager, expenseManager);
        UI.print(GREETING);
        driverLogic.run();
    }
}
