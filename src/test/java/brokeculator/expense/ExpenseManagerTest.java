package brokeculator.expense;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseManagerTest {
    ExpenseManager expenseManager = new ExpenseManager();
    Expense expense1 = new Expense("pasta", 10.0, "2021-10-10", "food");
    Expense expense2 = new Expense("bus", 20.0, "2021-10-11", "transport");
    Expense expense3 = new Expense("movie", 30.0, "2021-10-12", "entertainment");

    @Test
    void testAdd() {
        expenseManager.add(expense1);
        expenseManager.add(expense2);
        expenseManager.add(expense3);

        assertEquals(3, expenseManager.listExpenses(-1).size());
        assertEquals(expense1, expenseManager.listExpenses(-1).get(0));
        assertEquals(expense2, expenseManager.listExpenses(-1).get(1));
        assertEquals(expense3, expenseManager.listExpenses(-1).get(2));
    }

    @Test
    void testDelete() {
        expenseManager.add(expense1);
        expenseManager.add(expense2);
        expenseManager.add(expense3);
        expenseManager.delete(1);

        assertEquals(2, expenseManager.listExpenses(-1).size());
        assertEquals(expense1, expenseManager.listExpenses(-1).get(0));
        assertEquals(expense3, expenseManager.listExpenses(-1).get(1));
    }

    @Test
    void testSummariseExpenses() {
        expenseManager.add(expense1);
        expenseManager.add(expense2);
        expenseManager.add(expense3);

        assertEquals(60.0, expenseManager.summariseExpenses(0, -1));
        assertEquals(30.0, expenseManager.summariseExpenses(2, 2));
        assertEquals(50.0, expenseManager.summariseExpenses(1, 5));
    }

    @Test
    void testListExpenses() {
        expenseManager.add(expense1);
        expenseManager.add(expense2);
        expenseManager.add(expense3);

        assertEquals(3, expenseManager.listExpenses(-1).size());
        assertEquals(expense1, expenseManager.listExpenses(-1).get(0));
        assertEquals(expense2, expenseManager.listExpenses(-1).get(1));
        assertEquals(expense3, expenseManager.listExpenses(-1).get(2));

        assertEquals(2, expenseManager.listExpenses(2).size());
        assertEquals(expense1, expenseManager.listExpenses(2).get(0));
        assertEquals(expense2, expenseManager.listExpenses(2).get(1));
    }

    @Test
    void testGetExpensesStringRepresentation() {
        expenseManager.add(expense1);
        expenseManager.add(expense2);
        expenseManager.add(expense3);

        assertEquals("--expense--pasta $10.00 (2021-10-10) [FOOD]" + System.lineSeparator()
                + "--expense--bus $20.00 (2021-10-11) [TRANSPORT]" + System.lineSeparator()
                + "--expense--movie $30.00 (2021-10-12) [ENTERTAINMENT]" + System.lineSeparator(),
                expenseManager.getExpensesStringRepresentation());
    }
}
