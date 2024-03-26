package ui;

import health.HealthList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utility.CustomExceptions;
import utility.ErrorConstant;
import utility.WorkoutConstant;
import workouts.WorkoutList;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


class HandlerTest {
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("".getBytes());
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        System.setErr(originalErr);
        WorkoutList.clearWorkoutsRunGym();
        HealthList.clearBmisAndPeriods();
        Handler.destroyScanner();
        if (Handler.in == null){
            return; // Scanner is already closed
        }
        assert isScannerClosed(Handler.in) : "Scanner is not closed";
    }

    /**
     * Checks whether the Scanner has been closed after each JUnit test to prevent overwriting of test input for each
     * test.
     *
     * @param in Scanner object from Handler.
     * @return True if the scanner is closed. Otherwise, return false.
     */
    public static boolean isScannerClosed(Scanner in) {
        try {
            in.hasNext();
            return false;
        } catch (IllegalStateException e) {
            return true;
        }
    }

    /**
     * Tests the processInput function's behaviour to the EXIT command.
     */
    @Test
    void processInput_exitCommand_terminatesProgram() {
        String input = "EXIT";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Handler.initialiseScanner();
        Handler.processInput();
        String output = outContent.toString();
        assertTrue(output.contains("Initiating PulsePilot landing sequence..."));
    }

    /**
     * Tests the processInput function's behaviour to the NEW command to add a Run
     * object.
     */
    @Test
    void processInput_workoutCommand_addRunExercise() {
        String input = "WORKOUT /e:run /d:10.3 /t:00:40:10 /date:15-03-2024";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Handler.initialiseScanner();
        Handler.processInput();
        String output = outContent.toString();
        System.out.println(output);
        assertTrue(output.contains("Successfully added a new run session"));
    }

    /**
     * Tests the processInput function's behaviour to the HEALTH command to add
     * a BMI object.
     */
    @Test
    void processInput_healthCommand_addBMIHealthData() {
        String input = "HEALTH /h:bmi /height:1.70 /weight:65 /date:15-03-2024";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Handler.initialiseScanner();
        Handler.processInput();

        String output = outContent.toString();
        assertTrue(output.contains("Added: bmi | 1.70 | 65 | 15-03-2024"));
    }


    /**
     * Tests the processInput function's behaviour to the HISTORY command to print
     * all run objects.
     */
    @Test
    void processInput_historyCommand_printsHistoryRun() {
        String inputRun = "WORKOUT /e:run /d:10.3 /t:00:40:10 /date:15-03-2024";
        System.setIn(new ByteArrayInputStream(inputRun.getBytes()));
        Handler.initialiseScanner();
        Handler.processInput();
        Handler.destroyScanner();

        String inputHistory = "HISTORY /item:run";
        System.setIn(new ByteArrayInputStream(inputHistory.getBytes()));
        Handler.initialiseScanner();
        Handler.processInput();
        String output = outContent.toString();
        assertTrue(output.contains("Your run history"));
    }

    /**
     * Tests the processInput function's behaviour to the LATEST command to print
     * the latest run object.
     */
    @Test
    void processInput_latestCommand_printsLatestRun() {
        String inputRun = "WORKOUT /e:run /d:10.3 /t:00:40:10 /date:15-03-2024";
        System.setIn(new ByteArrayInputStream(inputRun.getBytes()));
        Handler.initialiseScanner();
        Handler.processInput();
        Handler.destroyScanner();

        String inputLatest = "LATEST /item:run";
        System.setIn(new ByteArrayInputStream(inputLatest.getBytes()));
        Handler.initialiseScanner();
        Handler.processInput();

        String output = outContent.toString();
        assertTrue(output.contains("Your latest run:"));
    }

    /**
     * Tests the processInput function's behaviour to the HELP command to print
     * the help message.
     */
    @Test
    void processInput_helpCommand_printsHelp() {
        String input = "HELP";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Handler.initialiseScanner();
        Handler.processInput();

        String output = outContent.toString();
        assertTrue(output.contains("Commands List"));
    }



    /**
     * Tests the processInput function's behaviour to an invalid command, which prints
     * an error.
     */
    @Test
    void processInput_invalidCommand_printsInvalidCommandException() {
        String input = "INVALID";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Handler.initialiseScanner();
        Handler.processInput();

        String expected = "Exception Caught!" +
                System.lineSeparator() +
                ErrorConstant.INVALID_COMMAND_ERROR +
                System.lineSeparator();
        assertEquals(expected, errContent.toString());
        Handler.destroyScanner();
    }

    /**
     * Tests the processInput function's behaviour when the HEALTH command is given with invalid parameters.
     */
    @Test
    void processInput_healthCommand_insufficientParameters() {
        String input = "HEALTH /h:bmi /height:1.70";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Handler.initialiseScanner();
        Handler.processInput();

        assertTrue(errContent.toString().contains(ErrorConstant.INSUFFICIENT_BMI_PARAMETERS_ERROR));
        Handler.destroyScanner();
    }

    /**
     * Test the behavior of the checkTypeOfExercise method when the user input is valid.
     * Expected behavior is to return {@code Constant.RUN} or {@code Constant.GYM}
     * Does not expect EXCEPTION to be thrown.
     */
    @Test
    void checkTypeOfExercise_correctUserInput_expectRunOrGym() {
        try {
            String input1 = "workout /e:run /d:10.3 /t:00:40:10 /date:15-03-2024";
            String expected1 = WorkoutConstant.RUN;
            String result1 = Handler.checkTypeOfExercise(input1);
            assertEquals(result1, expected1);

            String input2 = "workout /e:gym /n:4";
            String expected2 = WorkoutConstant.GYM;
            String result2 = Handler.checkTypeOfExercise(input2);
            assertEquals(result2, expected2);

        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            e.printStackTrace();
            fail(ErrorConstant.UNSPECIFIED_ERROR);
        }
    }

    /**
     * Test the behavior of the checkTypeOfExercise method when the user input has invalid parameters.
     * Expected behavior is to raise {@code InvalidInput} exception.
     * Does not test for insufficient parameters.
     * Refer to {@code checkTypeOfExercise_insufficientUserInput_throwInsufficientInput()} for that.
     */
    @Test
    void checkTypeOfExercise_invalidUserInput_throwInvalidInput() {


        // with invalid exercise type
        String input2 = "workout /e:wrong /d:10.3 /t:00:40:10 /date:15-03-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Handler.checkTypeOfExercise(input2));

        // with invalid exercise type
        String input3 = "workout /e:gymm /d:10.3 /t:00:40:10 /date:15-03-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Handler.checkTypeOfExercise(input3));
    }

    /**
     * Test the behavior of the checkTypeOfExercise method when the user input has insufficient parameters.
     * Expected behavior is to raise {@code InsufficientInput} exception.
     * Does not test for invalid values.
     * Refer to {@code checkTypeOfExercise_invalidUserInput_throwInvalidInput()} for that.
     */
    @Test
    void checkTypeOfExercise_insufficientUserInput_throwInsufficientInput() {

        // with invalid exercise type
        String input1 = "workout /e";
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Handler.checkTypeOfExercise(input1));

        // without distance, time, and date
        String input2 = "workout /e:run";
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Handler.checkTypeOfExercise(input2));

        // without time and date
        String input3 = "workout /e:run /d:10.3";
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Handler.checkTypeOfExercise(input3));

        // with invalid format
        String input5 = "workout /e-gymm /d-10.3 /t:00:40:10 /date:15-03-2024";
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Handler.checkTypeOfExercise(input5));

        // with wrong slash
        String input6 = "workout \\e:run \\d:30:10 \\t:00:20:10 \\date:15-03-2024";
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Handler.checkTypeOfExercise(input6));

    }
}
