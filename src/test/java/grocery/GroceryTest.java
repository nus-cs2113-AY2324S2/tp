package grocery;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroceryTest {
    @Test
    public void printGrocery_noAmountNoExpiration_leaveEmpty() {
        Grocery grocery = new Grocery("apple", "", "");
        String message = "apple" + ", amount: " + ", expiration: ";
        assertEquals(message, grocery.printGrocery());
    }

}