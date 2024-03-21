package workouts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.UiConstant;
import utility.CustomExceptions;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class WorkoutListTest {
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void cleanup() {
        WorkoutList.clearWorkoutsAndRun();
    }


    /**
     * Tests the behavior of adding a new run to the run list.
     * Verifies whether the newly added run is correctly reflected in the run and WorkoutList.
     */
    @Test
    void addRun_normalInput_expectAppend()  {
        try {
            Run inputRun = new Run("40:10", "10.3", "15/03/2024");
            WorkoutList.addRun(inputRun);
            ArrayList<? extends Workout> runList = WorkoutList.getWorkouts(UiConstant.RUN);
            ArrayList<? extends Workout> workoutList = WorkoutList.getWorkouts(UiConstant.ALL);

            Workout expectedRun = runList.get(runList.size() - 1);
            Workout expectedWorkout = workoutList.get(runList.size() - 1);

            assertEquals(inputRun, expectedRun);
            assertEquals(inputRun, expectedWorkout);

        } catch (CustomExceptions.OutOfBounds e) {
            fail("Should not throw an exception");
        } catch (CustomExceptions.InvalidInput e) {
            fail("Should not throw an exception.");
        }
    }


    /**
     * Tests the behavior of getting the workout list with {@code RUN} , {@code ALL}
     * Verifies whether the method is able to correct retrieve the list of workouts.
     *
     */
    @Test
    void getWorkouts_properInput_expectRetrievalRun() {
        try {

            ArrayList<Run> inputList = new ArrayList<>();
            inputList.add(new Run("40:10", "10.3", "15/03/2024"));
            inputList.add(new Run("30:10", "20.3", "30/03/2023"));


            ArrayList<? extends Workout> runList = WorkoutList.getWorkouts(UiConstant.RUN);
            for(int i = 0; i < inputList.size(); i++) {
                Run expected = inputList.get(i);
                Run actual = (Run) runList.get(i);
                assertEquals(expected, actual);
            }

        } catch (CustomExceptions.OutOfBounds | CustomExceptions.InvalidInput e) {
            fail("Should not throw an exception.");
        }
    }

    /**
     * Tests the behavior of getting the workout list with {@code RUN} , {@code ALL}
     * Verifies whether the method is able to correct retrieve the list of workouts.
     */
    @Test
    void getWorkouts_improperFilters_throwInvalidInput() throws CustomExceptions.InvalidInput {
        ArrayList<Workout> inputList = new ArrayList<>();
        inputList.add(new Run("40:10", "10.3", "15/03/2024"));
        inputList.add(new Run("30:10", "20.3", "30/03/2023"));

        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            ArrayList<? extends Workout> runList = WorkoutList.getWorkouts("invalidFilter");
        });
    }

    /**
     * Tests the behavior of getting an empty workout list
     * Expected behaviour is to raise {@code OutOfBounds} exception.
     */
    @Test
    void getWorkouts_emptyList_throwOutOfBoundsForRun() {
        assertThrows(CustomExceptions.OutOfBounds.class, () -> WorkoutList.getWorkouts(UiConstant.RUN));
    }

    /**
     * Tests the behavior of getting an empty run list
     * Expected behaviour is to raise {@code OutOfBounds} exception.
     */
    @Test
    void getWorkouts_emptyList_throwOutOfBoundsForAll() {
        assertThrows(CustomExceptions.OutOfBounds.class, () -> WorkoutList.getWorkouts(UiConstant.ALL));
    }

    /**
     * Tests the behavior of getting the latest run from the run list.
     * Expected behavior is for {@code actual} to equal to the {@code secondRun}
     */
    @Test
    void getLatestRun_properList_correctRetrieval() {
        try {
            new Run("20:10", "10.3", "15/03/2024");
            Run secondRun = new Run("20:10", "10.3", "15/03/2024");

            Run actual = WorkoutList.getLatestRun();
            assertEquals(secondRun, actual);
        } catch (CustomExceptions.OutOfBounds | CustomExceptions.InvalidInput e) {
            fail("Should not throw an exception");
        }

    }

    /**
     * Test the behaviour when you try to get the latest run from an empty list.
     * Expected behaviour is to raise {@code OutOfBounds} exception.
     */
    @Test
    void getLatestRun_emptyList_throwOutOfBound() {
        // Call the method or code that should throw the exception
        assertThrows(CustomExceptions.OutOfBounds.class, WorkoutList::getLatestRun);
    }
}
