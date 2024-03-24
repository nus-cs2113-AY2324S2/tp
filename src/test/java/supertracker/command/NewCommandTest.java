package supertracker.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import supertracker.TrackerException;
import supertracker.item.Inventory;
import supertracker.item.Item;
import supertracker.parser.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        LocalDate date = LocalDate.parse("22/08/2013", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Command command = new NewCommand(name, quantity, price, date);
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
        LocalDate date = LocalDate.parse("22/08/2013", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Command newCommand = new NewCommand(name, quantity, price, date);

        newCommand.execute();

        String userInput = "new n/milk q/100 p/5.00 e/22/08/2013";
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
    public void newCommand_invalidExpiryDate() {
        String invalidExpiryDateInput = "new n/milk q/100 p/5.33 e/hello";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(invalidExpiryDateInput));

        String invalidExpiryDateInputNumber = "new n/milk q/100 p/5.33 e/5.33";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(invalidExpiryDateInputNumber));

        String invalidExpiryDateYearFormat = "new n/milk q/100 p/5.33 e/22/11/22331";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(invalidExpiryDateYearFormat));

        String invalidExpiryDateOrder = "new n/milk q/100 p/5.33 e/2113/11/13";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(invalidExpiryDateOrder));
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
