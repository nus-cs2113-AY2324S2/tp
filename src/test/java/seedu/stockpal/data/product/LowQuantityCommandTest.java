package seedu.stockpal.data.product;

import org.junit.jupiter.api.Test;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.exceptions.NoLowQuantityException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LowQuantityCommandTest {
    private static final String LOW_QUANTITY_TEST_FILE = "src/test/data/LowQuantityCommandTest/LowQuantityTest";
    private static final String NO_LOW_QUANTITY_TEST_FILE = "src/test/data/LowQuantityCommandTest/NoLowQuantityTest";
    private final ProductList productList = new ProductList();
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();



    @Test
    public void testPrintLowQuantityProducts_printsLowQuantity() throws IOException {
        System.setOut(new PrintStream(output));
        Product corn = new Product("Pen", 50, 2.50, "Sarasa pen", 1);
        productList.addProduct(corn);

        Product milk = new Product("Eraser", 2, 1.50, null, 2);
        productList.addProduct(milk);

        Product banana = new Product("Ruler", 0, null, "Out of stock", 3);
        productList.addProduct(banana);

        try {
            productList.checkLowQuantityProducts();
        } catch (NoLowQuantityException nlqe) {
            fail("NoLowQuantityException should be thrown");
        }

        String expectedOutput = new String(Files.readAllBytes(Paths.get(LOW_QUANTITY_TEST_FILE))).trim();
        String printedOutput = output.toString().trim();
        assertEquals(expectedOutput, printedOutput);
    }

    @Test
    public void testPrintLowQuantityProducts_noLowQuantity() throws IOException {
        System.setOut(new PrintStream(output));
        Product corn = new Product("Pen", 50, 2.50, "Sarasa pen", 1);
        productList.addProduct(corn);
        Product milk = new Product("Eraser", 30, 1.50, null, 2);
        productList.addProduct(milk);
        Product banana = new Product("Ruler", 21, null, "Out of stock", 3);
        productList.addProduct(banana);

        assertThrows(NoLowQuantityException.class, productList::checkLowQuantityProducts);

        String printedOutput = output.toString().trim();

        String expectedOutput = new String(Files.readAllBytes(Paths.get(NO_LOW_QUANTITY_TEST_FILE))).trim();
        assertEquals(expectedOutput, printedOutput);
    }
}
