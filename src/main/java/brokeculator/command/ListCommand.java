package brokeculator.command;

import brokeculator.expense.Expense;
import brokeculator.expense.ExpenseManager;
import brokeculator.frontend.UI;

import java.util.ArrayList;

public class ListCommand extends Command {
    private int amountToList;

    public ListCommand(int amountToList) {
        this.amountToList = amountToList;
    }

    @Override
    public void execute(ExpenseManager expenseManager) {
        //TODO implement proper printing of expense list
        ArrayList<Expense> listOfExpensesToPrint = expenseManager.listExpenses(amountToList);

        if (listOfExpensesToPrint.isEmpty()) {
            UI.print("No expenses to list");
            return;
        }

        for (Expense expense : listOfExpensesToPrint) {
            UI.print(expense.getStringRepresentation());
        }
    }
}
