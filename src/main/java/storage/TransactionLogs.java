package storage;

import item.Item;

import java.io.IOException;
import java.util.ArrayList;

import static storage.Storage.writeToFile;

public class TransactionLogs extends Storage {
//    LocalDateTime currentTime = LocalDateTime.now();
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    String formattedDateTime = currentTime.format(formatter);
//    System.out.println(formattedDateTime);
    private static final String LOGNAME = "./TransactionLogs.txt";
    private static String dateTime;
    public static void setDateTime(String startDateTime) {
        dateTime = startDateTime;
    }

    /**
     * Returns the private file directory of TransactionLogs.
     */
    public static String getLogDirectory() {
        return LOGNAME;
    }

    public static void addToLog(ArrayList<Item> items, boolean ifAppend) {
        assert items != null : "Items cannot be null.";
        Item lastItem = items.get(items.size() - 1);
        String descriptionAdded = (items.size()) + "." + " | " + lastItem.getItemName() +
                " | " + "Qty: " + lastItem.getQuantity() + " " + lastItem.getUom() +
                " | " + "Cat: " + lastItem.getCategory() + " | " + "BuyPrice: $" +
                lastItem.getBuyPrice() + " | " + "SellPrice: $" + lastItem.getSellPrice() + "\n";
        updateFile(descriptionAdded, ifAppend);
    }

    public static void overwriteLog(ArrayList<Item> items, boolean ifAppend) {
        assert items != null : "Items cannot be null.";
        int length = items.size();
        for (int index = 0; index < length; index++) {
            String descriptionAdded = (index + 1) + "." + " | " + items.get(index).getItemName() +
                    " | " + "Qty: " + items.get(index).getQuantity() + " " + items.get(index).getUom() +
                    " | " + "Cat: " + items.get(index).getCategory() + " | " + "BuyPrice: $" +
                    items.get(index).getBuyPrice() + " | " + "SellPrice: $" +
                    items.get(index).getSellPrice() + "\n";
            if (index == 0) {
                updateFile(descriptionAdded, ifAppend);
            } else {
                updateFile(descriptionAdded, !ifAppend);
            }
        }
    }

}
