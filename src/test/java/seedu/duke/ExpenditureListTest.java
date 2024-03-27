package seedu.duke;
import expenditure.ExpenditureList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

}
