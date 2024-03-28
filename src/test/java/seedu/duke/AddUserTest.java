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
            fail("Exception occurred while creating a User object: " +
                    e.getMessage());
        }
    }

    @Test
    public void testAddUserToGroup() {
        String groupName = "testGroup";
        Group testGroup = Group.createGroup(groupName);
        testGroup.addMember("testUser");
        assertTrue(testGroup.isMember("testUser"),
                "User was not added to the group");
    }
}


