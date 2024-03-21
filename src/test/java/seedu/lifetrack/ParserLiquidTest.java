package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import seedu.lifetrack.liquids.liquidlist.LiquidEntry;
import seedu.lifetrack.liquids.liquidlist.LiquidList;
import seedu.lifetrack.system.exceptions.InvalidInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.lifetrack.system.parser.ParserLiquid.parseLiquidInput;

public class ParserLiquidTest {

    @Test
    public void parseLiquidInput_inputContains2Beverages_invalidInputExceptionThrown() {
        // setup test
        LiquidList liquidList = new LiquidList();
        String invalidInput = "liquids in b/Milo b/1000";

        // Call methods to test
        try {
            LiquidEntry entry = parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("Invalid input exception: " +
                    "Please ensure that you have entered b/ and v/\n"  +
                    "For example: liquids in b/Milo v/1000", e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_inputContains2Volumes_invalidInputExceptionThrown() {
        // setup test
        LiquidList liquidList = new LiquidList();
        String invalidInput = "liquids in v/Milo v/1000";

        // Call methods to test
        try {
            LiquidEntry entry = parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("Invalid input exception: " +
                    "Please ensure that you have entered b/ and v/\n" +
                    "For example: liquids in b/Milo v/1000", e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_inputMissingBeverage_invalidInputExceptionThrown() {
        // setup test
        LiquidList liquidList = new LiquidList();
        String invalidInput = "liquids in v/1000";

        // Call methods to test
        try {
            LiquidEntry entry = parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("Invalid input exception: " +
                    "Please ensure that you have entered b/ and v/\n" +
                    "For example: liquids in b/Milo v/1000", e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_inputMissingVolume_invalidInputExceptionThrown() {
        // setup test
        LiquidList liquidList = new LiquidList();
        String invalidInput = "liquids in b/Milo";

        // Call methods to test
        try {
            LiquidEntry entry = parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("Invalid input exception: " +
                    "Please ensure that you have entered b/ and v/\n" +
                    "For example: liquids in b/Milo v/1000", e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_inputWrongOrderVolumeBeforeBeverage_invalidInputExceptionThrown() {
        // setup test
        LiquidList liquidList = new LiquidList();
        String invalidInput = "liquids in v/1000 b/milo";

        // Call methods to test
        try {
            LiquidEntry entry = parseLiquidInput(invalidInput);
        } catch (InvalidInputException e) {
            assertEquals("Invalid input exception: " +
                    "Please ensure that you have entered b/ before v/\n" +
                    "For example: liquids in b/Milo v/1000", e.getMessage());
        }
    }
}
