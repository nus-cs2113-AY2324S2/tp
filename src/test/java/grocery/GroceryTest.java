package grocery;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GroceryTest {
    @Test
    public void printGrocery_noAmountNoExpiration_leaveEmpty() {
        Grocery grocery = new Grocery("apple", 0, null, 0);
        String message = "apple expiration date not set";
        assertEquals(message, grocery.printGrocery());
    }

    @Test
    public void printGrocery_correctAmtAndExp() {
        Grocery grocery = new Grocery("chicken", 1, LocalDate.now().plusDays(1), 0);
        String message = "chicken" + ", amount: 1" + ", expiration: " + LocalDate.now().plusDays(1);
        assertEquals(message, grocery.printGrocery());
    }
}
