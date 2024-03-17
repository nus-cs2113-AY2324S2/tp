package brokeculator.command;

import brokeculator.expense.Expense;
import brokeculator.expense.ExpenseManager;
import brokeculator.frontend.UI;

public class AddExpenseFromFileCommand extends Command{
    private final String fileString;

    public AddExpenseFromFileCommand(String fileString) {
        this.fileString = fileString;
    }

    @Override
    public void execute(ExpenseManager expenseManager) {
        try {
            Expense expense = Expense.getExpenseFromFile(this.fileString);
            expenseManager.add(expense);
        } catch (Exception e) {
            UI.print("Expense cannot be added");
        }
    }
}
