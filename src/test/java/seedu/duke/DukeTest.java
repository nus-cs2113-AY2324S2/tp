package seedu.duke;
import seedu.duke.UserList;
import

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

class DukeTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }
    @Test
    public void testInvalidCommand() {
        // Arrange
        UserList userList = new UserList();
        ByteArrayInputStream in = new ByteArrayInputStream("invalid\nbye\n".getBytes());
        System.setIn(in);
        String expectedOutput = "Welcome to Duke!\nInvalid command!\nGoodbye!\n";

        // Act
        Duke.main(new String[0]);

        // Assert
        assertEquals(expectedOutput, UI.printInvalidCommand());
    }
}
