package longah.node;

import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class MemberTest {
    /** 
     * Tests the successful creation of a member with a valid name.
     */
    @Test
    public void memberConstructor_validName_success() {
        try {
            Member member = new Member("Alice");
            assertEquals("Alice", member.getName());
            assertEquals(0.0, member.getBalance());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the successful creation of a member with a valid name and balance.
     */
    @Test
    public void memberConstructor_validNameAndBalance_success() {
        try {
            Member member = new Member("Alice", 10);
            assertEquals("Alice", member.getName());
            assertEquals(10.0, member.getBalance());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the unsuccessful creation of a member with an invalid name.
     */
    @Test
    public void memberConstructor_invalidName_exceptionThrown() {
        try {
            new Member("Alice123-");
            fail();
        } catch (Exception e) {
            boolean isMessage = LongAhException.isMessage((LongAhException) e, ExceptionMessage.INVALID_MEMBER_NAME);
            assertTrue(isMessage);
        }
    }

    /**
     * Tests the unsuccessful creation of a member with an empty name.
     */
    @Test
    public void memberConstructor_emptyName_exceptionThrown() {
        try {
            new Member("");
            fail();
        } catch (Exception e) {
            boolean isMessage = LongAhException.isMessage((LongAhException) e, ExceptionMessage.INVALID_MEMBER_NAME);
            assertTrue(isMessage);
        }
    }

    /**
     * Tests the constructor of the Member class with valid name and balance.
     */
    @Test
    public void addToBalance_validAdd_success() {
        try {
            Member member = new Member("Alice");
            member.addToBalance(10.0);
            assertEquals("Alice", member.getName());
            assertEquals(10.0, member.getBalance());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the addToBalance method of the Member class with invalid added balance.
     */
    @Test
    public void addToBalance_invalidAdd_exceptionThrown() {
        try {
            Member member = new Member("Bob");
            member.addToBalance(-20.0);
            fail();
        } catch (Exception e) {
            String expectedString = ExceptionMessage.INVALID_TRANSACTION_VALUE.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }

    /**
     * Tests the subtractFromBalance method of the Member class with valid subtracted balance.
     */
    @Test
    public void subtractFromBalance_validSubtract_success() {
        try {
            Member member = new Member("Alice");
            member.addToBalance(10.0);
            member.subtractFromBalance(5.0);
            assertEquals(5.0, member.getBalance());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the subtractFromBalance method of the Member class with invalid subtracted balance.
     */
    @Test
    public void subtractFromBalance_invalidSubtract_exceptionThrown() {
        try {
            Member member = new Member("Alice");
            member.subtractFromBalance(-20.0);
            fail();
        } catch (Exception e) {
            String expectedString = ExceptionMessage.INVALID_TRANSACTION_VALUE.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }

    /**
     * Tests the subtractFromBalance method of the Member class with boundary values.
     */
    @Test
    public void subtractFromBalance_boundaryValues_success() {
        try {
            Member member = new Member("Bob");
            member.addToBalance(Double.MAX_VALUE);
            member.subtractFromBalance(Double.MAX_VALUE);
            assertEquals(0.0, member.getBalance());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the subtractFromBalance method of the Member class with concurrent transactions.
     */
    @Test
    public void subtractFromBalance_concurrentTransactions_success() {
        try {
            Member member = new Member("Alice");
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    try {
                        member.addToBalance(1.0);
                    } catch (LongAhException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    try {
                        member.subtractFromBalance(1.0);
                    } catch (LongAhException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            assertEquals(0.0, member.getBalance());
        } catch (Exception e) {
            fail();
        }
    }
}
