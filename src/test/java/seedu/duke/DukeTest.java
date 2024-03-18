package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class DukeTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }
    @Test
    public void testInvalidCommand() {
        // Arrange
        ByteArrayInputStream in = new ByteArrayInputStream("invalid\n".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String expectedOutput = "Timetable comparison app opened. \r\nInvalid command. \r\n";

        // Act
        Duke.main(new String[0]);

        // Assert
        assertEquals(expectedOutput, out.toString());
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
