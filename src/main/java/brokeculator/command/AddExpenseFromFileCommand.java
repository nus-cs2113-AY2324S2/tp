package brokeculator.command;

import seedu.expense.Expense;
import seedu.expense.ExpenseManager;

public class AddExpenseFromFileCommand extends Command{
    private String fileString;
    private ExpenseManager expenseManager;

    public AddExpenseFromFileCommand(String fileString, ExpenseManager expenseManager) {
        this.fileString = fileString;
        this.expenseManager = expenseManager;
    }

    @Override
    public void execute() {
        try {
            Expense expense = Expense.getExpenseFromFile(this.fileString);
            this.expenseManager.add(expense);
        } catch (Exception e) {
            // TODO: fix feedback
            System.out.println("Expense cannot be added");
        }
    }
}
