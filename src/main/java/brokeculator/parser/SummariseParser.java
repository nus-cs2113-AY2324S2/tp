package brokeculator.parser;

import brokeculator.command.SummariseCommand;
import brokeculator.expense.ExpenseManager;

public class SummariseParser {
    private ExpenseManager expenseManager;

    public SummariseParser(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
    }

    public SummariseCommand parseInput(String userInput) {
        return new SummariseCommand(expenseManager);
    }
}
