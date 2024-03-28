package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidDayException;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.exceptions.InvalidUserException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {
    UserList userlist = new UserList();

    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void adduserCommandTest() throws InvalidDayException, InvalidFormatException, InvalidUserException {
        Parser.parseCommand("addUser User1", userlist);

        assertEquals(1, userlist.getListLength());
        assertEquals("User1", userlist.getActiveUser().getName());

        Parser.parseCommand("addUser User2", userlist);
        Parser.parseCommand("addUser User3", userlist);
        Parser.parseCommand("addUser User4", userlist);

        assertEquals(4, userlist.getListLength());
    }

    @Test
    public void invalidAdduserCommandTest() {
        try {
            Parser.parseCommand("addUser", userlist);
            fail();
        } catch (Exception e) {
            assertEquals("[ERROR] Invalid addUser format. Expected format: adduser <desired user's name>",
                    e.getMessage());
        }
    }

    @Test
    public void switchCommandTest() throws InvalidDayException, InvalidFormatException, InvalidUserException {
        User user1 = new User("User1");
        User user2 = new User("User2");
        userlist.addUser(user1);
        userlist.addUser(user2);
        Parser.parseCommand("switch User2", userlist);

        assertEquals(2, userlist.getListLength());
        assertEquals("User2", userlist.getActiveUser().getName());
    }

    @Test
    public void invalidSwitchCommandTest() {
        User user1 = new User("User1");
        User user2 = new User("User2");
        userlist.addUser(user1);
        userlist.addUser(user2);

        try {
            Parser.parseCommand("switch", userlist);
            fail();
        } catch (InvalidFormatException e) {
            assertEquals("[ERROR] Invalid switch format. Expected format: switch <desired user's name>",
                    e.getMessage());
        } catch (Exception e) {
            fail();
        }

        try {
            Parser.parseCommand("switch noUser", userlist);
            fail();
        } catch (InvalidUserException e) {
            assertEquals("User does not exist!", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void addTaskCommandTest() throws InvalidDayException, InvalidUserException, InvalidFormatException {
        User user1 = new User("User1");
        userlist.addUser(user1);

        Parser.parseCommand("addtask /on Monday /task test1 /from 9:00 /to 11:00 /type f", userlist);
        Parser.parseCommand("addtask /on Monday /task test2 /from 13:00 /to 15:00 /type f", userlist);
        ArrayList<Task> testTasks = user1.getTimetable().getWeeklyTasks().get("Monday");

        assertEquals("test1", testTasks.get(0).getDescription());
        assertEquals("09:00", testTasks.get(0).getStartTime().toString());
        assertEquals("11:00", testTasks.get(0).getEndTime().toString());

        assertEquals("test2", testTasks.get(1).getDescription());
        assertEquals("13:00", testTasks.get(1).getStartTime().toString());
        assertEquals("15:00", testTasks.get(1).getEndTime().toString());
    }

    @Test
    public void invalidAddTaskCommandTest() {

        String expectedErrorMessage = "[ERROR] Invalid addTask format. Expected format: addTask /on [day]" +
                " /task [description] /from [start time] /to [end time] /type [f/c]";
        try {
            Parser.parseCommand("addtask", userlist);
            fail();
        } catch (InvalidFormatException | InvalidDayException e) {
            assertEquals(expectedErrorMessage, e.getMessage());
        } catch (Exception e) {
            assertNull(e.getMessage());
        }

    }
}
