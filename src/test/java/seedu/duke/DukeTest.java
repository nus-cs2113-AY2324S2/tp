package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;

class DukeTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testAddTask() {
        UserList userlist = new UserList();
        User user = new User("Test User");
        userlist.addUser(user);
        Task task = new Task("Lecture", "Monday", "1:00", "2:00");
        user.getTimetable().addUserTask("Monday", task);
        assertEquals("Lecture", task.description);
        assertEquals(LocalTime.parse("01:00"), task.startTime);
    }
    @Test
    public void testAddUser() {
        UserList userList = new UserList();
        User user = new User("Test User");
        userList.addUser(user);

        // Verify that the user is added to the list
        assertEquals(1, userList.getListLength());
        assertEquals(user, userList.getActiveUser());
        assertTrue(userList.getUsers().contains(user));
    }
}
