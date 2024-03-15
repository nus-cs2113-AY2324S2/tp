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

    /**
     * Tests the listing of transactions when there are none stored in the system
     */
    @Test
    public void list_noTransactions_success(){
        TransactionList transactionList = new TransactionList();
        String printedOutput = transactionList.listTransactions();
        assertEquals("", printedOutput);
    }

    /**
     * Tests the listing of transactions when there are multiple entries stored in the system
     */
    @Test
    public void list_multiTransactions_success() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Jack");
            memberList.addMember("Jane");
            memberList.addMember("James");

            transactionList.add("p/Jack p/Jane a/200 p/James a/100", memberList);
            transactionList.add("p/Jane p/Jack a/150 p/James a/200", memberList);
            String printedOutput = transactionList.listTransactions();
            String expectedString = "1.Owner: Jack\n" +
                    "Payee 1: Jane Owed amount: 200.00\n" +
                    "Payee 2: James Owed amount: 100.00\n" +
                    "\n" +
                    "2.Owner: Jane\n" +
                    "Payee 1: Jack Owed amount: 150.00\n" +
                    "Payee 2: James Owed amount: 200.00\n";
            assertEquals(expectedString, printedOutput);
        } catch (LongAhException e) {
            fail();
        }
    }

    /**
     * Tests the listing of payments when the input member does not own any
     */
    @Test
    public void findPayment_noTransactions_success() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Jack");
            memberList.addMember("Jane");
            memberList.addMember("James");

            transactionList.add("p/Jack p/Jane a/200 p/James a/100", memberList);
            transactionList.add("p/Jane p/Jack a/150 p/James a/200", memberList);
            String printedOutput = transactionList.findPayments("James");
            String expectedString = "James owns the following list of transactions.";
            assertEquals(expectedString, printedOutput);

        } catch (LongAhException e) {
            fail();
        }
    }

    /**
     * Tests the listing of payments when the input member owns multiple transactions
     */
    @Test
    public void findPayment_multiTransactions_success() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Jack");
            memberList.addMember("Jane");
            memberList.addMember("James");

            transactionList.add("p/Jack p/Jane a/200 p/James a/100", memberList);
            transactionList.add("p/Jack p/Jane a/150 p/James a/200", memberList);
            String printedOutput = transactionList.findPayments("Jack");
            String expectedString = "Jack owns the following list of transactions.\n" +
                    "1.\n" +
                    "Owner: Jack\n" +
                    "Payee 1: Jane Owed amount: 200.00\n" +
                    "Payee 2: James Owed amount: 100.00\n" +
                    "\n" +
                    "2.\n" +
                    "Owner: Jack\n" +
                    "Payee 1: Jane Owed amount: 150.00\n" +
                    "Payee 2: James Owed amount: 200.00\n";
            assertEquals(expectedString, printedOutput);

        } catch (LongAhException e) {
            fail();
        }
    }

    /**
     * Tests the listing of debts when the input member does not have any
     */
    @Test
    public void findDebt_noTransactions_success() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Jack");
            memberList.addMember("Jane");
            memberList.addMember("James");

            transactionList.add("p/Jack p/Jane a/200 p/James a/100", memberList);
            transactionList.add("p/Jack p/Jane a/150 p/James a/200", memberList);
            String printedOutput = transactionList.findDebts("Jack");
            String expectedString = "Jack is involved as the payee in the following list of transactions.";
            assertEquals(expectedString, printedOutput);

        } catch (LongAhException e) {
            fail();
        }
    }

    /**
     * Tests the listing of debts when the input member is involved in multiple transactions as payee
     */
    @Test
    public void findDebt_multiTransactions_success() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Jack");
            memberList.addMember("Jane");
            memberList.addMember("James");

            transactionList.add("p/Jack p/Jane a/200 p/James a/100", memberList);
            transactionList.add("p/Jack p/Jane a/150 p/James a/200", memberList);
            String printedOutput = transactionList.findDebts("James");
            String expectedString = "James is involved as the payee in the following list of transactions.\n" +
                    "1.\n" +
                    "Owner: Jack\n" +
                    "Payee 1: Jane Owed amount: 200.00\n" +
                    "Payee 2: James Owed amount: 100.00\n" +
                    "\n" +
                    "2.\n" +
                    "Owner: Jack\n" +
                    "Payee 1: Jane Owed amount: 150.00\n" +
                    "Payee 2: James Owed amount: 200.00\n";
            assertEquals(expectedString, printedOutput);

        } catch (LongAhException e) {
            fail();
        }
    }
}
