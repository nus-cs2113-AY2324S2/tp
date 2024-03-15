package longah.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

public class TransactionListTest {
    /**
     * Tests the successful removal of a transaction from the list by index.
     */
    @Test
    public void remove_validIndex_success() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");

            transactionList.add("p/Alice p/Bob a/5", memberList);
            assertEquals(1, transactionList.getTransactionListSize());
            transactionList.remove(0);
            assertEquals(0, transactionList.getTransactionListSize());

        } catch (LongAhException e) {
            fail();
        }
    }

    /**
     * Tests the unsuccessful removal of a transaction from the list by an invalid index.
     */
    @Test
    public void remove_invalidIndex_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");

            transactionList.add("p/Alice p/Bob a/5", memberList);
            assertEquals(1, transactionList.getTransactionListSize());
            transactionList.remove(-1);
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.INVALID_INDEX.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }
}
