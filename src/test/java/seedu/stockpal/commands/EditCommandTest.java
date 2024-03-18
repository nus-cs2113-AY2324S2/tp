package seedu.stockpal.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.Storage;
import seedu.stockpal.storage.exception.InvalidStorageFilePathException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private ProductList productList = new ProductList();
    private Storage storage;
    @BeforeEach
    public void setUp() throws InvalidStorageFilePathException {
        storage = new Storage();

        System.setOut(new PrintStream(outContent));

        Product corn = new Product("Corn", 50, 1.00, "It's corn!", 1);
        productList.addProduct(corn);

        Product milk = new Product("Milk", 10, 1.50, "Contains lactose.", 2);
        productList.addProduct(milk);

        Product watermelon = new Product("Banana", 15, 5.00, "A bunch of bananas", 3);
        productList.addProduct(watermelon);
    }

    @Test
    public void editProduct_editName_nameUpdated() throws StockPalException {
        Command editCommand = new EditCommand(productList, 2, "Cheese", null, null, null, storage);
        editCommand.execute();

        Pid pid = new Pid(2);
        int index = productList.findProductIndex(pid);
        Product product = productList.get(index);
        assertEquals("Name: Cheese", product.getName().toString());
        assertEquals("Quantity: 10", product.getQuantity().toString());
        assertEquals("Price: 1.50", product.getPrice().toString());
        assertEquals("Description: Contains lactose.", product.getDescription().toString());
    }

    @Test
    public void editProduct_editQuantity_quantityUpdated() throws StockPalException {
        Command editCommand = new EditCommand(productList, 2, null, 20, null, null, storage);
        editCommand.execute();

        Pid pid = new Pid(2);
        int index = productList.findProductIndex(pid);
        Product product = productList.get(index);
        assertEquals("Name: Milk", product.getName().toString());
        assertEquals("Quantity: 20", product.getQuantity().toString());
        assertEquals("Price: 1.50", product.getPrice().toString());
        assertEquals("Description: Contains lactose.", product.getDescription().toString());
    }

    @Test
    public void editProduct_editPrice_priceUpdated() throws StockPalException {
        Command editCommand = new EditCommand(productList, 2, null, null, 3.10, null, storage);
        editCommand.execute();

        Pid pid = new Pid(2);
        int index = productList.findProductIndex(pid);
        Product product = productList.get(index);
        assertEquals("Name: Milk", product.getName().toString());
        assertEquals("Quantity: 10", product.getQuantity().toString());
        assertEquals("Price: 3.10", product.getPrice().toString());
        assertEquals("Description: Contains lactose.", product.getDescription().toString());
    }

    @Test
    public void editProduct_editDescription_descriptionUpdated() throws StockPalException {
        Command editCommand = new EditCommand(productList, 2, null, null, null, "Made by happy cows!", storage);
        editCommand.execute();

        Pid pid = new Pid(2);
        int index = productList.findProductIndex(pid);
        Product product = productList.get(index);
        assertEquals("Name: Milk", product.getName().toString());
        assertEquals("Quantity: 10", product.getQuantity().toString());
        assertEquals("Price: 1.50", product.getPrice().toString());
        assertEquals("Description: Made by happy cows!", product.getDescription().toString());
    }

    @Test
    public void editProduct_editAllAttributes_allAttributesUpdated() throws StockPalException {
        Command editCommand = new EditCommand(productList, 2, "Parmesan", 15, 3.00, "Made by happy cows!", storage);
        editCommand.execute();

        Pid pid = new Pid(2);
        int index = productList.findProductIndex(pid);
        Product product = productList.get(index);
        assertEquals("Name: Parmesan", product.getName().toString());
        assertEquals("Quantity: 15", product.getQuantity().toString());
        assertEquals("Price: 3.00", product.getPrice().toString());
        assertEquals("Description: Made by happy cows!", product.getDescription().toString());
    }

    @Test
    public void editProduct_noParameters_exceptionThrown() throws StockPalException {
        Command editCommand = new EditCommand(productList, 3, null, null, null, null, storage);
        editCommand.execute();
        assertEquals(Messages.MESSAGE_ERROR_MISSING_PARAMETERS + System.lineSeparator(), outContent.toString());
    }
}
