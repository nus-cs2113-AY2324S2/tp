package seedu.stockpal.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.stockpal.data.ProductList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    private final ProductList emptyProductList = new ProductList();
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    //Standard output stream will be redirected to variable output, temporarily
    @BeforeEach
    public void setUpStream() {
        System.setOut(new PrintStream(output));
    }

    //Reset to the original state
    @AfterEach
    public void restoreStream() {
        System.setOut(originalOut);
    }
    @Test
    public void emptyListTest() {
        ListCommand command = new ListCommand(emptyProductList);
        command.execute();
        String expected = "ProductList is empty\n".trim();
        //System.out.println(output);
        assertEquals(expected, output.toString().trim());
    }
}
