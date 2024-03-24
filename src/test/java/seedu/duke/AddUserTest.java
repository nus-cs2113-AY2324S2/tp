package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class AddUserTest {

    @Test
    public void testUser() {
        try {
            User user = new User("John");
            assertEquals("John", user.getName());
        } catch (Exception e) {
            fail("Exception occurred while creating a User object: " + e.getMessage());
        }
    }

    @Test
    public void testAddUserToGroup() {
        try {
            Group group = Group.getOrCreateGroup("TestGroup");
            User user = group.addMember("John");

            assertTrue(group.getMembers().contains(user), "User was not added to the group");
            assertEquals("John", user.getName(), "User name is not the expected value");
        } catch (Exception e) {
            fail("Exception occurred while adding a user to the group: " + e.getMessage());
        }
    }
}

