package seedu.stockpal.ui;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static seedu.stockpal.common.Messages.HORIZONTAL_LINE;

public final class Ui {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static String getUserInput() throws NoSuchElementException {
        String input = "";
        try {
            input = scanner.nextLine();
        } catch (NoSuchElementException exception) {
            System.out.println("error");
            System.exit(0);
        }
        return input;
    }

    /**
     * Outputs the list of messages, and replace line-separators
     * with platform independent line-separator.
     *
     * @param messages List of messages to output.
     */
    public static void printToScreen(String... messages) {
        for (String message : messages) {
            System.out.println(message.replace("\n", LINE_SEPARATOR));
        }
    }

    public static void printWelcomeMessage() {
        printToScreen(Messages.MESSAGE_WELCOME);
    }

    public static void printGoodbyeMessage() {
        printToScreen(Messages.MESSAGE_GOODBYE);
    }

    public static void printListTasks(ProductList products) {
        for (int i = 0; i < products.getSize(); i ++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product);
            System.out.println(HORIZONTAL_LINE);
            System.out.println(product.getPid());
            System.out.println(product.getQuantity());
            System.out.println(product.getPrice());
            System.out.println(product.getDescription());
            System.out.println(HORIZONTAL_LINE);
        }
    }
}
