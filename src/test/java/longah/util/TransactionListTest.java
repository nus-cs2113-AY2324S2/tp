package longah.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TransactionListTest {
    /**
     * Tests the successful removal of a transaction from the list by index.
     */

    private final ByteArrayOutputStream printedOutput = new ByteArrayOutputStream();
    private final PrintStream sysOutput = System.out;

    public void restoreStreams() {
        System.setOut(sysOutput);
    }

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

    @Test
    public void list_noTransactions_success(){
        TransactionList transactionList = new TransactionList();
        transactionList.listTransactions();
        assertEquals("", printedOutput.toString());
    }

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
            transactionList.listTransactions();
            String expectedString = "1.Owner: Jack\n" +
                    "Payee 1: Jane Owed amount: 200.00\n" +
                    "Payee 2: James Owed amount: 100.00\n" +
                    "\n" +
                    "2.Owner: Jane\n" +
                    "Payee 1: Jack Owed amount: 150.00\n" +
                    "Payee 2: James Owed amount: 200.00\n";
            assertEquals(expectedString, printedOutput.toString());
        } catch (LongAhException e) {
            fail();
        }
    }

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
            transactionList.findPayments("James");
            String expectedString = "James owns the following list of transactions.";
            assertEquals(expectedString, printedOutput.toString());

        } catch (LongAhException e) {
            fail();
        }
    }

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
            transactionList.findPayments("Jack");
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
            assertEquals(expectedString, printedOutput.toString());

        } catch (LongAhException e) {
            fail();
        }
    }

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
            transactionList.findDebts("Jack");
            String expectedString = "Jack is involved as the payee in the following list of transactions.";
            assertEquals(expectedString, printedOutput.toString());

        } catch (LongAhException e) {
            fail();
        }
    }

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
            transactionList.findDebts("James");
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
            assertEquals(expectedString, printedOutput.toString());

        } catch (LongAhException e) {
            fail();
        }
    }
}
