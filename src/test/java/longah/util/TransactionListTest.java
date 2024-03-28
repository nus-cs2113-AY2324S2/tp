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
            transactionList.remove(parts[1]);
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
            transactionList.remove(parts[1]);
            fail();
        } catch (LongAhException e) {
            boolean isMessage = LongAhException.isMessage(e, ExceptionMessage.INVALID_INDEX);
            assertTrue(isMessage);
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
            boolean isMessage = LongAhException.isMessage(e, ExceptionMessage.NO_TRANSACTION_FOUND);
            assertTrue(isMessage);
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
    public void findTransaction_noTransactions_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Jack");
            memberList.addMember("Jane");
            memberList.addMember("James");

            String command = "findtransaction James";
            String[] parts = command.split(" ", 2);
            transactionList.findTransactions(parts[1]);
            fail();

        } catch (LongAhException e) {
            boolean isMessage = LongAhException.isMessage(e, ExceptionMessage.TRANSACTIONS_SUMMED_UP);
            assertTrue(isMessage);
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
            String printedOutput = transactionList.findTransactions(parts[1]);

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
    public void findDebt_noTransactions_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Jack");
            memberList.addMember("Jane");
            memberList.addMember("James");

            transactionList.addTransaction("Jack p/Jane a/200 p/James a/100", memberList);
            transactionList.addTransaction("Jack p/Jane a/150 p/James a/200", memberList);
            String[] parts = "finddebt Jack".split(" ", 2);
            transactionList.findDebts(parts[1]);
            fail();

        } catch (LongAhException e) {
            boolean isMessage = LongAhException.isMessage(e, ExceptionMessage.TRANSACTIONS_SUMMED_UP);
            assertTrue(isMessage);
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
            String printedOutput = transactionList.findDebts(parts[1]);

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

    /**
     * Tests the editing of a transaction in the list with a valid index and expression.
     */
    @Test
    public void editTransactionList_validIndexAndExpression_success() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");

            transactionList.addTransaction("Alice p/Bob a/5", memberList);
            assertEquals(1, transactionList.getTransactionListSize());
            String command = "1 Alice p/Bob a/10";
            transactionList.editTransactionList(command, memberList);
            assertEquals(1, transactionList.getTransactionListSize());
            String expectedString = "1.\nLender: Alice\nBorrower 1: Bob Owed amount: 10.00\n";
            assertEquals(expectedString.trim(), transactionList.listTransactions().trim());
        } catch (LongAhException e) {
            fail();
        }
    }

    /**
     * Tests the editing of a transaction in the list with an invalid index.
     */
    @Test
    public void editTransactionList_invalidIndex_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");

            transactionList.addTransaction("Alice p/Bob a/5", memberList);
            assertEquals(1, transactionList.getTransactionListSize());
            String command = "-1 Alice p/Bob a/10";
            transactionList.editTransactionList(command, memberList);
            fail();
        } catch (LongAhException e) {
            boolean isMessage = LongAhException.isMessage(e, ExceptionMessage.INVALID_INDEX);
            assertTrue(isMessage);
        }
    }

    /**
     * Tests the editing of a transaction in the list with an invalid member.
     */
    @Test
    public void editTransactionList_invalidPerson_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");

            transactionList.addTransaction("Alice p/Bob a/5", memberList);
            assertEquals(1, transactionList.getTransactionListSize());
            String command = "1 Alice p/Charlie a/10";
            transactionList.editTransactionList(command, memberList);
            fail();
        } catch (LongAhException e) {
            boolean isMessage = LongAhException.isMessage(e, ExceptionMessage.MEMBER_NOT_FOUND);
            assertTrue(isMessage);
        }
    }

    /**
     * Test the successful deletion of a member from the transaction list.
     */
    @Test
    public void deleteMember_validMember_success() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            memberList.addMember("Charlie");
            
            transactionList.addTransaction("Alice p/Bob a/5 p/Charlie a/10", memberList);
            assertEquals(1, transactionList.getTransactionListSize());
            transactionList.deleteMember("Bob", memberList);
            assertEquals(1, transactionList.getTransactionListSize());
            String expected = "1.\nLender: Alice\nBorrower 1: Charlie Owed amount: 10.00\n";
            assertEquals(expected.trim(), transactionList.listTransactions().trim());
        } catch (LongAhException e) {
            fail();
        }
    }

    /**
     * Test the successful deletion of a member from the transaction list.
     * The delete command should invoke behvaiour to remove the transaction.
     */
    @Test
    public void deleteMember_transactionDeleted_exceptionThrown() {
        try {
            MemberList members = new MemberList();
            TransactionList transactions = new TransactionList();
            members.addMember("Alice");
            members.addMember("Bob");

            transactions.addTransaction("Alice p/Bob a/5", members);
            assertEquals(1, transactions.getTransactionListSize());
            transactions.deleteMember("Bob", members);
            assertEquals(0, transactions.getTransactionListSize());
            transactions.listTransactions();
            fail();
        } catch (LongAhException e) {
            String expected = ExceptionMessage.NO_TRANSACTION_FOUND.getMessage();
            assertEquals(expected, e.getMessage());
        }
    }

    /**
     * Test the successful deletion of a member from the transaction list.
     * The delete command should invoke behvaiour to remove the transaction.
     */
    @Test
    public void deleteMember_deleteLender_exceptionThrown() {
        try {
            MemberList members = new MemberList();
            TransactionList transactions = new TransactionList();
            members.addMember("Alice");
            members.addMember("Bob");

            transactions.addTransaction("Alice p/Bob a/5", members);
            assertEquals(1, transactions.getTransactionListSize());
            transactions.deleteMember("Alice", members);
            transactions.listTransactions();
            fail();
        } catch (LongAhException e) {
            String expected = ExceptionMessage.NO_TRANSACTION_FOUND.getMessage();
            assertEquals(expected, e.getMessage());
        }
    }

    /**
     * Test the unsuccessful deletion of a member from the transaction list.
     */
    @Test
    public void deleteMember_memberNotFound_exceptionThrown() {
        try {
            MemberList members = new MemberList();
            TransactionList transactions = new TransactionList();
            members.addMember("Alice");
            members.addMember("Bob");

            transactions.addTransaction("Alice p/Bob a/5", members);
            assertEquals(1, transactions.getTransactionListSize());
            transactions.deleteMember("Charlie", members);
            fail();
        } catch (LongAhException e) {
            String expected = ExceptionMessage.MEMBER_NOT_FOUND.getMessage();
            assertEquals(expected, e.getMessage());
        }
    }
}
