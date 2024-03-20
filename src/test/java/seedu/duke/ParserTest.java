package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.FlirtForkException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    // 3 part format
    //methodBeingTested_conditionToTest_ExpectedOutcome
    @Test
    void parseCommand_exitCommand_expectExitMessage() {
        try {
            Command result = Parser.parseCommand("exit");
            assertTrue(result instanceof ExitCommand, "Result should be an instance of ExitCommand");
        } catch (FlirtForkException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parseCommand_addFoodCommand_expectAddFoodCommand() {
        try {
            Command result = Parser.parseCommand("food sushi express");
            assertTrue(result instanceof AddFoodCommand, "Expected AddFoodCommand for 'food' input.");
        } catch (Exception e) {
            fail("Exception should not be thrown for valid 'food' input.");
        }
    }

    @Test
    void parseCommand_addActivityCommandInput_expectAddActivityCommand() {
        try {
            Command result = Parser.parseCommand("activity karaoke");
            assertTrue(result instanceof AddActivityCommand, "Expected AddActivityCommand for 'activity' input.");
        } catch (Exception e) {
            fail("Exception should not be thrown for valid 'activity' input.");
        }
    }

    @Test
    void parseCommand_listFavouritesCommandInput_expectListFavouritesCommand() {
        try {
            Command result = Parser.parseCommand("favourites");
            assertTrue(result instanceof ListFavouritesCommand,
                    "Expected ListFavouritesCommand for 'favourites' input.");
        } catch (Exception e) {
            fail("Exception should not be thrown for valid 'favourites' input.");
        }
    }

    @Test
    void parseCommand_deleteFavouritesCommandInputWithValidIndex_expectDeleteFavouritesCommand() {
        try {
            Command result = Parser.parseCommand("delete 1");
            assertTrue(result instanceof DeleteFavouritesCommand,
                    "Expected DeleteFavouritesCommand for 'delete' input with a valid index.");
        } catch (Exception e) {
            fail("Exception should not be thrown for valid 'delete' input with a valid index.");
        }
    }

    @Test
    void parseCommand_findFavouritesCommandInputWithKeyword_expectFindFavouritesCommand() {
        try {
            Command result = Parser.parseCommand("find sushi");
            assertTrue(result instanceof FindFavouritesCommand,
                    "Expected FindFavouritesCommand for 'find' input with a keyword.");
        } catch (Exception e) {
            fail("Exception should not be thrown for valid 'find' input with a keyword.");
        }
    }
}
