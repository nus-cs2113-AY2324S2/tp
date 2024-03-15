package financialtransactions;

import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class OutflowTest {
    @Test
    public void testSetCategory() {
        Outflow outflow = new Outflow("2024 Sem 2 School Fees", 999999.99, "2024-02-01");
        outflow.setCategory(Outflow.Category.EDUCATION);
        assertEquals(Outflow.Category.EDUCATION, outflow.getCategory());
    }
}
