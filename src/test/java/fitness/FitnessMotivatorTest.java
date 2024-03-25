package fitness;

import commands.fitnesscommands.AddExerciseCommand;
import exceptions.FitnessException;
import exceptions.Wellness360Exception;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static fitness.FitnessMotivator.FILE_PATH;
import static org.junit.jupiter.api.Assertions.*;
import static storage.Storage.isFileCreated;

public class FitnessMotivatorTest {

    private FitnessMotivator fitnessMotivator;
    private ExerciseList allExercises;

    @BeforeEach
    public void setUp() {
        File f = new File(FILE_PATH);
        if (isFileCreated(FILE_PATH)) {
            f.delete();
        }
        this.fitnessMotivator = new FitnessMotivator();
        this.allExercises = fitnessMotivator.allExercises;
    }

    @Test
    public void printExercises_getExercises_success() {
        String output = fitnessMotivator.getExercises();
        assertTrue(output.contains("Arms"));
        assertTrue(output.contains("Chest"));
        assertTrue(output.contains("Abs"));
        assertTrue(output.contains("Back"));
        assertTrue(output.contains("Legs"));
        assertTrue(output.contains("sets"));
        assertTrue(output.contains("reps"));
    }

    @Test
    public void addExerciseIntoList_addExercises_success() {
        // Test input for adding exercise
        String[] exerciseDetails = {
            "Arms", "testing", "3", "10"
        };

        // Generating results before adding and after adding exercises
        Exercise exercise = new Exercise(
            "testing", ExerciseType.ARMS, "3", "10");
        Exercise searchResultBeforeAdding =
            allExercises.findExercise(ExerciseType.ARMS, "testing");
        fitnessMotivator.addExercises(exerciseDetails);
        Exercise searchResultAfterAdding =
            allExercises.findExercise(ExerciseType.ARMS, "testing");

        // Assertions
        assertNull(searchResultBeforeAdding);
        assertEquals(exercise.getExerciseName(), searchResultAfterAdding.getExerciseName());
        assertEquals(exercise.getType(), searchResultAfterAdding.getType());
        assertEquals(exercise.getSets(), searchResultAfterAdding.getSets());
        assertEquals(exercise.getReps(), searchResultAfterAdding.getReps());
    }

    @Test
    public void incorrectParameters_addExercises_success() {
        // Checks if the validation of input for add Exercise works
        Wellness360Exception exceptionOne = assertThrows(FitnessException.class, () ->
                new AddExerciseCommand(fitnessMotivator, "testing"));
        Wellness360Exception exceptionTwo = assertThrows(FitnessException.class, () ->
                new AddExerciseCommand(fitnessMotivator, "arms, testing, a, b"));
        Wellness360Exception exceptionThree = assertThrows(FitnessException.class, () ->
                new AddExerciseCommand(fitnessMotivator, "testing, testing, 3, 10"));

        String expectedMessageOne =
            "ERROR MSG: Forgetting something? Key in the correct parameters please!";
        String expectedMessageTwo =
            "ERROR MSG: Did you enter your Sets and Reps correctly? :(";
        String expectedMessageThree = "ERROR MSG: " + "Hmm...Invalid type of exercise..." +
            System.lineSeparator() + "Only the following exercise types are allowed: " +
            "Arms, Chest, Abs, Back and Legs!";

        assertEquals(expectedMessageOne, exceptionOne.getMessage());
        assertEquals(expectedMessageTwo, exceptionTwo.getMessage());
        assertEquals(expectedMessageThree, exceptionThree.getMessage());

    }
}
