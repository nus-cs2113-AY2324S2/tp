package seedu.lifetrack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.lifetrack.system.exceptions.InvalidInputException;
import static seedu.lifetrack.system.parser.ParserCalories.parseCaloriesInput;

class ParserCaloriesTest {

    @Test
    public void parseCaloriesInput_missingKeywords_exceptionThrown() {
        try {
            parseCaloriesInput("calories in Running");
        } catch (InvalidInputException e) {
            assertEquals("Invalid input exception:" + " Please ensure that you have keyed in the correct format " +
                    "in the correct order!" + " Example input: " +
                    "calories in DESCRIPTION c/INTEGER_CALORIES date/DATE m/MACROS", e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_incompleteInput_exceptionThrown() {
        try {
            parseCaloriesInput("calories in Running date/220224");
        } catch (InvalidInputException e) {
            assertEquals("Invalid input exception:" + " Please ensure that you have keyed in the correct format " +
                    "in the correct order!" + " Example input: " +
                    "calories in DESCRIPTION c/INTEGER_CALORIES date/DATE m/MACROS", e.getMessage());
        }
    }

    @Test
    public void parseCaloriesInput_incorrectlyOrderedInput_exceptionThrown() {
        try {
            parseCaloriesInput("calories in Running date/220224 c/123");
        } catch (InvalidInputException e) {
            assertEquals("Invalid input exception:" + " Please ensure that you have keyed in the correct format " +
                    "in the correct order!" + " Example input: " +
                    "calories in DESCRIPTION c/INTEGER_CALORIES date/DATE m/MACROS", e.getMessage());
        }
    }
}
