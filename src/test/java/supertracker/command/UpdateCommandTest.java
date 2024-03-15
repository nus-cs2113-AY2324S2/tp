package supertracker.command;

import org.junit.jupiter.api.Test;
import supertracker.item.Inventory;
import supertracker.item.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class UpdateCommandTest {
    @Test
    public void updateCommand_validData_correctlyConstructed(){
        String name = "Milk";
        int quantity = 100;
        double price = 5.00;

        int newQuantity = 200;
        double newPrice = 3.00;

        Command newCommand = new NewCommand("Milk", quantity, price);
        newCommand.execute();
        Command updateCommand = new UpdateCommand("Milk", 200, 3.00);
        updateCommand.execute();

        assertTrue(Inventory.contains(name));
        Item item = Inventory.get(name);
        assertNotNull(item);
        assertEquals(name, item.getName());
        assertEquals(newQuantity, item.getQuantity());
        assertEquals(newPrice, item.getPrice());
    }
}
