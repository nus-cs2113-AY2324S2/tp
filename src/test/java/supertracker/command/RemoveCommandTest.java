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

public class RemoveCommandTest {
    @BeforeEach
    public void setUp() {
        Inventory.clear();
    }

    @Test
    public void removeCommand_validData_correctlyConstructed(){
        String name = "Milk";
        int quantity = 100;
        double price = 5.00;

        int quantityToRemove = 50;
        int newQuantity = quantity - quantityToRemove;

        Command newCommand = new NewCommand(name, quantity, price);
        newCommand.execute();
        Command removeCommand = new RemoveCommand(name, quantityToRemove);
        removeCommand.execute();

        assertTrue(Inventory.contains(name));
        Item item = Inventory.get(name);
        assertNotNull(item);
        assertEquals(name, item.getName());
        assertEquals(newQuantity, item.getQuantity());
    }

    @Test
    public void removeCommand_missingParamInput() {
        String userInput = "remove n/Milk";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }

    @Test
    public void removeCommand_emptyParamInput() {
        String userInput = "remove n/Milk q/";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }

    @Test
    public void removeCommand_itemNotInList() {
        String userInput = "remove n/Milk q/50";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }

    @Test
    public void removeCommand_quantityLessThanZero() {
        String userInput = "new n/Milk q/-50";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }

    @Test
    public void removeCommand_exceedCurrentQuantity() {
        String name = "Milk";
        int quantity = 50;
        double price = 5.00;

        int quantityToRemove = 100;
        int newQuantity = 0;

        Command newCommand = new NewCommand(name, quantity, price);
        newCommand.execute();
        Command removeCommand = new RemoveCommand(name, quantityToRemove);
        removeCommand.execute();

        assertTrue(Inventory.contains(name));
        Item item = Inventory.get(name);
        assertNotNull(item);
        assertEquals(name, item.getName());
        assertEquals(newQuantity, item.getQuantity());
    }
}
