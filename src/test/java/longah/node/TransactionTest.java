package longah.node;

import longah.util.MemberList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

public class TransactionTest {
    /**
     * Tests the successful creation of a transaction with balances correctly updated.
     */
    @Test
    public void transactionConstructor_transaction_success() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            Transaction transaction = new Transaction("Alice p/Bob a/5", memberList);
            Member lender = transaction.getLender();
            assertEquals("Alice", lender.getName());
            assertEquals(5.0, lender.getBalance());
            Member borrower = memberList.getMember("Bob");
            assertEquals(-5.0, borrower.getBalance());
        } catch (LongAhException e) {
            fail();
        }
    }

    /**
     * Tests the unsuccessful creation of a transaction with < 2 persons involved.
     */
    @Test
    public void transactionConstructor_invalidTransaction_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            new Transaction("Alice a/5", memberList);
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.INVALID_TRANSACTION_FORMAT.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }

    /**
     * Tests the unsuccessful creation of a transaction with an invalid command to denote amount.
     */
    @Test
    public void addPayee_invalidAmountCommand_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            Transaction transaction = new Transaction("Alice p/Bob a/5", memberList);
            transaction.addBorrower("Bob b/5", memberList);
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.INVALID_TRANSACTION_FORMAT.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }

    /**
     * Tests the unsuccessful creation of a transaction with an amount value specified in words
     * rather than a double value.
     */
    @Test
    public void addPayee_invalidAmountValue_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            Transaction transaction = new Transaction("Alice p/Bob a/5", memberList);
            transaction.addBorrower("Bob a/five", memberList);
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.INVALID_VALUE_FORMAT.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }

    /**
     * Tests the unsuccessful creation of a transaction with a negative amount
     * value for person that owes.
     */
    @Test
    public void addPayee_negativeAmountValue_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            Transaction transaction = new Transaction("Alice p/Bob a/5", memberList);
            transaction.addBorrower("Bob a/-5", memberList);
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.INVALID_TRANSACTION_VALUE.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }
}
