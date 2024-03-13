package grocery;

import exceptions.GitException;
import exceptions.NoSuchGroceryException;
import exceptions.WrongFormatException;

import java.util.ArrayList;
import java.util.List;

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
        groceries.add(grocery);
        System.out.println(grocery.getName() + " added!");
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
            System.out.println(grocery.getName() + " will expire on: " + grocery.getExpiration());
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
            System.out.println(grocery.getName() + ": " + grocery.getAmount());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongFormatException("amt");
        }
    }

    /**
     * Lists all the user's groceries.
     */
    public void listGroceries() {
        System.out.println("Here are your groceries!");
        for (Grocery grocery: groceries) {
            System.out.println(" - " + grocery.printGrocery());
        }
    }

}
