package seedu.stockpal.commands;

import org.junit.jupiter.api.Test;
import seedu.stockpal.data.ProductList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class NewCommandTest {

    @Test
    void newCommand_allFieldsFilled_expectCorrectAllocation() {
        final ProductList productList = new ProductList();
        NewCommand userInput = new NewCommand(productList, "chocolate", 100, 2.00, "ingredient");
        userInput.execute();

        assertEquals("chocolate",productList.products.get(0).getProductName().getName());
        assertEquals(100,productList.products.get(0).getProductQuantity().getQuantity());
        assertEquals(2.00,productList.products.get(0).getProductPrice().getPrice());
        assertEquals("ingredient",productList.products.get(0).getProductDescription().getDescription());
        assertEquals(1, productList.products.get(0).getProductPid().getPid());
    }

    @Test
    void newCommand_compulsoryFieldsFilled_expectCorrectAllocation() {
        final ProductList productList = new ProductList();
        NewCommand userInput = new NewCommand(productList, "chocolate", 100, null, null);
        userInput.execute();

        assertEquals("chocolate",productList.products.get(0).getProductName().getName());
        assertEquals(100,productList.products.get(0).getProductQuantity().getQuantity());
        assertNull(productList.products.get(0).getProductPrice().getPrice());
        assertNull(productList.products.get(0).getProductDescription().getDescription());
        assertEquals(1, productList.products.get(0).getProductPid().getPid());
    }

    @Test
    void newCommand_twoProductsWithCompulsoryFieldsFilled_expectCorrectAllocation() {
        final ProductList productList = new ProductList();
        NewCommand userInput1 = new NewCommand(productList, "chocolate", 100, 2.00, "ingredient");
        userInput1.execute();

        NewCommand userInput2 = new NewCommand(productList, "strawberry", 200, null, null);
        userInput2.execute();

        assertEquals("chocolate",productList.products.get(0).getProductName().getName());
        assertEquals(1, productList.products.get(0).getProductPid().getPid());

        assertEquals("strawberry",productList.products.get(1).getProductName().getName());
        assertEquals(2, productList.products.get(1).getProductPid().getPid());
    }

}
