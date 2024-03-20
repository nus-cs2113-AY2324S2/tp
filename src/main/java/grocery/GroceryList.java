package grocery;

import git.Ui;
import exceptions.GitException;
import exceptions.EmptyGroceryException;
import exceptions.IncompleteCommandException;
import exceptions.NoSuchGroceryException;
import exceptions.WrongFormatException;

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
            return groceries.get(index);
        } else {
            throw new NoSuchGroceryException();
        }
    }

    /**
     * Adds the expiration date of an existing grocery.
     *
     * @throws GitException Exception thrown depending on error.
     */
    public void setExpiration(String details) throws GitException {
        if (details.isEmpty()) {
            throw new EmptyGroceryException();
        }

        // Assuming the format is "exp GROCERY d/EXPIRATION_DATE"
        String parameter = "d/";
        String[] expParts = details.split(parameter, 2);
        Grocery grocery = getGrocery(expParts[0].strip());

        if (expParts.length < 2) {
            throw new WrongFormatException("date");
        }

        String date = expParts[1].strip();
        if (date.isEmpty()) {
            throw new IncompleteCommandException(parameter);
        } else {
            grocery.setExpiration(date);
            Ui.printExpSet(grocery);
        }
    }

    /**
     * Adds the amount of an existing grocery.
     *
     * @throws GitException Exception thrown depending on error.
     */
    public void setAmount(String details) throws GitException {
        if (details.isEmpty()) {
            throw new EmptyGroceryException();
        }

        // Assuming the format is "amt GROCERY a/AMOUNT"
        String parameter = "a/";
        String[] amtParts = details.split(parameter, 2);
        Grocery grocery = getGrocery(amtParts[0].strip());

        if (amtParts.length < 2) {
            throw new WrongFormatException("amt");
        }

        String amount = amtParts[1].strip();
        if (amount.isEmpty()) {
            throw new IncompleteCommandException(parameter);
        } else {
            grocery.setAmount(amount);
            Ui.printAmtSet(grocery);
        }
    }

    /**
     * Lists all the user's groceries.
     */
    public void listGroceries() {
        assert (!groceries.isEmpty()) : "There is nothing to list.";
        int size = groceries.size();
        if (size == 0) {
            Ui.printNoGrocery();
            return;
        }
        Ui.printGroceryList(groceries);
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
