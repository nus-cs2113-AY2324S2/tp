import java.util.Scanner;

/**
 * The UI class handles user interaction by displaying messages and reading user input.
 */
public class UI {
    private Scanner scanner;

    /**
     * Constructs a new UI instance.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println("Welcome to LongAh!");
    }

    /**
     * Displays the command prompt.
     */
    public void showCommandPrompt() {
        System.out.println("Enter command:");
    }

    /**
     * Reads the user input.
     *
     * @return The user input as a String.
     */
    public String getUserInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a message.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
