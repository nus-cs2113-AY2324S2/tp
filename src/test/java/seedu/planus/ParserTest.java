package seedu.planus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parseCommand_emptyCommandDescription_exceptionThrown() {
        try {
            assertEquals(false, Parser.parseCommand("add", new Timetable()));
            fail();
        } catch (Exception e) {
            assertEquals("Command entered is invalid.\nPlease enter: \"help\" " +
                    "for available commands.", Ui.INVALID_COMMAND);
        }

        try {
            assertEquals(false, Parser.parseCommand("change", new Timetable()));
            fail();
        } catch (Exception e) {
            assertEquals("To change grade, please enter: change grade " +
                    "[course code] [letter grade]\n    e.g. change grade CS1010 A", Ui.INVALID_CHANGE_GRADE);
        }

        try {
            assertEquals(false, Parser.parseCommand("init", new Timetable()));
            fail();
        } catch (Exception e) {
            assertEquals("Please retry with a major code.\ne.g. init CEG", Ui.MISSING_MAJOR);
        }

        try {
            assertEquals(false, Parser.parseCommand("rm", new Timetable()));
            fail();
        } catch (Exception e) {
            assertEquals("Command entered is invalid.\nPlease enter: \"help\" " +
                    "for available commands.", Ui.INVALID_COMMAND);
        }

    }
}
