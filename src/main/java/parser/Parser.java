package parser;

import transactions.Transaction;

public class Parser {

    public static final int ADD_COMMAND_INDEX = 3;

    public Transaction parseTransaction(String input) {
        String data = input.substring(ADD_COMMAND_INDEX + 1);
        String[] parseData = data.split("/");
        String description = null, date = null, amount = null, category = null;
        for(int i = 0; i < parseData.length; i++) {
            if (parseData[i].trim().equals("n")){
                description = parseData[i + 1];
            } else if (parseData[i].trim().equals("$")) {
                amount = parseData[i + 1];
            } else if (parseData[i].trim().equals("d")) {
                date = parseData[i + 1];
            } else if (parseData[i].trim().equals("c")) {
                category = parseData[i + 1];
            }
        }
        assert amount != null;
        return new Transaction(description, Float.parseFloat(amount), date, category);
    }
}
