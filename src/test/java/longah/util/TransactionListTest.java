package longah.util;

import org.junit.jupiter.api.Test;

import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

            transactionList.addTransaction("Alice p/Bob a/5", memberList);
            assertEquals(1, transactionList.getTransactionListSize());
            String[] parts = "remove 1".split(" ", 2);
            transactionList.remove(parts);
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

            transactionList.addTransaction("Alice p/Bob a/5", memberList);
            assertEquals(1, transactionList.getTransactionListSize());
            String[] parts = "remove -1".split(" ", 2);
            transactionList.remove(parts);
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.INVALID_INDEX.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }

    /**
     * Tests the listing of transactions when there are none stored in the system
     */
    @Test
    public void list_noTransactions_success() throws LongAhException {
        try {
            TransactionList transactionList = new TransactionList();
            transactionList.listTransactions();
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.NO_TRANSACTION_FOUND.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }
    
    /**
     * Tests the listing of transactions when multiple entries are stored in the system
     */
    @Test
    public void list_multiTransactions_success() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Jack");
            memberList.addMember("Jane");
            memberList.addMember("James");

            transactionList.addTransaction("Jack p/Jane a/100 p/James a/200", memberList);
            transactionList.addTransaction("Jane p/Jack a/150 p/James a/250", memberList);
            String printedOutput = transactionList.listTransactions();

            assertTrue(printedOutput.contains("Lender: Jack"));
            assertTrue(printedOutput.contains("Jane Owed amount: 100.00"));
            assertTrue(printedOutput.contains("James Owed amount: 200.00"));
            assertTrue(printedOutput.contains("Lender: Jane"));
            assertTrue(printedOutput.contains("Jack Owed amount: 150.00"));
            assertTrue(printedOutput.contains("James Owed amount: 250.00"));

        } catch (LongAhException e) {
            fail();
        }
    }

    /**
     * Tests the listing of transactions when the input member does not own any
     */
    @Test
    public void findTransaction_noTransactions_success() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Jack");
            memberList.addMember("Jane");
            memberList.addMember("James");

            String command = "findtransaction James";
            String[] parts = command.split(" ", 2);
            String printedOutput = transactionList.findTransactions(parts);
            fail();

        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.NO_TRANSACTION_FOUND_FOR_MEMBER.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }

    /**
     * Tests the listing of payments when the input member owns multiple entries
     */
    @Test
    public void findTransaction_multiTransactions_success() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Jack");
            memberList.addMember("Jane");
            memberList.addMember("James");

            transactionList.addTransaction("Jack p/James a/100 p/Jane a/200", memberList);
            transactionList.addTransaction("Jack p/Jane a/150 p/James a/250", memberList);
            String command = "findtransaction Jack";
            String[] parts = command.split(" ", 2);
            String printedOutput = transactionList.findTransactions(parts);

            assertTrue(printedOutput.contains("Jack owns the following list of transactions."));
            assertTrue(printedOutput.contains("Lender: Jack"));
            assertTrue(printedOutput.contains("Jane Owed amount: 200.00"));
            assertTrue(printedOutput.contains("James Owed amount: 100.00"));
            assertTrue(printedOutput.contains("Lender: Jack"));
            assertTrue(printedOutput.contains("Jane Owed amount: 150.00"));
            assertTrue(printedOutput.contains("James Owed amount: 250.00"));

        } catch (LongAhException e) {
            fail();
        }
    }

    /**
     * Tests the listing of debts when the input member does not owe any
     */
    @Test
    public void findDebt_noTransactions_success() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Jack");
            memberList.addMember("Jane");
            memberList.addMember("James");

            transactionList.addTransaction("Jack p/Jane a/200 p/James a/100", memberList);
            transactionList.addTransaction("Jack p/Jane a/150 p/James a/200", memberList);
            String[] parts = "finddebt Jack".split(" ", 2);
            String printedOutput = transactionList.findDebts(parts);
            fail();

        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.NO_DEBTS_FOUND_FOR_MEMBER.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }


    /**
     * Tests the listing of debts when the input member owes multiple entries
     */
    @Test
    public void findDebt_multiTransactions_success() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Jack");
            memberList.addMember("Jane");
            memberList.addMember("James");

            transactionList.addTransaction("Jack p/Jane a/200 p/James a/100", memberList);
            transactionList.addTransaction("Jack p/Jane a/150 p/James a/200", memberList);
            String command = "finddebt James";
            String[] parts = command.split(" ", 2);
            String printedOutput = transactionList.findDebts(parts);

            assertTrue(printedOutput.contains("Lender: Jack"));
            assertTrue(printedOutput.contains("Jane Owed amount: 200.00"));
            assertTrue(printedOutput.contains("James Owed amount: 100.00"));
            assertTrue(printedOutput.contains("Lender: Jack"));
            assertTrue(printedOutput.contains("Jane Owed amount: 150.00"));
            assertTrue(printedOutput.contains("James Owed amount: 200.00"));

        } catch (LongAhException e) {
            fail();
        }
    }

}
