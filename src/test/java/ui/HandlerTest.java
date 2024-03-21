package ui;

import health.HealthList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.UiConstant;

import utility.CustomExceptions;
import workouts.WorkoutList;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

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
        WorkoutList.clearWorkoutsAndRun();
        HealthList.clearBmisAndPeriods();
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
        Handler.destroyScanner();
    }

    /**
     * Tests the processInput function's behaviour to the NEW command to add a Run
     * object.
     */
    @Test
    void processInput_newCommand_addRunExercise() {
        String input = "NEW /e:run /d:10.3 /t:00:40:10 /date:15-03-2024";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Handler.initialiseScanner();
        Handler.processInput();

        String output = outContent.toString();
        assertTrue(output.contains("Successfully added a new run session"));
        Handler.destroyScanner();
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
        Handler.destroyScanner();
    }


    /**
     * Tests the processInput function's behaviour to the HISTORY command to print
     * all run objects.
     */
    @Test
    void processInput_historyCommand_printsHistoryRun() {
        String inputRun = "NEW /e:run /d:10.3 /t:00:40:10 /date:15-03-2024";
        System.setIn(new ByteArrayInputStream(inputRun.getBytes()));
        String inputHistory = "HISTORY /view:run";
        System.setIn(new ByteArrayInputStream(inputHistory.getBytes()));
        Handler.initialiseScanner();
        Handler.processInput();

        String output = outContent.toString();
        assertTrue(output.contains("history:"));
        Handler.destroyScanner();
    }

    /**
     * Tests the processInput function's behaviour to the LATEST command to print
     * the latest run object.
     */
    @Test
    void processInput_latestCommand_printsLatestRun() {
        String inputRun = "NEW /e:run /d:10.3 /t:00:40:10 /date:15-03-2024";
        System.setIn(new ByteArrayInputStream(inputRun.getBytes()));
        String inputLatest = "LATEST /view:run";
        System.setIn(new ByteArrayInputStream(inputLatest.getBytes()));
        Handler.initialiseScanner();
        Handler.processInput();

        String output = outContent.toString();
        assertTrue(output.contains("Your latest run:"));
        Handler.destroyScanner();
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
        Handler.destroyScanner();
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
                "Invalid command. Enter 'help' to view available commands." +
                System.lineSeparator() +
                System.lineSeparator() +
                "No enum constant utility.Command.INVALID" +
                System.lineSeparator();

        assertEquals(expected, errContent.toString());
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
            String input1 = "new /e:run /d:10.3 /t:00:40:10 /date:15-03-2024";
            String expected1 = UiConstant.RUN;
            String result1 = Handler.checkTypeOfExercise(input1);
            assertEquals(result1, expected1);

            String input2 = "new /e:gym /n:4";
            String expected2 = UiConstant.GYM;
            String result2 = Handler.checkTypeOfExercise(input2);
            assertEquals(result2, expected2);

            // with capital letter
            String input3 = "NEW /E:run /D:10.3 /T:00:40:10 /Date:15-03-2024";
            String expected3 = UiConstant.RUN;
            String result3 = Handler.checkTypeOfExercise(input3);
            assertEquals(result3, expected3);

            String input4 = "NEW /E:gym /N:4";
            String expected4 = UiConstant.GYM;
            String result4 = Handler.checkTypeOfExercise(input4);
            assertEquals(result4, expected4);

            // exercises in capital letter
            String input5 = "NEW /E:RUN /D:10.3 /T:00:40:10 /Date:15-03-2024";
            String expected5 = UiConstant.RUN;
            String result5 = Handler.checkTypeOfExercise(input5);
            assertEquals(result5, expected5);

            String input6 = "NEW /E:GYM /N:4";
            String expected6 = UiConstant.GYM;
            String result6 = Handler.checkTypeOfExercise(input6);
            assertEquals(result6, expected6);

        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            fail(e.getMessage());
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
        String input1 = "new /e";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Handler.checkTypeOfExercise(input1);
        });

        // with invalid exercise type
        String input2 = "new /e:wrong /d:10.3 /t:00:40:10 /date:15/03/2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Handler.checkTypeOfExercise(input2);
        });

        // with invalid exercise type
        String input3 = "new /e:gymm /d:10.3 /t:00:40:10 /date:15/03/2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Handler.checkTypeOfExercise(input3);
        });

        // with invalid format
        String input4 = "new /e-gymm /d-10.3 /t:00:40:10 /date:15/03/2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Handler.checkTypeOfExercise(input4);
        });

        // with wrong slash
        String input5 = "new \\e:run \\d:30:10 \\t:00:20:10 \\date:15/03/2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Handler.checkTypeOfExercise(input5);
        });
    }

    /**
     * Test the behavior of the checkTypeOfExercise method when the user input has insufficient parameters.
     * Expected behavior is to raise {@code InsufficientInput} exception.
     * Does not test for invalid values.
     * Refer to {@code checkTypeOfExercise_invalidUserInput_throwInvalidInput()} for that.
     */
    @Test
    void checkTypeOfExercise_insufficientUserInput_throwInsufficientInput() {
        // without distance, time, and date
        String input2 = "new /e:run";
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Handler.checkTypeOfExercise(input2));

        // without time and date
        String input3 = "new /e:run /d:10.3";
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Handler.checkTypeOfExercise(input3));

        // without the date
        String input4 = "new /e:run /d:30:10 /t:00:20:10";
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Handler.checkTypeOfExercise(input4));
    }
}
