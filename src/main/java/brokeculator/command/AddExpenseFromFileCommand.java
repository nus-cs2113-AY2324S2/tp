package brokeculator.command;

import brokeculator.expense.Expense;
import brokeculator.expense.ExpenseManager;

public class AddExpenseFromFileCommand extends Command{
    private String fileString;

    public AddExpenseFromFileCommand(String fileString) {
        this.fileString = fileString;
    }

    @Override
    public void execute(ExpenseManager expenseManager) {
        try {
            Expense expense = Expense.getExpenseFromFile(this.fileString);
            expenseManager.add(expense);
        } catch (Exception e) {
            // TODO: fix feedback
            System.out.println("Expense cannot be added");
        }
    }
}
