package seedu.budgetbuddy.command;

import seedu.budgetbuddy.ExpenseList;

public class SetBudgetCommand extends Command {
    private ExpenseList expenseList;
    private String category;
    private double budget;

    public SetBudgetCommand(ExpenseList expenseList, String category, double budget){
        this.expenseList = expenseList;
        this.category = category;
        this.budget = budget;
    }

    @Override
    public void execute(){
        expenseList.setBudget(this.category, this.budget);
        System.out.println("Budget Added :" + category + " of $" + budget);
    }
}
