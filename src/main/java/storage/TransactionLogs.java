package storage;

import item.Transaction;

import java.util.ArrayList;

public class TransactionLogs extends Storage {
    private static final String LOGNAME = "./TransactionLogs.txt";

    public static String getFileDirectory() {
        return LOGNAME;
    }

    public static void addToLog(ArrayList<Transaction> transactions) {
        assert transactions != null : "Transactions cannot be null.";
        Transaction lastTransaction = transactions.get(transactions.size() - 1);
        String descriptionAdded = "";
        descriptionAdded += "Date: " + lastTransaction.getDateTime() + "\n";
        descriptionAdded += "Transaction ID: " + transactions.size() + "\n";
        descriptionAdded += "Item Name: " + lastTransaction.getItemName() + "\n";
        descriptionAdded += "Quantity: " + lastTransaction.getQuantity() + "\n";
        descriptionAdded += "Unit Price: " + lastTransaction.getSellPrice() + "\n";
        descriptionAdded += "Total Price: " + lastTransaction.getTotalPrice() + "\n";
        descriptionAdded += "Profit: " + lastTransaction.getProfit() + "\n";
        descriptionAdded += "\n";
        updateFile(descriptionAdded, true);
    }

    public static void overwriteLog(ArrayList<Transaction> transactions) {
        assert transactions != null : "Items cannot be null.";
        int length = transactions.size();
        for (int index = 0; index < length; index++) {
            String descriptionAdded = "";
            descriptionAdded += "Date: " + transactions.get(index).getDateTime() + "\n";
            descriptionAdded += "Transaction ID: " + transactions.get(index + 1) + "\n";
            descriptionAdded += "Item Name: " + transactions.get(index).getItemName() + "\n";
            descriptionAdded += "Quantity: " + transactions.get(index).getQuantity() + "\n";
            descriptionAdded += "Unit Price: " + transactions.get(index).getSellPrice() + "\n";
            descriptionAdded += "Total Price: " + transactions.get(index).getTotalPrice() + "\n";
            descriptionAdded += "Profit: " + transactions.get(index).getProfit() + "\n";
            descriptionAdded += "\n";
            if (index == 0) {
                updateFile(descriptionAdded, false);
            } else {
                updateFile(descriptionAdded, true);
            }
        }
    }
}
