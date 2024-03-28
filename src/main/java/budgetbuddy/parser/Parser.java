package budgetbuddy.parser;

import budgetbuddy.account.Account;
import budgetbuddy.categories.Category;
import budgetbuddy.exceptions.EmptyArgumentException;
import budgetbuddy.exceptions.InvalidTransactionTypeException;
import budgetbuddy.transaction.TransactionList;
import budgetbuddy.transaction.type.Expense;
import budgetbuddy.transaction.type.Income;
import budgetbuddy.transaction.type.Transaction;

public class Parser {

    public static final int ADD_COMMAND_INDEX = 3;

    public Transaction parseTransaction(String input, Account account)
            throws InvalidTransactionTypeException, NumberFormatException, EmptyArgumentException {
        String data = input.substring(ADD_COMMAND_INDEX + 1);
        String[] parseData = data.split("/");
        String type = null;
        String description = null;
        String date = null;
        String amount = null;
        String category = null;
        for(int i = 0; i < parseData.length-1; i++) {
            switch (parseData[i].trim()) {
            case "t":
                type = parseData[i + 1].trim();
                break;
            case "n":
                description = parseData[i + 1].trim();
                break;
            case "$":
                if (TransactionList.isNotDouble(parseData[i + 1].trim())) {
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
        if(description.trim().isEmpty() || type.trim().isEmpty()){
            throw new EmptyArgumentException("data for the arguments ");
        } else if (type.equalsIgnoreCase("income")) {
            Income income = new Income(description, Double.parseDouble(amount), date, account);
            if (category != null){
                income.setCategory(Category.fromNumber(Integer.parseInt(category)));
            }
            return income;
        } else if (type.equalsIgnoreCase("expense")) {
            Expense expense = new Expense(description, Double.parseDouble(amount), date, account);
            if (category != null){
                expense.setCategory(Category.fromNumber(Integer.parseInt(category)));
            }
            return expense;
        } else {
            throw new InvalidTransactionTypeException(type);
        }
    }
}
