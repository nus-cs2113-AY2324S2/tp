package grocery;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GroceryTest {
    @Test
    public void printGrocery_noAmountNoExpiration_leaveEmpty() {
        Grocery grocery = new Grocery("apple", 0, null);
        String message = "apple";
        assertEquals(message, grocery.printGrocery());
    }

    @Test
    public void printGrocery_correctAmtAndExp() {
        Grocery grocery = new Grocery("chicken", 1, LocalDate.now().plusDays(1));
        String message = "chicken" + ", amount: 1" + ", expiration: " + LocalDate.now().plusDays(1);
        assertEquals(message, grocery.printGrocery());
    }
}
