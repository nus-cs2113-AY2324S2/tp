package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ExpenseTest{
    @Test
    public void newExpenseTest(){
        Expense testExpense = new Expense("Mukund",10, new String[]{"Mukund", " JX", "hehe"});
        assertEquals((float) 10, testExpense.getTotalAmount());
    }
}
