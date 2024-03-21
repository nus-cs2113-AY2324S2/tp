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
            UI.prettyPrint("You have no added expenses!");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listOfExpensesToPrint.size(); i++) {
            sb.append(i + 1).append(". ").append(listOfExpensesToPrint.get(i)).append(System.lineSeparator());
        }
        UI.prettyPrint(sb.toString());
    }
}
