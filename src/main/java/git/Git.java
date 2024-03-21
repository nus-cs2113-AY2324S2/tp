package git;

import exceptions.GitException;
import exceptions.InvalidCommandException;
import grocery.Grocery;
import grocery.GroceryList;

/**
 * Represents the Grocery in Time (GiT) program, allowing users to store and track their groceries!
 */
public class Git {
    // ATTRIBUTES
    private Ui ui;
    private boolean isRunning;

    private Parser parser;

    // METHODS
    /**
     * Initialise Git.
     */
    public Git() {
        ui = new Ui();
        parser = new Parser();
        isRunning = true;
    }

    /**
     * Runs Git.
     */
    private void run() {
        ui.printWelcome();
        while (isRunning) {
            try {
                String[] commandParts = ui.processInput();
                parser.executeCommand(commandParts);
                isRunning = parser.isRunning();
            } catch (GitException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * Main for GiT.
     */
    public static void main(String[] args) {
        new Git().run();
    }

}
