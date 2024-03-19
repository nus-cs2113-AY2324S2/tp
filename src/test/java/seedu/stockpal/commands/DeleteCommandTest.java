package seedu.stockpal.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.exceptions.PidNotFoundException;
import seedu.stockpal.exceptions.StockPalException;
import seedu.stockpal.storage.Storage;
import seedu.stockpal.storage.exception.InvalidStorageFilePathException;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteCommandTest {
    private static final String TEST_FILE_PATH = "src/test/data/NewCommandTest/Test.csv";
    public ProductList testProductList;
    private Storage storage;
    public ProductList expectedProductList;

    Product testProduct1 = new Product("Test1", 1, null, null, 1);
    Product testProduct2 = new Product("Test2", 2, null, null, 2);
    Product testProduct3 = new Product("Test3", 3, null, null, 3);

    Product expectedTestProduct1 = new Product("Test1", 1, null, null, 1);
    Product expectedTestProduct2 = new Product("Test2", 2, null, null, 2);
    Product expectedTestProduct3 = new Product("Test3", 3, null, null, 3);
    @BeforeEach
    public void setUp() throws InvalidStorageFilePathException {
        expectedProductList = new ProductList();
        testProductList = new ProductList();
        storage = new Storage(TEST_FILE_PATH);

        testProductList.addProduct(testProduct1);
        testProductList.addProduct(testProduct2);
        testProductList.addProduct(testProduct3);
    }

    @Test
    public void execute_productPIDExists_Success() {
        try {
            Integer pidToDelete = 1;
            DeleteCommand testDeleteCommand = new DeleteCommand(testProductList, pidToDelete, storage);
            expectedProductList.addProduct(expectedTestProduct2);
            expectedProductList.addProduct(expectedTestProduct3);
            testDeleteCommand.execute();
        } catch (StockPalException spe) {
            fail();
        }

        assertEquals(expectedProductList.getSize(), testProductList.getSize());
        for (int i = 0; i < expectedProductList.getSize(); i++) {
            Product expectedProduct = expectedProductList.getProducts().get(i);
            Product testProduct = testProductList.getProducts().get(i);

            assertEquals(expectedProduct.toString(), testProduct.toString());
        }
    }

    @Test
    public void execute_productPIDDoesNotExist_PIDNotFoundExceptionThrown() {
            Integer pidToDelete = 5;
            DeleteCommand testDeleteCommand = new DeleteCommand(testProductList, pidToDelete, storage);
            expectedProductList.addProduct(expectedTestProduct1);
            expectedProductList.addProduct(expectedTestProduct2);
            expectedProductList.addProduct(expectedTestProduct3);
            assertThrows(PidNotFoundException.class, testDeleteCommand::execute);
    }
}
