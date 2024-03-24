package supertracker.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import supertracker.TrackerException;
import supertracker.item.Inventory;
import supertracker.parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String LIST_INTRO = "     There are 3 unique items in your inventory:" + LINE_SEPARATOR;
    private static final String INDEX_1 = "     1.";
    private static final String INDEX_2 = "     2.";
    private static final String INDEX_3 = "     3.";
    private static final String A_NAME = " Name: Apple";
    private static final String B_NAME = " Name: Berry";
    private static final String C_NAME = " Name: Cake";
    private static final String A_QUANTITY = "    Quantity: 3";
    private static final String B_QUANTITY = "    Quantity: 2";
    private static final String C_QUANTITY = "    Quantity: 1";
    private static final String A_PRICE = "    Price: $2.00";
    private static final String B_PRICE = "    Price: $1.00";
    private static final String C_PRICE = "    Price: $3.00";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeAll
    public static void setUp() {
        Inventory.clear();
        Command[] commands = {
            new NewCommand("Apple", 3, 2.00),
            new NewCommand("Berry", 2, 1.00),
            new NewCommand("Cake", 1, 3.00)
        };
        for (Command c : commands) {
            c.execute();
        }
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void listCommand_alphabeticalAscending_correctlyConstructed() throws TrackerException {
        String userInput = "list";
        Command c = Parser.parseCommand(userInput);
        c.execute();
        String expected = LIST_INTRO +
                INDEX_1 + A_NAME + LINE_SEPARATOR +
                INDEX_2 + B_NAME + LINE_SEPARATOR +
                INDEX_3 + C_NAME + LINE_SEPARATOR;
        String actual = outContent.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void listCommand_alphabeticalDescending_correctlyConstructed() throws TrackerException {
        String userInput = "list r/";
        Command c = Parser.parseCommand(userInput);
        c.execute();
        String expected = LIST_INTRO +
                INDEX_1 + C_NAME + LINE_SEPARATOR +
                INDEX_2 + B_NAME + LINE_SEPARATOR +
                INDEX_3 + A_NAME + LINE_SEPARATOR;
        String actual = outContent.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void listCommand_quantityAscending_correctlyConstructed() throws TrackerException {
        String userInput = "list q/ sq/ sp/";
        Command c = Parser.parseCommand(userInput);
        c.execute();
        String expected = LIST_INTRO +
                INDEX_1 + C_NAME + C_QUANTITY + LINE_SEPARATOR +
                INDEX_2 + B_NAME + B_QUANTITY + LINE_SEPARATOR +
                INDEX_3 + A_NAME + A_QUANTITY + LINE_SEPARATOR;
        String actual = outContent.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void listCommand_quantityDescending_correctlyConstructed() throws TrackerException {
        String userInput = "list q/ sq/ sp/ r/";
        Command c = Parser.parseCommand(userInput);
        c.execute();
        String expected = LIST_INTRO +
                INDEX_1 + A_NAME + A_QUANTITY + LINE_SEPARATOR +
                INDEX_2 + B_NAME + B_QUANTITY + LINE_SEPARATOR +
                INDEX_3 + C_NAME + C_QUANTITY + LINE_SEPARATOR;
        String actual = outContent.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void listCommand_priceAscending_correctlyConstructed() throws TrackerException {
        String userInput = "list p/ sp/ sq/";
        Command c = Parser.parseCommand(userInput);
        c.execute();
        String expected = LIST_INTRO +
                INDEX_1 + B_NAME + B_PRICE + LINE_SEPARATOR +
                INDEX_2 + A_NAME + A_PRICE + LINE_SEPARATOR +
                INDEX_3 + C_NAME + C_PRICE + LINE_SEPARATOR;
        String actual = outContent.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void listCommand_priceDescending_correctlyConstructed() throws TrackerException {
        String userInput = "list p/ sp/ sq/ r/";
        Command c = Parser.parseCommand(userInput);
        c.execute();
        String expected = LIST_INTRO +
                INDEX_1 + C_NAME + C_PRICE + LINE_SEPARATOR +
                INDEX_2 + A_NAME + A_PRICE + LINE_SEPARATOR +
                INDEX_3 + B_NAME + B_PRICE + LINE_SEPARATOR;
        String actual = outContent.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void listCommand_quantityBeforePrice_correctlyConstructed() throws TrackerException {
        String userInput = "list q/ p/ q/";
        Command c = Parser.parseCommand(userInput);
        c.execute();
        String expected = LIST_INTRO +
                INDEX_1 + A_NAME + A_QUANTITY + A_PRICE + LINE_SEPARATOR +
                INDEX_2 + B_NAME + B_QUANTITY + B_PRICE + LINE_SEPARATOR +
                INDEX_3 + C_NAME + C_QUANTITY + C_PRICE + LINE_SEPARATOR;
        String actual = outContent.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void listCommand_priceBeforeQuantity_correctlyConstructed() throws TrackerException {
        String userInput = "list p/ q/ p/";
        Command c = Parser.parseCommand(userInput);
        c.execute();
        String expected = LIST_INTRO +
                INDEX_1 + A_NAME + A_PRICE + A_QUANTITY + LINE_SEPARATOR +
                INDEX_2 + B_NAME + B_PRICE + B_QUANTITY + LINE_SEPARATOR +
                INDEX_3 + C_NAME + C_PRICE + C_QUANTITY + LINE_SEPARATOR;
        String actual = outContent.toString();
        assertEquals(expected, actual);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
