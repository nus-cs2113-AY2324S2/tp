package longah.handler;
import java.util.Scanner;

/**
 * The UI class handles user interaction by displaying messages and reading user input.
 */
public class UI {
    private static Scanner scanner = new Scanner(System.in);

    private static final String SEPARATOR = "____________________________________________________________";
    /**
     * Displays the welcome message along with ASCII art.
     */
    public static void showWelcomeMessage() {
        System.out.println(" /$$                                      /$$$$$$  /$$       /$$    ");
        System.out.println("| $$                                     /$$__  $$| $$      | $$    ");
        System.out.println("| $$        /$$$$$$  /$$$$$$$   /$$$$$$ | $$  \\ $$| $$$$$$$ | $$    ");
        System.out.println("| $$       /$$__  $$| $$__  $$ /$$__  $$| $$$$$$$$| $$__  $$| $$    ");
        System.out.println("| $$      | $$  \\ $$| $$  \\ $$| $$  \\ $$| $$__  $$| $$  \\ $$|__/    ");
        System.out.println("| $$      | $$  | $$| $$  | $$| $$  | $$| $$  | $$| $$  | $$        ");
        System.out.println("| $$$$$$$$|  $$$$$$/| $$  | $$|  $$$$$$$| $$  | $$| $$  | $$ /$$    ");
        System.out.println("|________/ \\______/ |__/  |__/ \\____  $$|__/  |__/|__/  |__/|__/    ");
        System.out.println("                               /$$  \\ $$                            ");
        System.out.println("                              |  $$$$$$/                            ");
        System.out.println("                               \\______/                             ");
        System.out.println("Welcome to LongAh!");
    }

    /**
     * Displays the command prompt.
     */
    public static void showCommandPrompt() {
        System.out.println("Enter command:");
    }

    /**
     * Reads the user input.
     *
     * @return The user input as a String.
     */
    public static String getUserInput() {
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

    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }
}
