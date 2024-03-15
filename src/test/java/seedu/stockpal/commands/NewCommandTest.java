package seedu.stockpal.commands;

import org.junit.jupiter.api.Test;
import seedu.stockpal.data.ProductList;
import static org.junit.jupiter.api.Assertions.*;

public class NewCommandTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    // methodBeingTested_conditionToTest_expectedOutcome
    @Test
    void NewCommand_allFieldsFilled_expectCorrectAllocation() {
        final ProductList productList = new ProductList();
        NewCommand userInput = new NewCommand(productList, "chocolate", 100, 2.00, "ingredient");
        userInput.execute();

        assertEquals("chocolate",productList.products.get(0).getName().getName());
        assertEquals(100,productList.products.get(0).getQuantity().getQuantity());
        assertEquals(2.00,productList.products.get(0).getPrice().getPrice());
        assertEquals("ingredient",productList.products.get(0).getDescription().getDescription());
    }

    @Test
    void NewCommand_compulsoryFieldsFilled_expectCorrectAllocation() {
        final ProductList productList = new ProductList();
        NewCommand userInput = new NewCommand(productList, "chocolate", 100, null, null);
        userInput.execute();

        assertEquals("chocolate",productList.products.get(0).getName().getName());
        assertEquals(100,productList.products.get(0).getQuantity().getQuantity());
        assertNull(productList.products.get(0).getPrice().getPrice());
        assertNull(productList.products.get(0).getDescription().getDescription());
    }

    @Test
    void NewCommand_twoProductsWithCompulsoryFieldsFilled_expectCorrectAllocation() {
        final ProductList productList = new ProductList();
        NewCommand userInput1 = new NewCommand(productList, "chocolate", 100, 2.00, "ingredient");
        userInput1.execute();

        NewCommand userInput2 = new NewCommand(productList, "strawberry", 200, null, null);
        userInput2.execute();

        assertEquals("chocolate",productList.products.get(0).getName().getName());
        assertEquals("strawberry",productList.products.get(1).getName().getName());
    }


}
