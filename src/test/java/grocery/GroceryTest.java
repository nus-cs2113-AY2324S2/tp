package grocery;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GroceryTest {
    @Test
    public void printGrocery_noAmountNoExpiration_leaveEmpty() {
        Grocery grocery = new Grocery("apple", "", "");
        String message = "apple" + ", amount: " + ", expiration: ";
        assertEquals(message, grocery.printGrocery());
    }

    @Test
    public void printGrocery_correctAmtAndExp() {
        Grocery grocery = new Grocery("chicken", "1 leg", "soon");
        String message = "chicken" + ", amount: 1 leg" + ", expiration: soon";
        assertEquals(message, grocery.printGrocery());
    }
}
