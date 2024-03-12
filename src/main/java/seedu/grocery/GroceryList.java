package seedu.grocery;

import java.util.ArrayList;
import java.util.List;

public class GroceryList {
    private List<Grocery> groceries = new ArrayList<>();

    public void addGrocery(Grocery grocery) {
        groceries.add(grocery);
    }

    public void listGroceries() {
        System.out.println("Here are your groceries!");
        for (Grocery grocery: groceries) {
            System.out.println(" - " + grocery.printGrocery());
        }
    }

    // Additional methods for updating grocery items
}
