package seedu.grocery;

import java.util.ArrayList;
import java.util.List;

public class GroceryList {
    private List<Grocery> groceries = new ArrayList<>();

    public void addGrocery(Grocery grocery) {
        groceries.add(grocery);
    }

    // Additional methods for updating grocery items
}
