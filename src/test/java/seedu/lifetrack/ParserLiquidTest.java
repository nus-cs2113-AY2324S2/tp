package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.lifetrack.system.exceptions.InvalidInputException;
import static seedu.lifetrack.system.parser.ParserLiquid.parseLiquidInput;

public class ParserLiquidTest {

    @Test
    public void parseLiquidInput_missingKeywords_exceptionThrown() {
        try {
            parseLiquidInput("liquids in");
        } catch (InvalidInputException e) {
            assertEquals("\t Please ensure that you have keyed in the correct format!", e.getMessage());
        }
    }

    @Test
    public void parseLiquidInput_incompleteInput_exceptionThrown() {
        try {
            parseLiquidInput("liquids in b/Milo");
        } catch (InvalidInputException e) {
            assertEquals("\t Please ensure that you have keyed in the correct format!", e.getMessage());
        }
    }
}
