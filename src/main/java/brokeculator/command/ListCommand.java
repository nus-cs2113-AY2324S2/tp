package brokeculator.command;

import brokeculator.dashboard.Dashboard;
import brokeculator.expense.Expense;
import brokeculator.frontend.UI;

import java.util.ArrayList;

public class ListCommand extends Command {
    private int amountToList;

    public ListCommand(int amountToList) {
        this.amountToList = amountToList;
    }

    @Override
    public void execute(Dashboard dashboard) {
        //TODO implement proper printing of expense list
        ArrayList<Expense> listOfExpensesToPrint = dashboard.getExpenseManager().listExpenses(amountToList);

        if (listOfExpensesToPrint.isEmpty()) {
            UI.print("No expenses to list");
            return;
        }

        for (Expense expense : listOfExpensesToPrint) {
            UI.print(expense.getStringRepresentation());
        }
    }
}
