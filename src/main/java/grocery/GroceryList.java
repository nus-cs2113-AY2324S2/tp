package grocery;

import exceptions.InvalidAmountException;
import git.Ui;
import exceptions.GitException;
import exceptions.commands.EmptyGroceryException;
import exceptions.commands.IncompleteCommandException;
import exceptions.commands.NoSuchGroceryException;
import exceptions.commands.WrongFormatException;
import exceptions.CannotUseException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Stores all the user's groceries.
 */
public class GroceryList {
    private List<Grocery> groceries;
    private Logger logger;

    /**
     * Constructs GroceryList.
     */
    public GroceryList() {
        groceries = new ArrayList<>();
        LoggerGroceryList.setupLogger();
        logger = Logger.getLogger(GroceryList.class.getName());
    }

    /**
     * Adds a grocery.
     */
    public void addGrocery(Grocery grocery) throws EmptyGroceryException {
        if (grocery.getName() == null || grocery.getName().trim().isEmpty()) {
            throw new EmptyGroceryException();
        }

        try {
            groceries.add(grocery);
            Ui.printGroceryAdded(grocery);
            assert groceries.contains(grocery) : "Grocery should be added to the list";
        } catch (NullPointerException e) {
            System.out.println("Failed to add grocery: he groceries collection is null.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while adding the grocery: " + e.getMessage());
        }

        logger.log(Level.INFO, "Added " + grocery.printGrocery());
    }

    /**
     * Returns the desired grocery.
     *
     * @throws NoSuchGroceryException Selected grocery does not exist.
     */
    private Grocery getGrocery(String name) throws NoSuchGroceryException {
        int index = -1;
        for (Grocery grocery : groceries) {
            if(grocery.getName().equals(name)) {
                index = groceries.indexOf(grocery);
                break;
            }
        }

        if (index != -1) {
            assert groceries != null : "Found grocery should not be null";
            return groceries.get(index);
        } else {
            throw new NoSuchGroceryException();
        }
    }

    /**
     * Checks whether details are valid, else throw GitException accordingly.
     *
     * @param details User input.
     * @param command Command word.
     * @param parameter Parameter for the command.
     * @return String array of valid details.
     * @throws GitException Exception thrown depending on error.
     */
    private String[] checkDetails(String details, String command, String parameter) throws GitException {
        if (details.isEmpty()) {
            throw new EmptyGroceryException();
        }

        String[] detailParts = details.split(parameter, 2);
        Grocery grocery = getGrocery(detailParts[0].strip());
        if (detailParts.length < 2) {
            throw new WrongFormatException(command);
        }

        String attribute = detailParts[1].strip();
        if (attribute.isEmpty()) {
            throw new IncompleteCommandException(parameter);
        }

        return detailParts;
    }

    /**
     * Adds the expiration date of an existing grocery.
     *
     * @throws GitException Exception thrown depending on error.
     */
    public void editExpiration(String details) throws GitException {
        // Assuming the format is "exp GROCERY d/EXPIRATION_DATE"
        String[] expParts = checkDetails(details, "exp", "d/");
        Grocery grocery = getGrocery(expParts[0].strip());
        String date = expParts[1].strip();

        grocery.setExpiration(date);
        assert grocery.getExpiration().equals(date) : "Expiration date should be set correctly";
        Ui.printExpSet(grocery);

    }

    /**
     * Sets the amount of an existing grocery.
     *
     * @param details User input.
     * @param use True to reduce the amount of a grocery, false to set a new amount.
     * @throws GitException Exception thrown depending on error.
     */
    public void editAmount(String details, boolean use) throws GitException {
        // Assuming the format is "amt GROCERY a/AMOUNT"
        String[] amtParts = checkDetails(details, "amt", "a/");
        Grocery grocery = getGrocery(amtParts[0].strip());
        String amountString = amtParts[1].strip();

        int amount = 0;
        try {
            amount = Integer.parseInt(amountString);
        } catch (NumberFormatException e) {
            throw new InvalidAmountException();
        }

        // "use" is not valid if an amount was not previously set
        if (use && grocery.getAmount() == 0) {
            throw new CannotUseException();
        }

        int finalAmount = use ? Math.max(0, grocery.getAmount() - amount) : amount;
        grocery.setAmount(finalAmount);
        if (finalAmount == 0) {
            Ui.printAmtDepleted(grocery);
        } else {
            Ui.printAmtSet(grocery);
        }
    }

    /**
     * Lists all the user's groceries.
     */
    public void listGroceries() {
        int size = groceries.size();
        if (size == 0) {
            Ui.printNoGrocery();
        } else {
            Ui.printGroceryList(groceries);
        }
    }

    /**
     * Removes a grocery.
     *
     * @throws GitException Exception thrown depending on error.
     */
    public void removeGrocery(String details) throws GitException {
        assert (!groceries.isEmpty()) : "There is nothing to remove.";
        if (details.isEmpty()) {
            throw new EmptyGroceryException();
        }

        // Assuming the format is "del GROCERY"
        Grocery grocery = getGrocery(details);
        groceries.remove(grocery);
        Ui.printGroceryRemoved(grocery, groceries);
    }
}
