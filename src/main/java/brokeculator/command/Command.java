package brokeculator.command;

import brokeculator.expense.ExpenseManager;

public abstract class Command {
    public Command() {};
    public abstract void execute(ExpenseManager expenseManager);
}
