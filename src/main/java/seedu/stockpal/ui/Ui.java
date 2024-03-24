package seedu.stockpal.ui;

import org.apache.commons.text.WordUtils;
import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.Transaction;
import seedu.stockpal.data.TransactionList;
import seedu.stockpal.data.product.Product;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static seedu.stockpal.common.Messages.HORIZONTAL_LINE;
import static seedu.stockpal.common.Messages.LINE_SEPARATOR;

public final class Ui {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int WRAP_LENGTH = 81;

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
     * Outputs the list of messages as lines ending with line separator
     * , and replace line-separators with platform independent line-separator.
     *
     * @param messages List of messages to output.
     */
    public static void printToScreen(String... messages) {
        for (String message : messages) {
            String platformIndependentMessage = message.replace("\n", LINE_SEPARATOR);
            System.out.println(platformIndependentMessage);
        }
    }

    /**
     * Outputs the list of messages as lines ending without line separator
     * , and replace line-separators with platform independent line-separator.
     *
     * @param messages List of messages to output.
     */
    public static void printToScreenWithoutNewlineAtEnd(String... messages) {
        for (String message : messages) {
            String platformIndependentMessage = message.replace("\n", LINE_SEPARATOR);
            System.out.print(platformIndependentMessage);
        }
    }

    public static String indentTextIfRequired (String textToFormat) {
        return WordUtils.wrap(textToFormat, WRAP_LENGTH, LINE_SEPARATOR, true);
    }

    public static String wrapTextWithIndentation (String text, int padding) {
        String indentation = Messages.SINGLE_SPACE.repeat(padding);
        return text.replace("\n", LINE_SEPARATOR + indentation);
    }


    public static void printExceptionMessage(Exception exception) {
        printToScreen(exception.getMessage());
    }

    public static void printWelcomeMessage() {
        printToScreen(Messages.MESSAGE_WELCOME);
    }

    public static void printGoodbyeMessage() {
        printToScreen(Messages.MESSAGE_GOODBYE);
    }

    public static void printListTasks(ProductList products) {
        printToScreen(HORIZONTAL_LINE);
        for (int i = 0; i < products.getSize(); i++ ) {
            assert products.get(i) != null : "Product should not be a null object.";
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product);
            printToScreen(HORIZONTAL_LINE);
        }
    }

    public static void printEditSuccessMessage() {
        printToScreen(Messages.MESSAGE_EDIT_SUCCESS);
    }

    public static void printMissingParametersMessage() {
        Ui.printToScreen(Messages.MESSAGE_ERROR_MISSING_PARAMETERS);
    }

    public static void printInvalidPidMessage() {
        Ui.printToScreen(Messages.MESSAGE_ERROR_INVALID_PID);
    }

    public static void printDeleteSuccessMessage() {
        printToScreen(Messages.MESSAGE_DELETE_SUCCESS);
    }

    public static void printLowQuantityAlert() {
        printToScreen(Messages.ALERT_LOW_QUANTITY);
        printToScreen(HORIZONTAL_LINE);
    }

    public static void printThresholdWarningAlert() {
        printToScreen(Messages.ALERT_FIRST_LOW_QUANTITY_OCCURRENCE);
    }

    public static void printNoLowQuantity() {
        printToScreen(Messages.MESSAGE_NO_LOW_QUANTITY_PRODUCTS);
    }




    /**
     * Outputs the list of messages as lines ending with line separator
     * , and replace line-separators with platform independent line-separator.
     *
     * @param transactions List of transactions to output.
     */
    public static void printTransactionTasks(TransactionList transactions) {
        printToScreen(HORIZONTAL_LINE);
        for (int i = 0; i < transactions.getSize(); i++ ) {
            assert transactions.get(i) != null : "Product should not be a null object.";
            Transaction transaction = transactions.get(i);
            System.out.println((i + 1) + ". " + transaction);
        }
    }
}
