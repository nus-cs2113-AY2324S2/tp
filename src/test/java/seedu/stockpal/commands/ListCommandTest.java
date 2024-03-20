package seedu.stockpal.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.storage.exception.InvalidStorageFilePathException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.stockpal.common.Messages.MESSAGE_EMPTY_LIST;

public class ListCommandTest {
    private static final String LIST_TEST_FILE_TO_COMPARE = "src/test/data/ListCommandTest/ListTest.txt";
    private final ProductList emptyProductList = new ProductList();
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ProductList productList = new ProductList();

    //Standard output stream will be redirected to variable output, temporarily
    @BeforeEach
    public void setUpStreamForEmptyList() {
        System.setOut(new PrintStream(output));
    }

    //Reset to the original state
    @AfterEach
    public void restoreStreamForEmptyList() {
        System.setOut(originalOut);
    }
    @Test
    public void emptyListTest() {
        ListCommand command = new ListCommand(emptyProductList);
        command.execute();
        assertEquals(MESSAGE_EMPTY_LIST, output.toString().trim());
    }

    @BeforeEach
    public void setUpStreamForList() throws InvalidStorageFilePathException {
        Product corn = new Product("Corn", 50, 1.00, "It's corn!", 1);
        productList.addProduct(corn);

        Product milk = new Product("Milk", 10, 1.50, null, 2);
        productList.addProduct(milk);

        Product banana = new Product("Banana", 15, null, "A bunch of bananas", 3);
        productList.addProduct(banana);

        Product egg = new Product("Eggs", 20, null, null, 4);
        productList.addProduct(egg);
    }

    //Reset to the original state
    @AfterEach
    public void restoreStreamForList() {
        System.setOut(originalOut);
    }

    @Test
    public void listTest() throws IOException {
        ListCommand command = new ListCommand(productList);
        command.execute();
        String expected = new String(Files.readAllBytes(Paths.get(LIST_TEST_FILE_TO_COMPARE)));
        assertEquals(expected, output.toString());
    }
}
