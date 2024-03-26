package seedu.stockpal.data.product;

import org.junit.jupiter.api.Test;
import seedu.stockpal.exceptions.InsufficientAmountException;
import seedu.stockpal.exceptions.InventoryQuantityOverflowException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class QuantityTest {

    @Test
    public void updateIncreaseQuantity_anyInteger_success() {
        Quantity quantityObject = new Quantity(0, false);
        try {
            quantityObject.updateIncreaseQuantity(quantityObject, 10);
        } catch (InventoryQuantityOverflowException iqoe) {
            fail();
        }
        assertEquals(10, quantityObject.quantity);
    }

    @Test
    public void updateIncreaseQuantity_anyInteger_inventoryQuantityOverflowExceptionThrown() {
        Quantity quantityObject = new Quantity(10, false);
        try {
            quantityObject.updateIncreaseQuantity(quantityObject, Integer.MAX_VALUE);
            fail("Expected InventoryQuantityOverflowException was not thrown");
        } catch (InventoryQuantityOverflowException iqoe) {
            assertEquals("Overflow detected. No Change to quantity.", iqoe.getMessage());
        }
        assertEquals(10, quantityObject.quantity);
    }

    @Test
    public void updateDecreaseQuantity_anyInteger_success() {
        Quantity quantityObject = new Quantity(10, false);
        try {
            quantityObject.updateDecreaseQuantity(quantityObject, 5);
        } catch (InsufficientAmountException iae) {
            fail();
        }
        assertEquals(5, quantityObject.quantity);
    }

    @Test
    public void updateDecreaseQuantity_anyInteger_insufficientAmountExceptionThrown() {
        Quantity quantityObject = new Quantity(10, false);
        try {
            quantityObject.updateDecreaseQuantity(quantityObject, 15);
            fail("Expected InsufficientAmountException was not thrown");
        } catch (InsufficientAmountException iae) {
            assertEquals("Insufficient amount in inventory", iae.getMessage());
        }
        assertEquals(10, quantityObject.quantity);
    }
}
