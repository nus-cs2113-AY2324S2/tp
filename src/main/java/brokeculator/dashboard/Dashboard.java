package brokeculator.dashboard;

import brokeculator.expense.ExpenseManager;
import brokeculator.storage.FileManager;

public class Dashboard {

    private final ExpenseManager expenseManager;
    private final FileManager fileManager;

    public Dashboard(ExpenseManager expenseManager, FileManager fileManager) {
        this.expenseManager = expenseManager;
        this.fileManager = fileManager;
    }

    public ExpenseManager getExpenseManager() {
        return expenseManager;
    }

    public FileManager getFileManager() {
        return fileManager;
    }
}
