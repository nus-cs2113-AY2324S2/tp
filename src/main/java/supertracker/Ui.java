package supertracker;

import supertracker.item.Item;

public class Ui {
    private static final String LINE = "    --------------------------------------------------------------------------";
    private static final String EMPTY_LIST_MESSAGE = "Nothing to list! No items in inventory!";
    private static final String SINGLE_ITEM_LIST_MESSAGE= "There is 1 unique item in your inventory:";
    private static final String INVALID_COMMAND_MESSAGE = "Sorry! Invalid command!";
    private static final String WELCOME_MESSAGE = "Hello, welcome to SuperTracker, how may I help you?";
    private static final String FAREWELL_MESSAGE = "Goodbye!";

    private static String listSize(int size){
        return ("There are " + size + " unique items in your inventory:");
    }
    private static String priceMessage(Item item) {
        return "Price: " + item.getPriceString();
    }

    private static String quantityMessage(Item item) {
        return "Quantity: " + item.getQuantity();
    }

    private static String addItemOpening(Item item) {
        return item.getName() + " has been added to the inventory!";
    }

    private static void updateItemOpening(Item item) {
        System.out.println(item.getName() + " has been successfully updated!");
    }

    public static void printIndent(String string) {
        System.out.println("     " + string);
    }

    public static void printLine() {
        System.out.println(LINE);
    }

    public static void greetUser() {
        printLine();
        printIndent(WELCOME_MESSAGE);
        printLine();
    }

    public static void sayGoodbye() {
        printIndent(FAREWELL_MESSAGE);
    }

    public static void printInvalidCommand() {
        printIndent(INVALID_COMMAND_MESSAGE);
    }

    public static void newCommandSuccess(Item item) {
        printIndent(addItemOpening(item));
        printIndent(quantityMessage(item));
        printIndent(priceMessage(item));
    }

    public static void updateCommandSuccess(Item item) {
        updateItemOpening(item);
        printIndent(quantityMessage(item));
        printIndent(priceMessage(item));
    }

    public static void listIntro(int size) {
        if (size == 0) {
            printIndent(EMPTY_LIST_MESSAGE);
            return;
        }
        if (size == 1) {
            printIndent(SINGLE_ITEM_LIST_MESSAGE);
            return;
        }
        printIndent(listSize(size));
    }

    public static void listItem(Item item, int index, boolean qExists, boolean pExists, String firstParam) {
        String stringToPrint = index + ". Name: " + item.getName();
        String quantityString = "    Quantity: " + item.getQuantity();
        String priceString = "    Price: " + item.getPriceString();

        if (qExists && pExists) {
            if (firstParam.equals("q")) {
                stringToPrint += (quantityString + priceString);
            } else if (firstParam.equals("p")) {
                stringToPrint += (priceString + quantityString);
            }
        } else if (qExists) {
            stringToPrint += quantityString;
        } else if (pExists) {
            stringToPrint += priceString;
        }

        printIndent(stringToPrint);
    }
}
