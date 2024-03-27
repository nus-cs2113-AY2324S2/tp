package seedu.duke;
import expenditure.Expenditure;
import expenditure.ExpenditureList;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ExpenditureListTest {

    @Test
    public void testValidDate() {
        new ExpenditureList();
        assertTrue(ExpenditureList.isValidDate("01.01.2022"));
        assertTrue(ExpenditureList.isValidDate("31.12.2022"));
        assertTrue(ExpenditureList.isValidDate("15.06.2023"));
    }

    @Test
    public void testInvalidDate() {
        new ExpenditureList();
        assertFalse(ExpenditureList.isValidDate("40.01.2022"));
        assertFalse(ExpenditureList.isValidDate("31.32.2022"));
        assertFalse(ExpenditureList.isValidDate("-1.06.2023"));
    }

    @Test
    public void testAddExpenditureValidInput() {
        int initialCount = ExpenditureList.expenditureCount;
        ExpenditureList.addExpenditure("e/ add/ d/ Lunch amt/10.12 date/02.02.2022", true);
        assertEquals(initialCount + 1, ExpenditureList.expenditureCount);
    }

    @Test
    public void testListExpensesByYearValidYear() {
        ExpenditureList.addExpenditure("e/ add/ d/ Lunch amt/10.12 date/02.02.2023", true);
        ExpenditureList.addExpenditure("e/ add/ d/ Lunch amt/10.12 date/02.04.2022", true);
        ExpenditureList.addExpenditure("e/ add/ d/ Lunch amt/10.12 date/02.03.2022", true);
        List<Expenditure> expensesByYear = ExpenditureList.listExpensesByYear("2022");
        assertEquals(2, expensesByYear.size());
    }

    @Test
    public void testListExpensesByYearValidMonth() {
        ExpenditureList.addExpenditure("e/ add/ d/ Lunch amt/10.12 date/02.02.2022", true);
        ExpenditureList.addExpenditure("e/ add/ d/ Lunch amt/10.12 date/02.04.2022", true);
        ExpenditureList.addExpenditure("e/ add/ d/ Lunch amt/10.12 date/02.03.2022", true);
        List<Expenditure> expensesByMonth = ExpenditureList.listExpensesByMonth("02.2022");
        assertEquals(1, expensesByMonth.size());
    }
}
