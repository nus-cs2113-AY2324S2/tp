package workouts;

import org.junit.jupiter.api.Test;
import utility.Constant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RunTest {

    /**
     * Tests the behaviour of parsing a time string with hours into an integer array.
     */
    @Test
    void parseTime_correctInputWithHours_returnListOfTimes() {
        Integer[] result = Run.parseTime("01:50:52");
        Integer[] expected = {1, 50, 52};
        for (int i = 0; i < Constant.MAX_RUNTIME_ARRAY_LENGTH; i++) {
            assertEquals(result[i], expected[i]);
        }
    }

    /**
     * Tests the behaviour of parsing a time string without hours into an integer array.
     */
    @Test
    void parseTime_correctInputWithOutHours_returnListOfTimes() {
        Integer[] result = Run.parseTime("50:52");
        Integer[] expected = {50, 52};
        for (int i = 0; i < Constant.MIN_RUNTIME_ARRAY_LENGTH; i++) {
            assertEquals(result[i], expected[i]);
        }
    }

    /**
     * Tests the behaviour of calculating the total seconds taken for the run.
     */
    @Test
    void calculateSeconds_correctInput_returnTotalSeconds() {
        new Run("01:05:42", "10.3");
        int result = Run.calculateTotalSeconds();
        int expected = 3942;
        assertEquals(result, expected);
    }

    /**
     * Tests the behaviour of calculating and returning a string representing the pace for the run.
     */
    @Test
    void calculatePace_correctInput_returnPace() {
        new Run("1:20:10", "10.3");
        String result = Run.calculatePace();
        String expected ="7:47/km";
        assertEquals(result, expected);

    }
}