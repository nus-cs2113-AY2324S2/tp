//@@author rexyyong
package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.system.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.lifetrack.system.parser.ParserHydration.parseHydrationInput;

public class ParserHydrationTest {

    @Test
    public void parseHydrationInput_inputContains2Beverages_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "liquids in Milo b/1000 b/1000";

        // Call methods to test
        try {
            parseHydrationInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have entered all keywords!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());

        }
    }

    @Test
    public void parseHydrationInput_inputContains2Volumes_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "liquids in Milo v/1000 v/1000";

        // Call methods to test
        try {
            parseHydrationInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have entered all keywords!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());

        }
    }

    @Test
    public void parseHydrationInput_inputMissingVolume_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "liquids in Milo date/221024";

        // Call methods to test
        try {
            parseHydrationInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have entered all keywords!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());

        }
    }

    @Test
    public void parseHydrationInput_inputWrongOrderDateBeforeVolume_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "hydration add Milo d/221024 v/1000";

        // Call methods to test
        try {
            parseHydrationInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have keyed the input in the correct order!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());
        }
    }

    @Test
    public void parseHydrationInput_inputNonIntegerValueForVolume_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "hydration add Milo v/##s100 d/221024";

        // Call methods to test
        try {
            parseHydrationInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that positive integer value is keyed in for volume!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());
        }
    }

    @Test
    public void parseHydrationInput_inputNegativeValueForVolume_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "hydration add Milo v/-1000 d/221024";

        // Call methods to test
        try {
            parseHydrationInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that positive integer value is keyed in for volume!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());
        }
    }

    //@@author shawnpong
    @Test
    public void parseHydrationInput_missingKeywords_exceptionThrown() {
        try {
            parseHydrationInput("liquids in");
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have entered all keywords!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());
        }
    }

    @Test
    public void parseHydrationInput_incompleteInput_exceptionThrown() {
        try {
            parseHydrationInput("liquids in b/Milo");
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have entered all keywords!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());

        }
    }

    @Test
    public void parseHydrationInput_emptyBeverageName_exceptionThrown() {
        try {
            parseHydrationInput("liquids in v/1000");
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have entered all keywords!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());

        }
    }
    @Test
    public void parseHydrationInput_emptyVolumeDescription_exceptionThrown() {
        try {
            parseHydrationInput("liquids in Milo v/   ");
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have entered all keywords!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());

        }
    }
}
