package brokeculator.command;

import brokeculator.expense.ExpenseManager;
import brokeculator.frontend.UI;

import java.util.ArrayList;

public class ListCommand extends Command {
    private int amountToList;
    private ExpenseManager expenseManager;

    public ListCommand(int amountToList, ExpenseManager expenseManager) {
        this.amountToList = amountToList;
        this.expenseManager = expenseManager;
    }

    @Override
    public void execute() {
        //TODO implement proper printing of expense list
        //ArrayList<Expense> listOfExpensesToPrint = expenseManager.listExpenses(amountToList);
        UI.print("placeholder for printing expense list");
    }
}
