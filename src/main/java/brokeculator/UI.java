package brokeculator;

import java.util.Scanner;

public class UI {
    private static final String STRING_DECORATION = "------------------------------------";
    private static Scanner scanner = new Scanner(System.in);

    /**
     * appends topLineDecoration and bottomLineDecoration to the top and bottom of message respectively
     * @param String message message to encapsulate
     * @param String topLineDecoration string to append to top of message
     * @param String bottomLineDecoration string to append to bottom of message
     * @return message appended with topLineDecoration and bottomLineDecoration at top and bottom respectively
     */
    public static String prettify(String message, String topLineDecoration, String bottomLineDecoration) {
        return topLineDecoration + System.lineSeparator()
                + message + System.lineSeparator() + bottomLineDecoration;
    }

    /**
     * function for retrieving user input line by line
     * @return String singleLineUserInput single line of user input
     */
    public static String getUserInput() {
        try {
            String singleLineUserInput = scanner.nextLine();
            return singleLineUserInput;
        } catch (Exception e) {
            UI.print("Error in scanning input, recreating scanner");
            recreateScanner();
            return null;
        }
    }
    private static void recreateScanner() {
        scanner = new Scanner(System.in);
    }
    /**
     *  prints out message to stdout with the decorators ------ at the top and bottom of the file
     * @param message the message to print
     */
    public static void print(String message){
        System.out.println(prettify(message, STRING_DECORATION, STRING_DECORATION));
    }
}
