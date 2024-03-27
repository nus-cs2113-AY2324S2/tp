package brokeculator.expense;

import brokeculator.enumerators.Category;
import brokeculator.storage.parsing.FileKeyword;
import brokeculator.storage.parsing.SaveableType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseManagerTest {
    ExpenseManager expenseManager = new ExpenseManager();
    Expense expense1 = new Expense("pasta", 10.0, "2021-10-10", "food");
    Expense expense2 = new Expense("bus", 20.0, "2021-10-11", "transport");
    Expense expense3 = new Expense("movie", 30.0, "2021-10-12", "entertainment");

    @Test
    void testAdd() {
        Category.addCategory("food");
        Category.addCategory("transport");
        Category.addCategory("entertainment");
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
        Category.addCategory("food");
        Category.addCategory("transport");
        Category.addCategory("entertainment");
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
        Category.addCategory("food");
        Category.addCategory("transport");
        Category.addCategory("entertainment");
        expenseManager.add(expense1);
        expenseManager.add(expense2);
        expenseManager.add(expense3);

        assertEquals(60.0, expenseManager.summariseExpenses(0, -1));
        assertEquals(30.0, expenseManager.summariseExpenses(2, 2));
        assertEquals(50.0, expenseManager.summariseExpenses(1, 5));
    }

    @Test
    void testListExpenses() {
        Category.addCategory("food");
        Category.addCategory("transport");
        Category.addCategory("entertainment");
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
        Category.addCategory("food");
        Category.addCategory("transport");
        Category.addCategory("entertainment");
        expenseManager.add(expense1);
        expenseManager.add(expense2);
        expenseManager.add(expense3);

        String actualExpensesStringRepresentation = expenseManager.getExpensesStringRepresentation();

        String expense1StringRepresentation = FileKeyword.formatWithKeyword(SaveableType.EXPENSE,
                expense1.getStringRepresentation());
        String expense2StringRepresentation = FileKeyword.formatWithKeyword(SaveableType.EXPENSE,
                expense2.getStringRepresentation());
        String expense3StringRepresentation = FileKeyword.formatWithKeyword(SaveableType.EXPENSE,
                expense3.getStringRepresentation());
        String expectedExpensesStringRepresentation = expense1StringRepresentation + System.lineSeparator()
                + expense2StringRepresentation + System.lineSeparator()
                + expense3StringRepresentation + System.lineSeparator();

        assertEquals(actualExpensesStringRepresentation, expectedExpensesStringRepresentation);
    }
}
