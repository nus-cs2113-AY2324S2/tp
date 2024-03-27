package brokeculator;
import brokeculator.dashboard.Dashboard;
import brokeculator.event.EventManager;
import brokeculator.storage.FileManager;
import brokeculator.expense.ExpenseManager;

public class Brokeculator {
    public static void main(String[] args) {
        ExpenseManager expenseManager = new ExpenseManager();
        FileManager fileManager = new FileManager();
        EventManager eventManager = EventManager.getInstance();
        Dashboard dashboard = new Dashboard(expenseManager, fileManager, eventManager);
        Logic driverLogic = new Logic(dashboard);
        driverLogic.run();
    }
}
