package seedu.stockpal.data.product;

import org.junit.jupiter.api.Test;
import seedu.stockpal.exceptions.InsufficientAmountException;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void updateIncreaseQuantity_anyInteger_success() {
        Quantity quantityObject = new Quantity(0);
        quantityObject.updateIncreaseQuantity(10);
        assertEquals(10, quantityObject.quantity);
    }

    @Test
    public void updateDecreaseQuantity_anyInteger_success() {
        Quantity quantityObject = new Quantity(10);
        try {
            quantityObject.updateDecreaseQuantity(5);
        } catch (InsufficientAmountException e) {
            fail();
        }
        assertEquals(5, quantityObject.quantity);
    }

    @Test
    public void updateDecreaseQuantity_anyInteger_InsufficientAmountExceptionThrown() {
        Quantity quantityObject = new Quantity(10);
        try {
            quantityObject.updateDecreaseQuantity(15);
            fail("Expected InsufficientAmountException was not thrown");
        } catch (InsufficientAmountException e) {
            assertEquals("Insufficient amount in inventory", e.getMessage());
        }
        assertEquals(10, quantityObject.quantity);
    }
}
