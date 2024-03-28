package supertracker.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import supertracker.TrackerException;
import supertracker.item.Inventory;
import supertracker.item.Item;
import supertracker.parser.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandTest {
    private static final String NAME = "Milk";
    @BeforeEach
    public void setUp() {
        Inventory.clear();

        int quantity = 100;
        double price = 5.00;
        LocalDate date = LocalDate.parse("01/01/2113", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Command newCommand = new NewCommand(NAME, quantity, price, date);
        newCommand.execute();
    }

    @Test
    public void deleteCommand_validData_correctlyConstructed() {
        Command deleteCommand = new DeleteCommand(NAME);
        deleteCommand.execute();

        assertFalse(Inventory.contains(NAME));
        Item item = Inventory.get(NAME);
        assertNull(item);
    }

    @Test
    public void deleteCommand_missingParamInput() {
        String userInput = "delete";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }

    @Test
    public void addCommand_emptyParamInput() {
        String userInput = "delete n/";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }

    @Test
    public void addCommand_itemNotInList() {
        String userInput = "delete n/cake";
        assertThrows(TrackerException.class, () -> Parser.parseCommand(userInput));
    }
}
