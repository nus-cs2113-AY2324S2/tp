package seedu.stockpal.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.storage.Storage;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.exception.InvalidStorageFilePathException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class NewCommandTest {
    private static final String TEST_FILE_PATH = "src/test/data/NewCommandTest/Test.csv";
    public ProductList productList;
    public Storage storage;

    @BeforeEach
    public void setUp() throws InvalidStorageFilePathException {
        productList = new ProductList();
        storage = new Storage(TEST_FILE_PATH);
    }

    @Test
    void newCommand_allFieldsFilled_expectCorrectAllocation() throws StockPalException {
        NewCommand userInput = new NewCommand(productList, "chocolate", 100,
                2.00, "ingredient", storage);
        userInput.execute();

        assertEquals("chocolate",productList.getProducts().get(0).getName().getName());
        assertEquals(100,productList.getProducts().get(0).getQuantity().getQuantity());
        assertEquals(2.00,productList.getProducts().get(0).getPrice().getPrice());
        assertEquals("ingredient",productList.getProducts().get(0).getDescription().getDescription());
        assertEquals(1, productList.getProducts().get(0).getPid().getPid());
    }

    @Test
    void newCommand_compulsoryFieldsFilled_expectCorrectAllocation() throws StockPalException {
        NewCommand userInput = new NewCommand(productList, "chocolate",
                100, null, null, storage);
        userInput.execute();

        assertEquals("chocolate",productList.getProducts().get(0).getName().getName());
        assertEquals(100,productList.getProducts().get(0).getQuantity().getQuantity());
        assertNull(productList.getProducts().get(0).getPrice().getPrice());
        assertNull(productList.getProducts().get(0).getDescription().getDescription());
        assertEquals(1, productList.getProducts().get(0).getPid().getPid());
    }

    @Test
    void newCommand_twoProductsWithCompulsoryFieldsFilled_expectCorrectAllocation() throws StockPalException {
        NewCommand userInput1 = new NewCommand(productList, "chocolate", 100,
                2.00, "ingredient", storage);
        userInput1.execute();

        NewCommand userInput2 = new NewCommand(productList, "strawberry", 200,
                null, null, storage);
        userInput2.execute();

        assertEquals("chocolate",productList.products.get(0).getName().getName());
        assertEquals(1, productList.products.get(0).getPid().getPid());

        assertEquals("strawberry",productList.products.get(1).getName().getName());
        assertEquals(2, productList.products.get(1).getPid().getPid());
    }

}
