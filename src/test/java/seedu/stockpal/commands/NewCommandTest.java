package seedu.stockpal.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.exceptions.StockPalException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class NewCommandTest {
    public ProductList productList;

    @BeforeEach
    public void setUp() {
        productList = new ProductList();
    }

    @Test
    void newCommand_allFieldsFilled_expectCorrectAllocation() throws StockPalException {
        NewCommand userInput = new NewCommand("chocolate", 100,
                2.00, "ingredient");
        userInput.execute(productList);

        assertEquals("chocolate",productList.getProducts().get(0).getName().getName());
        assertEquals(100,productList.getProducts().get(0).getQuantity().getQuantity());
        assertEquals(2.00,productList.getProducts().get(0).getPrice().getPrice());
        assertEquals("ingredient",productList.getProducts().get(0).getDescription().getDescription());
        assertEquals(1, productList.getProducts().get(0).getPid().getPid());
    }

    @Test
    void newCommand_compulsoryFieldsFilled_expectCorrectAllocation() throws StockPalException {
        NewCommand userInput = new NewCommand("chocolate",
                100, null, null);
        userInput.execute(productList);

        assertEquals("chocolate",productList.getProducts().get(0).getName().getName());
        assertEquals(100,productList.getProducts().get(0).getQuantity().getQuantity());
        assertNull(productList.getProducts().get(0).getPrice().getPrice());
        assertNull(productList.getProducts().get(0).getDescription().getDescription());
        assertEquals(1, productList.getProducts().get(0).getPid().getPid());
    }

    @Test
    void newCommand_twoProductsWithCompulsoryFieldsFilled_expectCorrectAllocation() throws StockPalException {
        NewCommand userInput1 = new NewCommand("chocolate", 100,
                2.00, "ingredient");
        userInput1.execute(productList);

        NewCommand userInput2 = new NewCommand("strawberry", 200,
                null, null);
        userInput2.execute(productList);

        assertEquals("chocolate",productList.products.get(0).getName().getName());
        assertEquals(1, productList.products.get(0).getPid().getPid());

        assertEquals("strawberry",productList.products.get(1).getName().getName());
        assertEquals(2, productList.products.get(1).getPid().getPid());
    }

}
