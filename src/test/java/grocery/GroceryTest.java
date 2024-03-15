package grocery;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GroceryTest {
    @Test
    public void printGrocery_noAmountNoExpiration_leaveEmpty() {
        Grocery grocery = new Grocery("apple", "", "");
        String message = "apple";
        assertEquals(message, grocery.printGrocery());
    }
}
