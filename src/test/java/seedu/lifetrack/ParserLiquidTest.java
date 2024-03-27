//@@author rexyyong
package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.system.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.lifetrack.system.parser.ParserLiquid.parseLiquidInput;

public class ParserLiquidTest {

    @Test
    public void parseLiquidInput_inputContains2Beverages_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "liquids in Milo b/1000 b/1000";

        // Call methods to test
        try {
            parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have entered all keywords!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());

        }
    }

    @Test
    public void parseLiquidInput_inputContains2Volumes_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "liquids in Milo v/1000 v/1000";

        // Call methods to test
        try {
            parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have entered all keywords!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());

        }
    }

    @Test
    public void parseLiquidInput_inputMissingVolume_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "liquids in Milo date/221024";

        // Call methods to test
        try {
            parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have entered all keywords!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());

        }
    }

    @Test
    public void parseLiquidInput_inputWrongOrderDateBeforeVolume_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "liquids in Milo date/221024 v/1000";

        // Call methods to test
        try {
            parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have keyed the input in the correct order!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_inputNonIntegerValueForVolume_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "liquids in Milo v/##s100 date/221024";

        // Call methods to test
        try {
            parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that positive integer value is keyed in for volume!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_inputNegativeValueForVolume_invalidInputExceptionThrown() {
        // setup test
        String invalidInput = "liquids in Milo v/-1000 date/221024";

        // Call methods to test
        try {
            parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that positive integer value is keyed in for volume!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());
        }
    }

    //@@author shawnpong
    @Test
    public void parseLiquidInput_missingKeywords_exceptionThrown() {
        try {
            parseLiquidInput("liquids in");
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have entered all keywords!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_incompleteInput_exceptionThrown() {
        try {
            parseLiquidInput("liquids in b/Milo");
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have entered all keywords!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());

        }
    }

    @Test
    public void parseLiquidInput_emptyBeverageName_exceptionThrown() {
        try {
            parseLiquidInput("liquids in v/1000");
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have entered all keywords!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());

        }
    }
    @Test
    public void parseLiquidInput_emptyVolumeDescription_exceptionThrown() {
        try {
            parseLiquidInput("liquids in Milo v/   ");
        } catch (InvalidInputException e) {
            assertEquals("\t Invalid input!\n" +
                    "\t Please ensure that you have entered all keywords!\n" +
                    "\t Example input: liquids in Milo v/1000 date/221024", e.getMessage());

        }
    }
}
