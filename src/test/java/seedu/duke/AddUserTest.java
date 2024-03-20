package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddUserTest {

    @Test
    public void testUser() {
        try {
            User user = new User("John");
            assertEquals("John", user.getName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testAddUserToGroup() {
        try {
            Group group = new Group("TestGroup");
            User user = new User("John");
            group.addUsers(user);
            assertEquals("John", group.users.get(0).getName());
        } catch (Exception e) {
            fail();
        }
    }
    
}
