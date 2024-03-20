package budgetbuddy.parser;

import budgetbuddy.account.Account;
import budgetbuddy.exception.InvalidTransactionTypeException;
import budgetbuddy.transaction.TransactionList;
import budgetbuddy.transaction.type.Expense;
import budgetbuddy.transaction.type.Income;
import budgetbuddy.transaction.type.Transaction;
import com.sun.jdi.InvalidTypeException;

public class Parser {

    public static final int ADD_COMMAND_INDEX = 3;

    public Transaction parseTransaction(String input, Account account) throws InvalidTransactionTypeException, NumberFormatException {
        String data = input.substring(ADD_COMMAND_INDEX + 1);
        String[] parseData = data.split("/");
        String type = null;
        String description = null;
        String date = null;
        String amount = null;
        String category = null;
        for(int i = 0; i < parseData.length; i++) {
            switch (parseData[i].trim()) {
            case "t":
                type = parseData[i + 1].trim();
                break;
            case "n":
                description = parseData[i + 1].trim();
                break;
            case "$":
                // Checks that input is an Integer
                if (!TransactionList.isInteger(amount)) {
                    throw new NumberFormatException(parseData[i+1].trim());
                } else {
                    amount = parseData[i + 1].trim();
                    break;
                }
            case "d":
                date = parseData[i + 1].trim();
                break;
            case "c":
                category = parseData[i + 1].trim();
                break;
            default:
                break;
            }
        }
        assert amount != null;
        assert type != null;
        if (type.equalsIgnoreCase("income")) {
            return new Income(description, Double.parseDouble(amount), category, date, account);
        } else if (type.equalsIgnoreCase("expense")) {
            return new Expense(description, Double.parseDouble(amount), category, date, account);
        } else {
            throw new InvalidTransactionTypeException(type);
        }
    }
}
