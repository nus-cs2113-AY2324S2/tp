package seedu.budgetbuddy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RecurringExpensesListTest {

    RecurringExpensesList recurringExpensesList = new RecurringExpensesList();

    @Test
    void addNewRecurringList_addValidNewList_success() {
        recurringExpensesList.addNewRecurringList("Entertainment");
        assertEquals(1, recurringExpensesList.getSize());
    }

    @Test
    void removeList_removeValidListNumber_success() {
        recurringExpensesList.addNewRecurringList("Entertainment");
        recurringExpensesList.addNewRecurringList("Housing");
        recurringExpensesList.addNewRecurringList("Utilities");
        recurringExpensesList.removeList(2);

        assertEquals(2, recurringExpensesList.getSize());
    }

    @Test
    void getSize_addThreeLists_sizeReturnedCorrect() {
        recurringExpensesList.addNewRecurringList("Entertainment");
        recurringExpensesList.addNewRecurringList("Housing");
        recurringExpensesList.addNewRecurringList("Utilities");

        int expectedSize = 3;

        int obtainedSize = recurringExpensesList.getSize();

        assertEquals(expectedSize, obtainedSize);
    }

    @Test
    void getExpenseListAtListNumber_validListNumber_returnsCorrectList() {
        recurringExpensesList.addNewRecurringList("Entertainment");
        recurringExpensesList.addNewRecurringList("Utilities");
        recurringExpensesList.addNewRecurringList("Housing");
        ExpenseList obtainedList = recurringExpensesList.getExpenseListAtListNumber(2);


        assertNotNull(obtainedList);
        assertEquals("Utilities", obtainedList.getName());
    }
}
