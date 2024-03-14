package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.CustomExceptions;
import workouts.Run;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.fail;

class HandlerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /*
    @Test
    void handleExercise_validInputRun_expectRunAdded() throws CustomExceptions.InvalidInput {
        // Test Setup
        String input = "new /e:run /d:10.3 /t:00:40:10 /date:15/03/2024";

        // Exercise
        Handler.handleExercise(input);

        // Verify
        String expected = "Added: run | 10.3 | 00:40:10 | 2024-03-15\n";
        assertEquals(expected, outContent.toString());
    }

    @Test
    void handleExercise_invalidInputMissingParameter_expectException() {
        // Test Setup
        String input = "new /e:run /d:10.3 /t:00:40:10"; // Missing /date parameter

        // Exercise and Verify
        assertThrows(CustomExceptions.InvalidInput.class, () -> Handler.handleExercise(input));
    }

     */

    @Test
    void getRun_validInput_expectCorrectParsing() throws CustomExceptions.InvalidInput {
        // Test Setup
        String input = "new /e:run /d:10.3 /t:00:40:10 /date:15/03/2024";

        // Exercise
        String[] result = Handler.getRun(input);

        // Verify
        assertArrayEquals(new String[]{"run", "10.3", "00:40:10", "15/03/2024"}, result);
    }

    @Test
    void getRun_missingParameter_expectException() {
        // Test Setup
        String input = "new /e:run /d:10.3 /t:00:40:10"; // Missing /date parameter

        // Exercise and Verify
        assertThrows(CustomExceptions.InvalidInput.class, () -> Handler.getRun(input));
    }
    
    /*
    @Test
    void handleNew_validInputRun_expectRunAdded() throws CustomExceptions.InvalidInput {
        // Test Setup
        String input = "new /e:run /d:10.3 /t:00:40:10 /date:15/03/2024";

        // Exercise
        Handler.handleNew(input);

        // Verify
        String expected = "Added: run | 10.3 | 00:40:10 | 2024-03-15\n";
        assertEquals(expected, outContent.toString());
    }


    @Test
    void handleHistory_allFilter_expectAllWorkoutsPrinted() {
        // Test Setup
        new Run("40:10", "10.3", "15/03/2024");
        new Run("01:59:10", "15.3");

        // Exercise
        Handler.handleHistory("history all");

        // Verify
        // Add the expected output string here
        String expected = "..."; // Implement the expected output
        assertEquals(expected, outContent.toString());
    }

    @Test
    void handleLatest_oneRunInList_expectLatestRunPrinted() {
        // Test Setup
        new Run("40:10", "10.3", "15/03/2024");

        // Exercise
        Handler.handleLatest("latest");

        // Verify
        // Add the expected output string here
        String expected = "..."; // Implement the expected output
        assertEquals(expected, outContent.toString());
    }

    @Test
    void handleHelp_noInput_expectHelpMessagePrinted() {
        // Exercise
        Handler.handleHelp("");

        // Verify
        // Add the expected output string here
        String expected = "..."; // Implement the expected help message
        assertEquals(expected, outContent.toString());
    }

     */
}
