package supertracker.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import supertracker.TrackerException;
import supertracker.item.Inventory;
import supertracker.item.Item;
import supertracker.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewCommandTest {
    @BeforeEach
    public void setUp() {
        Inventory.clear();
    }

    @Test
    public void newCommand_validData_correctlyConstructed() {
        String name = "Milk";
        int quantity = 100;
        double price = 5.00;

        Command command = new NewCommand(name, quantity, price);
        command.execute();

        assertTrue(Inventory.contains(name));
        Item item = Inventory.get(name);
        assertNotNull(item);
        assertEquals(name, item.getName());
        assertEquals(quantity, item.getQuantity());
        assertEquals(price, item.getPrice());
    }

    @Test
    public void newCommand_missingParamInput() {
        String userInput = "new n/Milk";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }

    @Test
    public void newCommand_emptyParamInput() {
        String userInput = "new n/Milk q/100 p/";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }

    @Test
    public void newCommand_itemAlreadyInList() {
        String name = "Milk";
        int quantity = 100;
        double price = 5.00;

        Command newCommand = new NewCommand(name, quantity, price);
        newCommand.execute();

        String userInput = "new n/milk q/100 p/5.00";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }

    @Test
    public void newCommand_quantityOrPriceLessThanZero() {
        String invalidQuantityInput = "new n/milk q/-100 p/5.00";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(invalidQuantityInput));

        String invalidPriceInput = "new n/milk q/100 p/-5.00";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(invalidPriceInput));
    }

    @Test
    public void newCommand_priceRoundedTo2Dp() throws TrackerException {
        String name = "Milk";

        String userInput = "new n/" + name + " q/100 p/5.555";
        Command command = Parser.parseCommand(userInput);
        command.execute();

        assertTrue(Inventory.contains(name));
        Item item = Inventory.get(name);
        assertNotNull(item);
        assertEquals(5.56, item.getPrice());
    }
}
