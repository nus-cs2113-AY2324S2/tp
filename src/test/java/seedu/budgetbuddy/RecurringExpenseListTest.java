package seedu.budgetbuddy;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecurringExpenseListTest {

    @Test
    public void getName_requestForName_returnsValidName() {
        ArrayList<Expense> expenses = new ArrayList<>();
        RecurringExpenseList recurringExpenseList = new RecurringExpenseList("Bruno", expenses);

        assertEquals("Bruno", recurringExpenseList.getName());
    }
}
