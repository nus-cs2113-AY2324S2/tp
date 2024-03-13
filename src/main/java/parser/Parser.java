package parser;

import transactions.Transaction;

public class Parser {

    public static final int ADD_COMMAND_INDEX = 3;

    public Transaction parseTransaction(String input) {
        String data = input.substring(ADD_COMMAND_INDEX + 1);
        String[] parseData = data.split("/");
        String description = null;
        String date = null;
        String amount = null;
        String category = null;
        for(int i = 0; i < parseData.length; i++) {
            switch (parseData[i].trim()) {
            case "n":
                description = parseData[i + 1];
                break;
            case "$":
                amount = parseData[i + 1];
                break;
            case "d":
                date = parseData[i + 1];
                break;
            case "c":
                category = parseData[i + 1];
                break;
            }
        }
        assert amount != null;
        return new Transaction(description, Float.parseFloat(amount), date, category);
    }
}
