package brokeculator.parser;

import brokeculator.command.Command;
import brokeculator.command.DeleteExpenseFromEventCommand;
import brokeculator.command.InvalidCommand;
import brokeculator.parser.util.Keyword;
import brokeculator.parser.util.OrderParser;

public class DeleteExpenseFromEventParser {
    private static final Keyword[] KEYWORDS = {
        new Keyword(" /i ", "Expense index", false),
    };
    public static Command parseInput(String userInput) {
        String[] userInputs;
        try {
            userInputs = OrderParser.parseOrder(userInput, KEYWORDS);
        } catch (Exception e) {
            return new InvalidCommand(e.getMessage());
        }

        int expenseIdx;
        try {
            expenseIdx = Integer.parseInt(userInputs[0]);
        } catch (NumberFormatException e) {
            return new InvalidCommand("Indexes must be integers");
        }

        return new DeleteExpenseFromEventCommand(expenseIdx);
    }
}
