package longah.handler;

import java.util.Scanner;

/**
 * The UI class handles user interaction by displaying messages and reading user input.
 */
public class UI {
    private static Scanner scanner = new Scanner(System.in);

    // @@author haowern98
    /**
     * Displays the welcome message along with ASCII art.
     */
    public static void showWelcomeMessage() {
        UI.showMessage(" /$$                                      /$$$$$$  /$$       /$$    ");
        UI.showMessage("| $$                                     /$$__  $$| $$      | $$    ");
        UI.showMessage("| $$        /$$$$$$  /$$$$$$$   /$$$$$$ | $$  \\ $$| $$$$$$$ | $$    ");
        UI.showMessage("| $$       /$$__  $$| $$__  $$ /$$__  $$| $$$$$$$$| $$__  $$| $$    ");
        UI.showMessage("| $$      | $$  \\ $$| $$  \\ $$| $$  \\ $$| $$__  $$| $$  \\ $$|__/    ");
        UI.showMessage("| $$      | $$  | $$| $$  | $$| $$  | $$| $$  | $$| $$  | $$        ");
        UI.showMessage("| $$$$$$$$|  $$$$$$/| $$  | $$|  $$$$$$$| $$  | $$| $$  | $$ /$$    ");
        UI.showMessage("|________/ \\______/ |__/  |__/ \\____  $$|__/  |__/|__/  |__/|__/    ");
        UI.showMessage("                               /$$  \\ $$                            ");
        UI.showMessage("                              |  $$$$$$/                            ");
        UI.showMessage("                               \\______/                             ");
        UI.showMessage("Welcome to LongAh!");
    }

    /**
     * Displays the command prompt.
     */
    public static void showCommandPrompt() {
        System.out.println("Enter command: ");
    }

    /**
     * Reads the user input.
     *
     * @return The user input as a String.
     */
    public static String getUserInput() {
        if (!scanner.hasNextLine()) {
            return null;
        }
        return scanner.nextLine().trim();
    }

    /**
     * Displays a message.
     *
     * @param message The message to display.
     */
    public static void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays a message.
     *
     * @param message The message to display.
     * @param newLine Whether to print a new line after the message.
     */
    public static void showMessage(String message, boolean newLine) {
        if (newLine) {
            System.out.println(message);
        } else {
            System.out.print(message);
        };
    }

    /**
     * Checks if there is another line of input.
     * Used for text ui testing.
     *
     * @return true if there is another line of input, false otherwise.
     */
    public static boolean hasNextLine() {
        return scanner.hasNextLine();
    }
}
