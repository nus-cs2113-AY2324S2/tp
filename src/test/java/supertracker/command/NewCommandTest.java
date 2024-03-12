package supertracker.command;

import org.junit.jupiter.api.Test;
import supertracker.item.Inventory;
import supertracker.item.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewCommandTest {
    @Test
    public void newCommand_validData_correctlyConstructed() {
        String name = "Milk";
        int quantity = 100;
        double price = 5.00;

        Command command = new NewCommand("Milk", 100, 5.00);
        command.execute();

        assertTrue(Inventory.contains(name));
        Item item = Inventory.get(name);
        assertNotNull(item);
        assertEquals(name, item.getName());
        assertEquals(quantity, item.getQuantity());
        assertEquals(price, item.getPrice());
    }
}
