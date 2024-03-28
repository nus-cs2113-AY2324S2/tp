package seedu.budgetbuddy;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.budgetbuddy.exception.BudgetBuddyException;

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
    public void calculateRemainingSavings_insufficientFunds_success() {
        SavingList savingList = new SavingList();
        double initialAmount = 200;
        double totalExpenses = 1000;
        double expectedRemaining = -800;

        double actualRemaining = savingList.calculateRemainingSavings(initialAmount, totalExpenses);

        assertEquals(expectedRemaining, actualRemaining);
    }

    @Test @Disabled
    public void calculateRemainingSavings_insufficientFunds_exceptionThrown() {
        SavingList savingList = new SavingList();
        double initialAmount = 100;
        double totalExpenses = 200;

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            savingList.calculateRemainingSavings(initialAmount, totalExpenses);
        });
        assertEquals("java.lang.Exception: Insufficient Funds", exception.getMessage());
    }

    @Test
    public void findTotalSavings_calculateSavingsList_success() throws BudgetBuddyException {
        SavingList savingList = new SavingList();
        savingList.addSaving("Salary", "500"); // Adding initial savings to work with
        savingList.addSaving("Investments", "300");

        savingList.findTotalSavings();

        assertEquals(800, savingList.initialAmount);

    }

    @Test
    public void editSaving_validInput_success() throws BudgetBuddyException {
        // Create a SavingList and add some savings
        SavingList savingList = new SavingList();
        savingList.addSaving("Salary", String.valueOf(100));
        savingList.addSaving("Investments", String.valueOf(200));

        // Edit one of the savings
        savingList.editSaving("Salary", 1, 150);

        // Verify that the saving was edited successfully
        assertEquals(150, savingList.getSavings().get(0).getAmount(), 0.001);
    }

    @Test
    public void reduceSavings_validIndexAndAmount_success() throws BudgetBuddyException {

        SavingList savingList = new SavingList();
        savingList.addSaving("Salary", "500"); // Adding initial savings to work with
        savingList.addSaving("Investments", "300");

        int indexToReduce = 2;
        double amountToReduce = 100;
        double expectedAmountAfterReduction = 200;

        savingList.reduceSavings(indexToReduce - 1, amountToReduce);

        // Assert that the amount after reduction is as expected
        assertEquals(expectedAmountAfterReduction, savingList.getSavings().get(indexToReduce - 1).getAmount());
    }
}
