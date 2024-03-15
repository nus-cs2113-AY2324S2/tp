package workouts;

import org.junit.jupiter.api.Test;
import utility.Constant;
import utility.CustomExceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RunTest {

    /**
     * Tests the behaviour of parsing a time string with hours into an integer array.
     */
    @Test
    void parseTime_correctInputWithHours_returnListOfTimes() throws CustomExceptions.InvalidInput {
        String testTime = "01:59:10";
        Run runTest = new Run(testTime, "15.3");
        Integer[] result = runTest.parseTime(testTime);
        Integer[] expected = {1, 59, 10};
        for (int i = 0; i < Constant.MAX_RUNTIME_ARRAY_LENGTH; i++) {
            assertEquals(result[i], expected[i]);
        }
        WorkoutList.clearWorkoutsAndRun();
    }

    /**
     * Tests the behaviour of parsing a time string without hours into an integer array.
     */
    @Test
    void parseTime_correctInputWithOutHours_returnListOfTimes() throws CustomExceptions.InvalidInput {
        String testTime = "50:52";
        Run runTest = new Run(testTime, "15.3");
        Integer[] result = runTest.parseTime("50:52");
        Integer[] expected = {50, 52};
        for (int i = 0; i < Constant.MIN_RUNTIME_ARRAY_LENGTH; i++) {
            assertEquals(result[i], expected[i]);
        }
        WorkoutList.clearWorkoutsAndRun();
    }

    /**
     * Test the behaviour of an incorrect time string being passed in for the run.
     */
    @Test
    void parseTime_wrongInput_throwInvalidInputForRun() {
        String testTime = "50";
        assertThrows(CustomExceptions.InvalidInput.class, () -> new Run(testTime, "15.3"));
    }

    /**
     * Tests the behaviour of calculating the total seconds taken for the run.
     */
    @Test
    void calculateSeconds_correctInput_returnTotalSeconds() throws CustomExceptions.InvalidInput {
        Run testRun = new Run("01:05:42", "10.3");
        int result = testRun.calculateTotalSeconds();
        int expected = 3942;
        assertEquals(result, expected);
        WorkoutList.clearWorkoutsAndRun();
    }

    /**
     * Tests the behaviour of calculating and returning a string representing the pace for the run.
     */
    @Test
    void calculatePace_correctInput_returnPace() throws CustomExceptions.InvalidInput {
        Run testRun = new Run("1:20:10", "10.3");
        String result = testRun.calculatePace();
        String expected ="7:47/km";
        assertEquals(result, expected);
        WorkoutList.clearWorkoutsAndRun();
    }
}
