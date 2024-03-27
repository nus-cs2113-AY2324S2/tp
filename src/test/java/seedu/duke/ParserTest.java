package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.*;
import seedu.duke.exceptions.FlirtForkException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;

class ParserTest {

    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        // Create a dummy UserDetails object for testing
        userDetails = new UserDetails("John Doe", "25", "Male", "Single", "Central",
                "Italian", "14/2/2024");
    }

    // 3 part format
    //methodBeingTested_conditionToTest_ExpectedOutcome
    @Test
    void parseCommand_exitCommand_expectExitMessage() {
        try {
            Command result = Parser.parseCommand("exit", userDetails);
            assertTrue(result instanceof ExitCommand, "Result should be an instance of ExitCommand");
        } catch (FlirtForkException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseCommand_addFoodCommand_expectAddFoodCommand() {
        try {
            Command result = Parser.parseCommand("food sushi express", userDetails);
            assertTrue(result instanceof AddFoodCommand, "Expected AddFoodCommand for 'food' input.");
        } catch (Exception e) {
            fail("Exception should not be thrown for valid 'food' input.");
        }
    }

    @Test
    void parseCommand_addActivityCommandInput_expectAddActivityCommand() {
        try {
            Command result = Parser.parseCommand("activity karaoke", userDetails);
            assertTrue(result instanceof AddActivityCommand, "Expected AddActivityCommand for 'activity' input.");
        } catch (Exception e) {
            fail("Exception should not be thrown for valid 'activity' input.");
        }
    }

    @Test
    void parseCommand_userDetailsCommandInput_expectUserDetailsCommand() {
        try {
            Command result = Parser.parseCommand("me", userDetails);
            assertTrue(result instanceof UserDetailsCommand, "Expected UserDetailsCommand for 'me' input.");
        } catch (Exception e) {
            fail("Exception should not be thrown for valid 'me' input.");
        }
    }

    @Test
    void parseCommand_listFavouritesCommandInput_expectListFavouritesCommand() {
        try {
            Command result = Parser.parseCommand("favourites", userDetails);
            assertTrue(result instanceof ListFavouritesCommand,
                    "Expected ListFavouritesCommand for 'favourites' input.");
        } catch (Exception e) {
            fail("Exception should not be thrown for valid 'favourites' input.");
        }
    }

    @Test
    void parseCommand_deleteFavouritesCommandInputWithValidIndex_expectDeleteFavouritesCommand() {
        try {
            Command result = Parser.parseCommand("delete 1", userDetails);
            assertTrue(result instanceof DeleteFavouritesCommand,
                    "Expected DeleteFavouritesCommand for 'delete' input with a valid index.");
        } catch (Exception e) {
            fail("Exception should not be thrown for valid 'delete' input with a valid index.");
        }
    }

    @Test
    void parseCommand_findFavouritesCommandInputWithKeyword_expectFindFavouritesCommand() {
        try {
            Command result = Parser.parseCommand("find sushi", userDetails);
            assertTrue(result instanceof FindFavouritesCommand,
                    "Expected FindFavouritesCommand for 'find' input with a keyword.");
        } catch (Exception e) {
            fail("Exception should not be thrown for valid 'find' input with a keyword.");
        }
    }
}
