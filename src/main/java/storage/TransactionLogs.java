package storage;

import item.Transaction;
import itemlist.Cashier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TransactionLogs extends Storage {
    private static final String LOGNAME = "./TransactionLogs.txt";

    public static String getFileDirectory() {
        return LOGNAME;
    }

    public static void updateFile(String inputText, boolean ifAppend) {
        try {
            writeToFile(getFileDirectory(), inputText, ifAppend);
        } catch (IOException e) {
            System.out.println("IOExceptions occurred");
        }
    }

    public static void readFromFile(String fileName) {
        System.out.println("start...");
        try {
            System.out.println("try blk");
            Scanner scanner = new Scanner(new File(fileName));
            System.out.println("File created");
            String commandQty = "";
            String commandProfit = "";
            String commandTotalSell = "";
            String commandSell = "";
            String commandName = "";
            String commandDate = "";
            while (scanner.hasNext()) {
                String fileLine = scanner.nextLine();
                System.out.println(fileLine);
                if (fileLine.contains("Quantity: ")) {
                    commandQty = fileLine.replace("Quantity: ", "");
                } else if (fileLine.contains("Date: ")) {
                    commandDate = fileLine.replace("Date: ", "");
                } else if (fileLine.contains("Unit Price: ")) {
                    commandSell = fileLine.replace("Unit Price: ", "");
                } else if (fileLine.contains("Total Price: ")) {
                    commandTotalSell = fileLine.replace("Total Price: ", "");
                } else if (fileLine.contains("Item Name: ")){
                    commandName = fileLine.replace("Item Name: ", "");
                } else if (fileLine.contains("Profit: ")) {
                    commandProfit = fileLine.replace("Profit: ", "");
                    int quantityAsInt = Integer.parseInt(commandQty);
                    int buyAsInt = (Integer.parseInt(commandTotalSell) - Integer.parseInt(commandProfit))
                            / quantityAsInt;
                    Transaction toAdd = new Transaction(commandName, quantityAsInt,
                            buyAsInt, Integer.parseInt(commandSell), commandDate);
                    Cashier.addItem(toAdd);
                }
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("File does not exist.");
        } catch(NumberFormatException e) {
            System.out.println("Invalid numbers found");
        }
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
