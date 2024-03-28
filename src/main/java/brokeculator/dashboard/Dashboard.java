package brokeculator.dashboard;

import brokeculator.event.EventManager;
import brokeculator.expense.ExpenseManager;
import brokeculator.storage.FileManager;

public class Dashboard {

    private final ExpenseManager expenseManager;
    private final FileManager fileManager;
    private final EventManager eventManager;

    public Dashboard(ExpenseManager expenseManager, FileManager fileManager, EventManager eventManager) {
        this.expenseManager = expenseManager;
        this.fileManager = fileManager;
        this.eventManager = eventManager;
    }

    public ExpenseManager getExpenseManager() {
        return expenseManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }
}
