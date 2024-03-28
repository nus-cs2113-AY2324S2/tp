package financialtransactions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InflowTest {
    @Test
    public void testSetCategory() {
        Inflow inflow = new Inflow("February salary", 20.00, "2024-03-02");
        inflow.setCategory(Inflow.Category.INCOME);
        assertEquals(Inflow.Category.INCOME, inflow.getCategory());
    }
}
