package byteceps.processing;

import byteceps.commands.Parser;
import byteceps.errors.Exceptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WeeklyProgramManagerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private Parser parser;
    private ExerciseManager exerciseManager;
    private WorkoutManager workoutManager;
    private WeeklyProgramManager weeklyProgramManager;

    @BeforeEach
    void setUp() {
        parser = new Parser();
        exerciseManager = new ExerciseManager();
        workoutManager = new WorkoutManager(exerciseManager);
        TrackedWorkoutsManager trackedWorkoutsManager = new TrackedWorkoutsManager();
        weeklyProgramManager = new WeeklyProgramManager(exerciseManager, workoutManager, trackedWorkoutsManager);

        // create dummy exercises and workouts
        String[] exerciseInput = {"exercise /add benchpress", "exercise /add deadlift", "exercise /add barbell squat"};
        for (String input : exerciseInput) {
            parser.parseInput(input);
            assertDoesNotThrow(() -> exerciseManager.execute(parser));
        }

        String[] workoutInput = {"workout /create leg day", "workout /create full day"};
        for (String input : workoutInput) {
            parser.parseInput(input);
            assertDoesNotThrow(() -> workoutManager.execute(parser));
        }
    }

    @AfterEach
    void tearDown() {
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
    void execute_assignValidWorkout_success() {
        String assignWorkoutInput = "program /assign leg day /to thurs";
        parser.parseInput(assignWorkoutInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));
    }

    @Test
    void execute_assignDuplicateWorkout_throwsActivityExistsException() {
        String assignWorkoutInput = "program /assign leg day /to thurs";
        parser.parseInput(assignWorkoutInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));
        assertThrows(Exceptions.ActivityExistsException.class, () -> weeklyProgramManager.execute(parser));
    }

    @Test
    void execute_assignInvalidWorkout_throwsActivityDoesNotExist() {
        String assignWorkoutInput = "program /assign laze day /to thurs";
        parser.parseInput(assignWorkoutInput);
        assertThrows(Exceptions.ActivityDoesNotExists.class, () -> weeklyProgramManager.execute(parser));
    }

    @Test
    void execute_assignBlankWorkout_throwsActivityDoesNotExist() {
        String assignWorkoutInput = "program /assign/to thurs";
        parser.parseInput(assignWorkoutInput);
        assertThrows(Exceptions.ActivityDoesNotExists.class, () -> weeklyProgramManager.execute(parser));
    }

    @Test
    void execute_assignInvalidDate_throwsInvalidInput() {
        String assignWorkoutInput = "program /assign leg day /to wrong day";
        parser.parseInput(assignWorkoutInput);
        assertThrows(Exceptions.InvalidInput.class, () -> weeklyProgramManager.execute(parser));

        assignWorkoutInput = "program /assign leg day /to 2024-03-11";
        parser.parseInput(assignWorkoutInput);
        assertThrows(Exceptions.InvalidInput.class, () -> weeklyProgramManager.execute(parser));
    }

    @Test
    void execute_incompleteAssignCommand_throwsInvalidInput() {
        String assignWorkoutInput = "program /assign";
        parser.parseInput(assignWorkoutInput);
        assertThrows(Exceptions.InvalidInput.class, () -> weeklyProgramManager.execute(parser));
    }

    @Test
    void execute_clearWorkout_success() {
        setUpStreams();
        String assignWorkoutInput = "program /assign leg day /to thurs";
        parser.parseInput(assignWorkoutInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        outContent.reset();
        weeklyProgramManager.executeListAction();

        String expectedAssignedOutput = "[BYTE-CEPS]> Your workouts for the week:\n" +
                "\tMONDAY: Rest day\n" +
                "\n" +
                "\tTUESDAY: Rest day\n" +
                "\n" +
                "\tWEDNESDAY: Rest day\n" +
                "\n" +
                "\tTHURSDAY: leg day\n" +
                "\n" +
                "\tFRIDAY: Rest day\n" +
                "\n" +
                "\tSATURDAY: Rest day\n" +
                "\n" +
                "\tSUNDAY: Rest day\n" +
                "\n" +
                "\n" +
                "-------------------------------------------------";

        assertEquals(expectedAssignedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));

        String clearWorkoutInput = "program /clear thurs";
        parser.parseInput(clearWorkoutInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        outContent.reset();


        weeklyProgramManager.executeListAction();
        String expectedClearOutput = "[BYTE-CEPS]> Your workouts for the week:\n" +
                "\tMONDAY: Rest day\n" +
                "\n" +
                "\tTUESDAY: Rest day\n" +
                "\n" +
                "\tWEDNESDAY: Rest day\n" +
                "\n" +
                "\tTHURSDAY: Rest day\n" +
                "\n" +
                "\tFRIDAY: Rest day\n" +
                "\n" +
                "\tSATURDAY: Rest day\n" +
                "\n" +
                "\tSUNDAY: Rest day\n" +
                "\n" +
                "\n" +
                "-------------------------------------------------";
        assertEquals(expectedClearOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
        restoreStreams();
    }

    @Test
    void execute_clearInvalidDay_throwsInvalidInput() {
        String clearWorkoutInput = "program /clear noday";
        parser.parseInput(clearWorkoutInput);
        assertThrows(Exceptions.InvalidInput.class, () -> weeklyProgramManager.execute(parser));
    }

    @Test
    void execute_clearAll_success() {
        setUpStreams();
        String assignWorkoutInput = "program /assign leg day /to thurs";
        parser.parseInput(assignWorkoutInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        String assignWorkoutInput2 = "program /assign full day /to mon";
        parser.parseInput(assignWorkoutInput2);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        outContent.reset();
        weeklyProgramManager.executeListAction();

        String expectedAssignedOutput = "[BYTE-CEPS]> Your workouts for the week:\n" +
                "\tMONDAY: full day\n" +
                "\n" +
                "\tTUESDAY: Rest day\n" +
                "\n" +
                "\tWEDNESDAY: Rest day\n" +
                "\n" +
                "\tTHURSDAY: leg day\n" +
                "\n" +
                "\tFRIDAY: Rest day\n" +
                "\n" +
                "\tSATURDAY: Rest day\n" +
                "\n" +
                "\tSUNDAY: Rest day\n" +
                "\n" +
                "\n" +
                "-------------------------------------------------";

        assertEquals(expectedAssignedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));

        String clearWorkoutInput = "program /clear";
        parser.parseInput(clearWorkoutInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        outContent.reset();

        weeklyProgramManager.executeListAction();
        String expectedClearOutput = "[BYTE-CEPS]> Your workouts for the week:\n" +
                "\tMONDAY: Rest day\n" +
                "\n" +
                "\tTUESDAY: Rest day\n" +
                "\n" +
                "\tWEDNESDAY: Rest day\n" +
                "\n" +
                "\tTHURSDAY: Rest day\n" +
                "\n" +
                "\tFRIDAY: Rest day\n" +
                "\n" +
                "\tSATURDAY: Rest day\n" +
                "\n" +
                "\tSUNDAY: Rest day\n" +
                "\n" +
                "\n" +
                "-------------------------------------------------";
        assertEquals(expectedClearOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
        restoreStreams();
    }

    @Test
    void track_validTrack_success() {
        setUpStreams();
        String dateString = LocalDate.now().toString();
        String todayString = LocalDate.now().getDayOfWeek().toString();
        String assignWorkoutInput = String.format("program /assign full day /to %s", todayString);

        parser.parseInput(assignWorkoutInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        String trackInput = "program /log benchpress /weight 500 /sets 5 /reps 5";
        parser.parseInput(trackInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        String expectedOutput = String.format("[BYTE-CEPS]> Workout full day assigned to %s\n" +
                "-------------------------------------------------" +
                "[BYTE-CEPS]> Successfully tracked 500kg benchpress with 5 sets and 5 reps on %s\n" +
                "-------------------------------------------------\n", todayString, dateString);

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));

        outContent.reset();

        String todayInput = "program /today";
        parser.parseInput(todayInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        expectedOutput = String.format("[BYTE-CEPS]> Listing Exercises on %s:\n" +
                "1. benchpress (weight: 500, sets: 5, reps:5)\n" +
                "-------------------------------------------------", dateString);

        assertEquals(expectedOutput.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));
        restoreStreams();
    }

    @Test
    void track_incompleteTrack_throwsInvalidInput() {
        String todayString = LocalDate.now().getDayOfWeek().toString();
        String assignWorkoutInput = String.format("program /assign full day /to %s", todayString);

        parser.parseInput(assignWorkoutInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        String[] invalidInputs = {"program /log benchpress /weight 500 /sets 5",
            "program /log benchpress /weight 500 /reps 5",
            "program /log benchpress /sets 5 /reps 5",
            "program /log/weight 500 /sets 5 /reps 5",
            "program /log benchpress /weight /sets 5 /reps 5",
            "program /log benchpress /weight /sets 5 /reps 5",
            "program /log benchpress /weight 2 /sets /reps 5",
            "program /log benchpress /weight 2 /sets 5 /reps ",
            "program /log benchpress /weight /sets /reps ",
            "program /log benchpress /weight /sets /reps abc",
            "program /log benchpress /weight /sets test /reps 4",
            "program /log benchpress /weight abc /sets 3 /reps 4",
        };
        for (String input : invalidInputs) {
            parser.parseInput(input);
            assertThrows(Exceptions.InvalidInput.class, () -> weeklyProgramManager.execute(parser));
        }
    }

    @Test
    void track_invalidExerciseToTrack_throwsActivityDoesNotExist() {
        String todayString = LocalDate.now().getDayOfWeek().toString();
        String assignWorkoutInput = String.format("program /assign full day /to %s", todayString);

        parser.parseInput(assignWorkoutInput);
        String trackInput = "program /log snooze /weight 500 /sets 5 /reps 5";
        parser.parseInput(trackInput);
        assertThrows(Exceptions.ActivityDoesNotExists.class, () -> weeklyProgramManager.execute(parser));
    }

    @Test
    void track_history_success() {
        setUpStreams();
        String dateString = LocalDate.now().toString();
        String todayString = LocalDate.now().getDayOfWeek().toString();
        String assignWorkoutInput = String.format("program /assign full day /to %s", todayString);
        parser.parseInput(assignWorkoutInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        String trackInput = "program /log benchpress /weight 500 /sets 5 /reps 5";
        parser.parseInput(trackInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        outContent.reset();

        String historyInput = "program /history";
        parser.parseInput(historyInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        String expectedHistory = String.format("[BYTE-CEPS]> Listing Tracked Workouts: 1. %s\n" +
                "-------------------------------------------------", dateString);

        assertEquals(expectedHistory.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));

        assignWorkoutInput = "program /assign full day /to monday";
        parser.parseInput(assignWorkoutInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));


        String trackHistoryInput = "program /log benchpress /weight 500 /sets 5 /reps 5 /date 2024-03-25";
        parser.parseInput(trackHistoryInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        outContent.reset();
        parser.parseInput(historyInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        expectedHistory = String.format("[BYTE-CEPS]> Listing Tracked Workouts: 1. %s 2. 2024-03-25\n" +
                "-------------------------------------------------", dateString);

        assertEquals(expectedHistory.replaceAll("\\s+", ""),
                outContent.toString().replaceAll("\\s+", ""));


        restoreStreams();
    }

    @Test
    void track_historyInvalidDate_throwsInvalidInput() {
        String assignWorkoutInput = "program /assign full day /to monday";
        parser.parseInput(assignWorkoutInput);
        assertDoesNotThrow(() -> weeklyProgramManager.execute(parser));

        String trackHistoryInput = "program /log benchpress /weight 500 /sets 5 /reps 5 /date 2024-2323-23";
        parser.parseInput(trackHistoryInput);
        assertThrows(Exceptions.InvalidInput.class, () -> weeklyProgramManager.execute(parser));
    }
}
