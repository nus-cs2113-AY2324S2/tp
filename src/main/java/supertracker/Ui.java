package supertracker;

import supertracker.item.Item;

public class Ui {
    private static final String LINE = "    --------------------------------------------------------------------------";
    public static void printIndent(String string) {
        System.out.println("     " + string);
    }

    public static void printLine() {
        System.out.println(LINE);
    }

    public static void greetUser() {
        printLine();
        printIndent("Hello, welcome to SuperTracker, how may I help you?");
        printLine();
    }

    public static void sayGoodbye() {
        printIndent("Goodbye!");
    }

    public static void printInvalidCommand() {
        printIndent("Sorry! Invalid command!");
    }

    public static void newCommandSuccess(Item item) {
        printIndent(item.getName() + " has been added to the inventory!");
        printIndent("Quantity: " + item.getQuantity());
        printIndent("Price: " + item.getPriceString());
    }

    public static void listIntro(int size) {
        if (size == 0) {
            printIndent("Nothing to list! No items in inventory!");
            return;
        }
        if (size == 1) {
            printIndent("There is 1 unique item in your inventory:");
            return;
        }
        printIndent("There are " + size + " unique items in your inventory:");
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
