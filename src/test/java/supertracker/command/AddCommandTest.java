package supertracker.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import supertracker.TrackerException;
import supertracker.item.Inventory;
import supertracker.item.Item;
import supertracker.parser.Parser;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {
    @BeforeEach
    public void setUp() {
        Inventory.clear();
    }

    @Test
    public void addCommand_validData_correctlyConstructed(){
        String name = "Milk";
        int quantity = 100;
        double price = 5.00;

        int quantityToAdd = 50;
        int newQuantity = quantity + quantityToAdd;

        Command newCommand = new NewCommand(name, quantity, price);
        newCommand.execute();
        Command addCommand = new AddCommand(name, quantityToAdd);
        addCommand.execute();

        assertTrue(Inventory.contains(name));
        Item item = Inventory.get(name);
        assertNotNull(item);
        assertEquals(name, item.getName());
        assertEquals(newQuantity, item.getQuantity());
    }

    @Test
    public void addCommand_missingParamInput() {
        String userInput = "add n/Milk";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }

    @Test
    public void addCommand_emptyParamInput() {
        String userInput = "add n/Milk q/";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }

    @Test
    public void addCommand_itemNotInList() {
        String userInput = "add n/Milk q/100";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }

    @Test
    public void newCommand_quantityLessThanZero() {
        String userInput = "new n/Milk q/-100";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }
}
