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

            transactionList.add("Alice p/Bob a/5", memberList);
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

            transactionList.add("Alice p/Bob a/5", memberList);
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
    
    /*
    @Test
    public void list_multiTransactions_success() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Jack");
            memberList.addMember("Jane");
            memberList.addMember("James");

            transactionList.add("p/Jack p/Jane a/100 p/James a/200", memberList);
            transactionList.add("p/Jane p/Jack a/150 p/James a/200", memberList);
            String printedOutput = transactionList.listTransactions();
            String expectedString = "1.\n" +
                    "Owner: Jack\n" +
                    "Payee 1: James Owed amount: 200.00\n" +
                    "Payee 2: Jane Owed amount: 100.00\n" +
                    "\n" +
                    "2.\n" +
                    "Owner: Jane\n" +
                    "Payee 1: Jack Owed amount: 150.00\n" +
                    "Payee 2: James Owed amount: 200.00\n" +
                    "\n";
            assertEquals(expectedString, printedOutput);
        } catch (LongAhException e) {
            fail();
        }
    }
     */

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

            transactionList.add("Jack p/Jane a/200 p/James a/100", memberList);
            transactionList.add("Jane p/Jack a/150 p/James a/200", memberList);
            String command = "findpayment James";
            String[] parts = command.split(" ", 2);
            String printedOutput = transactionList.findTransactions(parts);
            String expectedString = "James owns the following list of transactions." + "\n";
            assertEquals(expectedString, printedOutput);

        } catch (LongAhException e) {
            fail();
        }
    }

    /*
    @Test
    public void findPayment_multiTransactions_success() {
        try {
            MemberList memberList = new MemberList();
            TransactionList transactionList = new TransactionList();
            memberList.addMember("Jack");
            memberList.addMember("Jane");
            memberList.addMember("James");

            transactionList.add("p/Jack p/James a/100 p/Jane a/200 ", memberList);
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
                    "Payee 2: James Owed amount: 200.00\n" +
                    "\n";

            assertEquals(expectedString, printedOutput);

        } catch (LongAhException e) {
            fail();
        }
    }
     */

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

            transactionList.add("Jack p/Jane a/200 p/James a/100", memberList);
            transactionList.add("Jack p/Jane a/150 p/James a/200", memberList);
            String[] parts = "finddebt Jack".split(" ", 2);
            String printedOutput = transactionList.findDebts(parts);
            String expectedString = "Jack is involved as the payee in the following list of transactions." + "\n";
            assertEquals(expectedString, printedOutput);

        } catch (LongAhException e) {
            fail();
        }
    }


    /*
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
                    "Payee 2: James Owed amount: 200.00\n"  +
                    "\n";

            assertEquals(expectedString, printedOutput);

        } catch (LongAhException e) {
            fail();
        }
    }

     */

}
