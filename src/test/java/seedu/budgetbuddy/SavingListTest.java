package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavingListTest {

    @Test
    public void calculateRemainingSavings_sufficientFunds_success() {
        SavingList savingList = new SavingList();
        int initialAmount = 1000;
        int totalExpenses = 200;
        int expectedRemaining = 800;

        int actualRemaining = savingList.calculateRemainingSavings(initialAmount, totalExpenses);

        assertEquals(expectedRemaining, actualRemaining);
    }

    @Test
    public void testCalculateRemainingSavings_insufficientFunds_exceptionThrown() {
        SavingList savingList = new SavingList();
        int initialAmount = 100;
        int totalExpenses = 200;

        assertThrows(RuntimeException.class, () -> {
            savingList.calculateRemainingSavings(initialAmount, totalExpenses);
        });
    }
}
