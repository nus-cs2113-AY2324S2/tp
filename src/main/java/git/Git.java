package git;

import exceptions.GitException;
import grocery.Grocery;
import grocery.GroceryList;

/**
 * Represents the Grocery in Time (GiT) program, allowing users to store and track their groceries!
 */
public class Git {
    // ATTRIBUTES
    private GroceryList groceryList;
    private Ui ui;
    private boolean isRunning;

    // METHODS
    /**
     * Initialise Git.
     */
    public Git() {
        groceryList = new GroceryList();
        ui = new Ui();
        isRunning = true;
    }

    /**
     * Handles commands.
     *
     * @param command Command.
     * @param details Command details.
     * @throws GitException Exception thrown depending on specific error.
     */
    private void executeCommand(String command, String details) throws GitException {
        switch (command) {
        case "add":
            // Assuming the format is "add GROCERY"
            Grocery grocery = new Grocery(details, "", "");
            groceryList.addGrocery(grocery);
            break;

        case "exp":
            groceryList.setExpiration(details);
            break;

        case "amt":
            groceryList.setAmount(details);
            break;

        case "del":
            // groceryList.removeGrocery(commandParts[1]);
            break;

        case "list":
            groceryList.listGroceries();
            break;

        case "help":
            ui.displayHelp();
            break;

        case "exit":
            System.out.println("bye bye!");
            isRunning = false;
            break;

        default:
            System.out.println("Unknown command. Type 'help' for a list of commands.");
            break;
        }
    }

    /**
     * Runs Git.
     */
    private void run() {
        while (isRunning) {
            try {
                String[] commandParts = ui.processInput();
                executeCommand(commandParts[0], commandParts[1]);
            } catch (GitException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                // throw the other error
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
