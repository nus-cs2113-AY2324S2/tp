package brokeculator.parser;

import brokeculator.command.AddExpenseToEventCommand;
import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import brokeculator.parser.util.Keyword;
import brokeculator.parser.util.OrderParser;

public class AddExpenseToEventParser {
    private static final Keyword[] KEYWORDS = {
        new Keyword(" /exi ", "Expense index", false),
        new Keyword(" /evi ", "Event index", false)
    };
    public static Command parseInput(String userInput) {

        String[] userInputs;
        try {
            userInputs = OrderParser.parseOrder(userInput, KEYWORDS);
        } catch (Exception e) {
            return new InvalidCommand(e.getMessage());
        }

        int expenseIdx;
        int eventIdx;
        try {
            expenseIdx = Integer.parseInt(userInputs[0]);
            eventIdx = Integer.parseInt(userInputs[1]);
        } catch (NumberFormatException e) {
            return new InvalidCommand("Indexes must be integers");
        }

        return new AddExpenseToEventCommand(expenseIdx, eventIdx);
    }
}
