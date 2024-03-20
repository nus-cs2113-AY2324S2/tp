package grocery;

import exceptions.GitException;
import exceptions.NoSuchGroceryException;
import exceptions.WrongFormatException;

import java.util.ArrayList;
import java.util.List;
import git.Ui;

/**
 * Stores all the user's groceries.
 */
public class GroceryList {
    private List<Grocery> groceries;

    /**
     * Constructs Grocerylist.
     */
    public GroceryList() {
        groceries = new ArrayList<>();
    }

    /**
     * Adds a grocery.
     */
    public void addGrocery(Grocery grocery) {
        if (grocery.getName() == null || grocery.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("The grocery name is invalid.");
        }
        try {
            groceries.add(grocery);
            Ui.printGroceryAdded(grocery);
        } catch (NullPointerException e) {
            System.out.println("Failed to add grocery: the groceries collection is null.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while adding the grocery: " + e.getMessage());
        }
    }
    

    /**
     * Returns the desired grocery.
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
     */
    public void setExpiration(String details) throws GitException {
        try {
            // Assuming the format is "exp GROCERY d/EXPIRATION_DATE"
            String[] expParts = details.split("d/", 2);
            Grocery grocery = getGrocery(expParts[0].strip());
            grocery.setExpiration(expParts[1].strip());
            Ui.printExpSet(grocery);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongFormatException("date");
        }
    }

    /**
     * Adds the amount of an existing grocery.
     */
    public void setAmount(String details) throws GitException {
        try {
            // Assuming the format is "amt GROCERY a/AMOUNT"
            String[] expParts = details.split("a/", 2);
            Grocery grocery = getGrocery(expParts[0].strip());
            grocery.setAmount(expParts[1].strip());
            Ui.printAmtSet(grocery);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongFormatException("amt");
        }
    }

    /**
     * Lists all the user's groceries.
     */
    public void listGroceries() {
        int size = groceries.size();
        if (size == 0) {
            Ui.printNoGrocery();
            return;
        }
        Ui.printGroceryList(groceries);
    }

    public void removeGrocery(String details) throws NoSuchGroceryException {
        // Assuming the format is "del GROCERY"
        Grocery grocery = getGrocery(details);
        groceries.remove(grocery);
        Ui.printGroceryRemoved(grocery, groceries);
    }
}
