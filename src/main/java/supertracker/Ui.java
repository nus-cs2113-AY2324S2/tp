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
        printIndent("Price: $" + String.format("%.2f", item.getPrice()));
    }
}
