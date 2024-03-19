package budgetbuddy.parser;

import budgetbuddy.account.Account;
import budgetbuddy.transaction.type.Expense;
import budgetbuddy.transaction.type.Income;
import budgetbuddy.transaction.type.Transaction;

public class Parser {

    public static final int ADD_COMMAND_INDEX = 3;

    public Transaction parseTransaction(String input, Account account) {
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
                amount = parseData[i + 1].trim();
                break;
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
        if (type.equalsIgnoreCase("income")) {
            return new Income(description, Double.parseDouble(amount), category, date, account);
        } else if (type.equalsIgnoreCase("expense")) {
            return  new Expense(description, Double.parseDouble(amount), category, date, account);
        }
        return null;
    }
}
