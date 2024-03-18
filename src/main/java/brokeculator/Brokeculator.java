package brokeculator;
import brokeculator.dashboard.Dashboard;
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
        Dashboard dashboard = new Dashboard(expenseManager, fileManager);
        Logic driverLogic = new Logic(dashboard);
        UI.print(GREETING);
        driverLogic.run();
    }
}
