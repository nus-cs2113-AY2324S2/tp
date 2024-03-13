package longah.node;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import longah.exception.ExceptionMessage;

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
     * Tests the unsuccessful creation of a member with an invalid name.
     */
    @Test
    public void memberConstructor_invalidName_exceptionThrown() {
        try {
            new Member("Alice123-");
            fail();
        } catch (Exception e) {
            String expectedString = ExceptionMessage.INVALID_MEMBER_NAME.getMessage();
            assertEquals(expectedString, e.getMessage());
        }
    }
}
