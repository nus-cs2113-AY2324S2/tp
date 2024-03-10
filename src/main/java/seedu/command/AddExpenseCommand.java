package seedu.command;

import seedu.data.ExpenseList;

public class AddExpenseCommand extends Command{
    private ExpenseList expenses;
    private final String category;
    private final String amount;
    private final String description;

    public AddExpenseCommand(ExpenseList expenses,String category, String amount, String description) {
        this.expenses = expenses;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public void execute() {
        expenses.addExpense(this.category,this.amount,this.description);
        System.out.println("Expense Added :" + category + " of $" + amount + " description : " + description);
    }
}
