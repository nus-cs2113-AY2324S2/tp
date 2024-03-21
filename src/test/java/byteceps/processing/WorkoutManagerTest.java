package byteceps.processing;

import byteceps.commands.Parser;
import byteceps.errors.Exceptions;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkoutManagerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private Parser parser;
    private WorkoutManager workoutManager;
    private ExerciseManager exerciseManager;

    @BeforeEach
    void setUp() {
        parser = new Parser();
        exerciseManager = new ExerciseManager();
        workoutManager = new WorkoutManager(exerciseManager);
    }

    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void execute_createValidWorkout_success() {
        String validInput = "workout /create LegDay";
        parser.parseInput(validInput);
        assertDoesNotThrow(() -> workoutManager.execute(parser));
    }

    @Test
    public void execute_createEmptyNameWorkout_throwsInvalidInput() {
        String emptyInput = "workout /create";
        parser.parseInput(emptyInput);
        assertThrows(AssertionError.class, () -> workoutManager.execute(parser));
    }

    @Test
    public void execute_createDuplicateWorkout_throwsActivityExists() {
        String validInput = "workout /create LegDay";
        parser.parseInput(validInput);
        assertDoesNotThrow(() -> workoutManager.execute(parser));
        assertThrows(Exceptions.ActivityExistsException.class, () -> workoutManager.execute(parser));
    }

    @Test
    public void execute_deleteExistingWorkoutPlan_success() {
        String workoutInput = "workout /create chest day";
        parser.parseInput(workoutInput);
        assertDoesNotThrow(() -> workoutManager.execute(parser));

        String deleteInput = "workout /delete chest day";
        parser.parseInput(deleteInput);
        assertDoesNotThrow(() -> workoutManager.execute(parser));

        // Ensure the workout plan is deleted
        assertThrows(Exceptions.ActivityDoesNotExists.class, () -> workoutManager.retrieve("LegDay"));
    }

    @Test
    public void execute_deleteNonExistingWorkoutPlan_throwsActivityDoesNotExists() {
        String deleteInput = "workout /delete NonExistingWorkout";
        parser.parseInput(deleteInput);
        assertThrows(Exceptions.ActivityDoesNotExists.class, () -> workoutManager.execute(parser));
    }

    @Test
    public void execute_assignExerciseToWorkout_success() {
        String exerciseInput = "exercise /add Squat";
        parser.parseInput(exerciseInput);
        assertDoesNotThrow(() -> exerciseManager.execute(parser));

        String workoutInput = "workout /create LegDay";
        parser.parseInput(workoutInput);
        assertDoesNotThrow(() -> workoutManager.execute(parser));

        String assignInput = "workout /assign Squat /to LegDay";
        parser.parseInput(assignInput);
        assertDoesNotThrow(() -> workoutManager.execute(parser));
    }

    @Test
    public void execute_assignExerciseToNonexistentWorkout_throwsActivityDoesNotExist() {
        String exerciseInput = "exercise /add Squat";
        parser.parseInput(exerciseInput);
        assertDoesNotThrow(() -> exerciseManager.execute(parser));

        String validInput = "workout /assign Squat /to NonexistentWorkout";
        parser.parseInput(validInput);
        assertThrows(Exceptions.ActivityDoesNotExists.class, () -> workoutManager.execute(parser));
    }

    @Test
    public void execute_unassignExerciseFromWorkout_success() {
        String exerciseInput = "exercise /add Push-ups";
        parser.parseInput(exerciseInput);
        assertDoesNotThrow(() -> exerciseManager.execute(parser));

        String workoutInput = "workout /create ChestDay";
        parser.parseInput(workoutInput);
        assertDoesNotThrow(() -> workoutManager.execute(parser));

        String assignInput = "workout /assign Push-ups /to ChestDay";
        parser.parseInput(assignInput);
        assertDoesNotThrow(() -> workoutManager.execute(parser));

        String unassignInput = "workout /unassign Push-ups /from ChestDay";
        parser.parseInput(unassignInput);
        assertDoesNotThrow(() -> workoutManager.execute(parser));
    }

    @Test
    public void execute_unassignNonexistentExerciseFromWorkout_throwsActivityDoesNotExist() {
        String validInput = "workout /unassign NonexistentExercise /from LegDay";
        parser.parseInput(validInput);
        assertThrows(Exceptions.ActivityDoesNotExists.class, () -> workoutManager.execute(parser));
    }

    @Test
    public void execute_listWorkouts_success() {
        setUpStreams();

        String validInput1 = "workout /create LegDay";
        String validInput2 = "workout /create ArmDay";

        parser.parseInput(validInput1);
        assertDoesNotThrow(() -> workoutManager.execute(parser));
        parser.parseInput(validInput2);
        assertDoesNotThrow(() -> workoutManager.execute(parser));

        workoutManager.executeListAction();
        String expectedOutput = "[ByteCeps]> Added Workout Plan: LegDay\n" +
                "-------------------------------------------------\n" +
                "[ByteCeps]> Added Workout Plan: ArmDay\n" +
                "-------------------------------------------------\n" +
                "[ByteCeps]> Listing Workouts:\n" +
                "\t\t\t1. LegDay\n" +
                "\t\t\t2. ArmDay\n" +
                "\n" +
                "-------------------------------------------------\n";

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));

        restoreStreams();
    }

    @Test
    public void execute_info_success() {
        String exerciseInput1 = "exercise /add Squat";
        String exerciseInput2 = "exercise /add lunges";
        parser.parseInput(exerciseInput1);
        assertDoesNotThrow(() -> exerciseManager.execute(parser));
        parser.parseInput(exerciseInput2);
        assertDoesNotThrow(() -> exerciseManager.execute(parser));

        String workoutInput = "workout /create LegDay";
        parser.parseInput(workoutInput);
        assertDoesNotThrow(() -> workoutManager.execute(parser));

        String assignInput1 = "workout /assign Squat /to LegDay";
        String assignInput2 = "workout /assign lunges /to LegDay";
        parser.parseInput(assignInput1);
        assertDoesNotThrow(() -> workoutManager.execute(parser));
        parser.parseInput(assignInput2);
        assertDoesNotThrow(() -> workoutManager.execute(parser));

        String infoInput = "workout /info LegDay";
        parser.parseInput(infoInput);

        setUpStreams();
        assertDoesNotThrow(() -> workoutManager.execute(parser));
        String expectedOutput = "[ByteCeps]> Listing exercises in workout plan 'LegDay':\n" +
                "\t\t\t1. Squat\n" +
                "\t\t\t2. lunges\n" +
                "-------------------------------------------------\n";


        assertEquals(expectedOutput.replaceAll("\\s+", ""), outContent.toString().replaceAll("\\s+", ""));
        restoreStreams();
    }

    @Test
    public void execute_invalidWorkoutAction_throwsIllegalState() {
        String invalidInput = "workout /unknown";
        parser.parseInput(invalidInput);

        assertThrows(IllegalStateException.class, () -> workoutManager.execute(parser));
    }


}
