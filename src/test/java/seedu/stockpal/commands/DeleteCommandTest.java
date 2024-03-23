package seedu.stockpal.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.exceptions.PidNotFoundException;
import seedu.stockpal.exceptions.StockPalException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandTest {
    public ProductList testProductList;
    public ProductList expectedProductList;

    @BeforeEach
    public void setUp() {
        Product testProduct1 = new Product("Test1", 1, null, null, 1);
        Product testProduct2 = new Product("Test2", 2, null, null, 2);
        Product testProduct3 = new Product("Test3", 3, null, null, 3);
        expectedProductList = new ProductList();
        testProductList = new ProductList();

        testProductList.addProduct(testProduct1);
        testProductList.addProduct(testProduct2);
        testProductList.addProduct(testProduct3);
    }

    @Test
    public void execute_productPidExists_success() {
        Product expectedProduct2 = new Product("Test2", 2, null, null, 2);
        Product expectedProduct3 = new Product("Test3", 3, null, null, 3);
        try {
            Integer pidToDelete = 1;
            DeleteCommand testDeleteCommand = new DeleteCommand(pidToDelete);
            expectedProductList.addProduct(expectedProduct2);
            expectedProductList.addProduct(expectedProduct3);
            testDeleteCommand.execute(testProductList);
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
    public void execute_productPidDoesNotExist_pidNotFoundExceptionThrown() {
        Product expectedProduct1 = new Product("Test1", 1, null, null, 1);
        Product expectedProduct2 = new Product("Test2", 2, null, null, 2);
        Product expectedProduct3 = new Product("Test3", 3, null, null, 3);
        Integer pidToDelete = 5;

        DeleteCommand testDeleteCommand = new DeleteCommand(pidToDelete);
        expectedProductList.addProduct(expectedProduct1);
        expectedProductList.addProduct(expectedProduct2);
        expectedProductList.addProduct(expectedProduct3);
        assertThrows(PidNotFoundException.class, () -> testDeleteCommand.execute(expectedProductList));
    }
}
