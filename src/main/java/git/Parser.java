package git;

import exceptions.GitException;
import exceptions.InvalidCommandException;
import grocery.Grocery;
import grocery.GroceryList;

/**
 * Deals with commands entered by user.
 */
public class Parser {
    private GroceryList groceryList;
    private Ui ui;

    private boolean isRunning;

    public Parser() {
        groceryList = new GroceryList();
        ui = new Ui();
        isRunning = true;
    }

    /**
     * Handles commands.
     *
     * @param commandParts Command and its details.
     * @throws GitException Exception thrown depending on specific error.
     */
    public void executeCommand(String[] commandParts) throws GitException {
        assert commandParts.length == 2 : "Command passed in wrong format";

        switch (commandParts[0]) {
        case "add":
            // Assuming the format is "add GROCERY"
            Grocery grocery = new Grocery(commandParts[1], "", "");
            groceryList.addGrocery(grocery);
            break;

        case "exp":
            // if (commandParts.length < 2) throw new EmptyGroceryException();
            groceryList.setExpiration(commandParts[1]);
            break;

        case "amt":
            groceryList.setAmount(commandParts[1]);
            break;

        case "del":
            groceryList.removeGrocery(commandParts[1]);
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
            throw new InvalidCommandException();
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}
