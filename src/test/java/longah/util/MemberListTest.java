package longah.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

public class MemberListTest {
    /**
     * Tests the successful addition of a member to the list.
     */
    @Test
    public void addMember_validName_success() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            assertEquals(1, memberList.getMemberListSize());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the unsuccessful addition of a member to the list when the name is repeated.
     */
    @Test
    public void addMember_duplicateName_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice");
            memberList.addMember("Alice");
            fail();
        } catch (LongAhException e) {
            boolean isMessage = LongAhException.isMessage(e, ExceptionMessage.DUPLICATE_MEMBER);
            assertTrue(isMessage);
        }
    }

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
            boolean isMessage = LongAhException.isMessage(e, ExceptionMessage.NO_MEMBERS_FOUND);
            assertTrue(isMessage);
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
    public void updateMembersBalance_validTransaction_success() {
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
    public void updateMembersBalance_noTransactions_success() {
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
    public void updateMembersBalance_multipleMembers_success() {
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

    /**
     * Tests the successful edit of name of a member in the group.
     */
    @Test
    public void editMemberName_validCommand_success() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice", 5);
            String expected = "Alice: $5.0\n";
            assertEquals(expected, memberList.listMembers());
            memberList.editMemberName("1 Bob");
            expected = "Bob: $5.0\n";
            assertEquals(expected, memberList.listMembers());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the unsuccessful edit of name of a member in the group when the index is invalid.
     */
    @Test
    public void editMemberName_invalidIndexValue_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice", 5);
            memberList.editMemberName("2 Bob");
            fail();
        } catch (LongAhException e) {
            boolean isMessage = LongAhException.isMessage(e, ExceptionMessage.INVALID_INDEX);
            assertTrue(isMessage);
        }
    }

    /**
     * Tests the unsuccessful edit of name of a member in the group when the index is invalid.
     */
    @Test
    public void editMemberName_invalidIndexSyntax_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice", 5);
            memberList.editMemberName("Bob");
            fail();
        } catch (LongAhException e) {
            boolean isMessage = LongAhException.isMessage(e, ExceptionMessage.INVALID_INDEX);
            assertTrue(isMessage);
        }
    }

    /**
     * Tests the successful deletion of a member in the group.
     * Balance should not be updated at this point as updating is performed after commands are invoked.
     */
    @Test
    public void deleteMember_validName_success() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice", 5);
            memberList.addMember("Bob", 10);
            memberList.deleteMember("Alice");
            String expected = "Bob: $10.0\n";
            assertEquals(expected, memberList.listMembers());
        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tests the unsuccessful deletion of a member in the group when the name is invalid.
     */
    @Test
    public void deleteMember_invalidName_exceptionThrown() {
        try {
            MemberList memberList = new MemberList();
            memberList.addMember("Alice", 5);
            memberList.addMember("Bob", 10);
            memberList.deleteMember("Charlie");
            fail();
        } catch (LongAhException e) {
            boolean isMessage = LongAhException.isMessage(e, ExceptionMessage.MEMBER_NOT_FOUND);
            assertTrue(isMessage);
        }
    }
}
