package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUi {

    public static final String DIVIDER = "----------------";

    public static final String WELCOME_MESSAGE = "Welcome to StockMaster, where you can master the knowledge on your " +
            "Stock!";

    public static final String GOODBYE_MESSAGE = "Thank you for using StockMaster, hope we have helped your lazy ass!";
    private final Scanner in;

    public TextUi() {
        this.in = new Scanner(System.in);
    }

    public String getUserInput() {
        System.out.println("Enter Command:");
        Scanner in = new Scanner(System.in);
        String userInput = in.hasNextLine() ? in.nextLine() : "";
        if (shouldIgnore(userInput)) {
            return "Invalid Command"; //Might want to change this with Exceptions
        }
        return userInput;
    }

    public static boolean shouldIgnore(String userInput) {
        return userInput.trim().isEmpty();
    }

    public void showWelcomeMessage(String version, String storageFilePath) {
        replyToUser(
                DIVIDER,
                version,
                DIVIDER,
                "Data is being extracted from: " + storageFilePath,
                WELCOME_MESSAGE
        );
    }

    public void showGoodByeMessage(String storageFilePath) {
        replyToUser(
                DIVIDER,
                "Data is being saved to :" + storageFilePath,
                DIVIDER,
                GOODBYE_MESSAGE
        );
    }

    public static void replyToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    public static <T> void showInventoryList(ArrayList<T> arrayList) {
        if (arrayList.isEmpty()) {
            replyToUser("There is nothing here! Time to spend some money and stock em up!");
        } else {
            replyToUser("List: ");
            for (T item : arrayList) {
                if (item == null) {
                    break;
                }
                replyToUser(arrayList.indexOf(item) + 1 + ". " + item);
            }
        }
    }

    public static void showEditMessage(String item, int oldQuantity, int newQuantity) {
        replyToUser("\n" +
                "Changed quantity of " + item + " from " + oldQuantity + " to " + newQuantity
        );
    }

    public static void showSellMessage(String item, int sellQuantity, int remainingQuantity, int sellPrice) {
        replyToUser("Quantity of " + item + " sold: " + sellQuantity + ", for: $" + sellPrice + "\n" +
                "Quantity remaining: " + remainingQuantity
        );
    }
}
