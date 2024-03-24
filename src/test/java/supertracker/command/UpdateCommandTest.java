package supertracker.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import supertracker.parser.Parser;
import supertracker.TrackerException;
import supertracker.item.Inventory;
import supertracker.item.Item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class UpdateCommandTest {
    @BeforeEach
    public void setUp() {
        Inventory.clear();
    }

    @Test
    public void updateCommand_validData_correctlyConstructed(){
        String name = "Milk";
        int quantity = 100;
        double price = 5.00;
        LocalDate date = LocalDate.parse("22/08/2013", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        int newQuantity = 200;
        double newPrice = 3.00;

        Command newCommand = new NewCommand(name, quantity, price, date);
        newCommand.execute();
        Command updateCommand = new UpdateCommand(name, newQuantity, newPrice);
        updateCommand.execute();

        assertTrue(Inventory.contains(name));
        Item item = Inventory.get(name);
        assertNotNull(item);
        assertEquals(name, item.getName());
        assertEquals(newQuantity, item.getQuantity());
        assertEquals(newPrice, item.getPrice());
    }

    @Test
    public void updateCommand_invalidInput() {
        String name = "Milk";
        int quantity = 100;
        double price = 5.00;
        LocalDate date = LocalDate.parse("22/08/2013", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Command newCommand = new NewCommand(name, quantity, price, date);
        newCommand.execute();

        String userInput = "update n/Milk p/-1";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }

    @Test
    public void updateCommand_emptyParamInput() {
        String name = "Milk";
        int quantity = 100;
        double price = 5.00;
        LocalDate date = LocalDate.parse("22/08/2013", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Command newCommand = new NewCommand(name, quantity, price, date);
        newCommand.execute();

        String userInput = "update n/Milk p/";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }

    @Test
    public void updateCommand_itemNotInList() {
        String name = "Milk";
        int quantity = 100;
        double price = 5.00;
        LocalDate date = LocalDate.parse("22/08/2013", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Command newCommand = new NewCommand(name, quantity, price, date);
        newCommand.execute();

        String userInput = "update n/apple q/20 p/3";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }
}
