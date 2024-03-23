package longah.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import longah.exception.ExceptionMessage;
import longah.exception.LongAhException;

public class MemberListTest {
    /**
     * Tests checking of a valid name in the member list.
     */
    @Test
    public void isMember_validName_success() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            assertEquals(true, memberList.isMember("Alice"));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests checking of an invalid name in the member list.
     */
    @Test
    public void isMember_invalidName_failure() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            assertEquals(false, memberList.isMember("Bob"));
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the successful addition of a member to the list.
     */
    @Test
    public void listMembers_hasMembers_success() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            String expected = "Alice: $0.0\nBob: $0.0\n";
            assertEquals(expected,memberList.listMembers());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the unsuccessful listing of members when there are none stored in the system.
     */
    @Test
    public void listMembers_noMembers_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            memberList.listMembers();
            fail();
        } catch (LongAhException e) {
            String expectedString = ExceptionMessage.NO_MEMBERS_FOUND.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }

    /**
     * Tests the successful addition of a member to the list.
     */
    @Test
    public void getMemberListSize_validMembers_success() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            assertEquals(2, memberList.getMemberListSize());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the successful computation of multiple transactions.
     */
    @Test
    public void solveTransactions_validTransaction_success() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            TransactionList transactionList = new TransactionList();
            transactionList.addTransaction("Alice p/Bob a/5", memberList);
            transactionList.addTransaction("Bob p/Alice a/10", memberList);
            memberList.updateMembersBalance(transactionList);
            String expected = "Alice: -$5.0\nBob: $5.0\n";
            assertEquals(expected, memberList.listMembers());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the successful computation of no transactions.
     */
    @Test
    public void solveTransactions_noTransactions_success() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            TransactionList transactionList = new TransactionList();
            memberList.updateMembersBalance(transactionList);
            String expected = "Alice: $0.0\nBob: $0.0\n";
            assertEquals(expected, memberList.listMembers());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the successful computation of multiple members in the group.
     */
    @Test
    public void solveTransactions_multipleMembers_success() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Bob");
            memberList.addMember("Charlie");
            TransactionList transactionList = new TransactionList();
            transactionList.addTransaction("Alice p/Bob a/5", memberList);
            transactionList.addTransaction("Bob p/Charlie a/10", memberList);
            transactionList.addTransaction("Charlie p/Alice a/15", memberList);
            memberList.updateMembersBalance(transactionList);
            String expected = "Alice: -$10.0\nBob: $5.0\nCharlie: $5.0\n";
            assertEquals(expected, memberList.listMembers());
        } catch (Exception e) {
            fail();
        }
    }
}
