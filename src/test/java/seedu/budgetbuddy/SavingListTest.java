package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavingListTest {

    @Test
    public void calculateRemainingSavings_sufficientFunds_success() {
        SavingList savingList = new SavingList();
        double initialAmount = 1000;
        double totalExpenses = 200;
        double expectedRemaining = 800;

        double actualRemaining = savingList.calculateRemainingSavings(initialAmount, totalExpenses);

        assertEquals(expectedRemaining, actualRemaining);
    }

    @Test
    public void testCalculateRemainingSavings_insufficientFunds_exceptionThrown() {
        SavingList savingList = new SavingList();
        double initialAmount = 100;
        double totalExpenses = 200;

        assertThrows(RuntimeException.class, () -> {
            savingList.calculateRemainingSavings(initialAmount, totalExpenses);
        });
    }
}
