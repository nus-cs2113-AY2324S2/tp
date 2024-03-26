//@@author rexyyong
package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.system.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.lifetrack.system.exceptions.InvalidInputExceptionMessage.*;
import static seedu.lifetrack.system.parser.ParserCalories.parseCaloriesInput;
import static seedu.lifetrack.system.parser.ParserLiquid.parseLiquidInput;

public class ParserLiquidTest {

    @Test
    public void parseLiquidInput_inputContains2Beverages_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "liquids in b/Milo b/1000";

        // Call methods to test
        try {
            parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("Invalid input exception: " +
                    "Please ensure that you have entered b/ and v/\n" +
                    "For example: liquids in b/Milo v/1000", e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_inputContains2Volumes_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "liquids in v/Milo v/1000";

        // Call methods to test
        try {
            parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("Invalid input exception: " +
                    "Please ensure that you have entered b/ and v/\n" +
                    "For example: liquids in b/Milo v/1000", e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_inputMissingBeverage_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "liquids in v/1000";

        // Call methods to test
        try {
            parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("Invalid input exception: " +
                    "Please ensure that you have entered b/ and v/\n" +
                    "For example: liquids in b/Milo v/1000", e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_inputMissingVolume_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "liquids in b/Milo";

        // Call methods to test
        try {
            parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("Invalid input exception: " +
                    "Please ensure that you have entered b/ and v/\n" +
                    "For example: liquids in b/Milo v/1000", e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_inputWrongOrderVolumeBeforeBeverage_invalidInputExceptionThrown() {
        try {
            parseCaloriesInput("hydration add milo date/202232 v/123");
        } catch (InvalidInputException e) {
            assertEquals(getIncorrectOrderMessage(), e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_inputNonIntegerValueForVolume_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "liquids in b/Milo v/##s100";

        // Call methods to test
        try {
            parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("Invalid input Exception: " +
                    "Please enter a positive integer value for volume", e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_inputNegativeValueForVolume_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "liquids in b/Milo v/-1000";

        // Call methods to test
        try {
            parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("Invalid input Exception: " +
                    "Please enter a positive integer value for volume", e.getMessage());
        }
    }

    //@@author shawnpong
    @Test
    public void parseLiquidInput_missingKeywords_exceptionThrown() {
        try {
            parseCaloriesInput("hydration add milo");
        } catch (InvalidInputException e) {
            assertEquals(getHydrationMissingKeywordsMessage(), e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_incompleteInput_exceptionThrown() {
        try {
            parseLiquidInput("liquids in b/Milo");
        } catch (InvalidInputException e) {
            assertEquals("Invalid input exception: " +
                    "Please ensure that you have entered b/ and v/\n" +
                    "For example: liquids in b/Milo v/1000", e.getMessage());
        }
    }
}
