package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class BalanceTest {
    @Test
    public void testConstructor(){
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("member1"));
        users.add(new User("member2"));
        users.add(new User("member3"));

        ArrayList<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense("member1", "15", new String[]{"", "member2", "member3"}));
        expenses.add(new Expense("member2", "30", new String[]{"", "member1", "member3"}));
        expenses.add(new Expense("member3", "100", new String[]{"", "member1"}));

        Balance member1Balance = new Balance("member1", expenses, users);
        member1Balance.printBalance();
        Balance member2Balance = new Balance("member2", expenses, users);
        member2Balance.printBalance();
        Balance member3Balance = new Balance("member3", expenses, users);
        member3Balance.printBalance();

        assertEquals(-5.0f, member1Balance.getBalanceList().get("member2"));
        assertEquals(-45.0f, member1Balance.getBalanceList().get("member3"));

        assertEquals(5.0f, member2Balance.getBalanceList().get("member1"));
        assertEquals(10.0f, member2Balance.getBalanceList().get("member3"));

        assertEquals(45.0f, member3Balance.getBalanceList().get("member1"));
        assertEquals(-10.0f, member3Balance.getBalanceList().get("member2"));
    }

}
