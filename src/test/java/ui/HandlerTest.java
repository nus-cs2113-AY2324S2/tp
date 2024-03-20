package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.Constant;

import utility.CustomExceptions;
import workouts.Run;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


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

    @Test
    void getRun_validInput_expectCorrectParsing() throws CustomExceptions.InvalidInput {
        // Test Setup
        String input = "new /e:run /d:10.3 /t:00:40:10 /date:15-03-2024";

        // Exercise
        String[] result = Run.getRun(input);

        // Verify
        assertArrayEquals(new String[]{"run", "10.3", "00:40:10", "15-03-2024"}, result);
    }

    @Test
    void getRun_missingParameter_expectException() {
        // Test Setup
        String input = "new /e:run /d:10.3"; // Missing /t parameter

        // Exercise and Verify
        assertThrows(CustomExceptions.InvalidInput.class, () -> Run.getRun(input));
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
            String expected1 = Constant.RUN;
            String result1 = Handler.checkTypeOfExercise(input1);
            assertEquals(result1, expected1);

            String input2 = "new /e:gym /n:4";
            String expected2 = Constant.GYM;
            String result2 = Handler.checkTypeOfExercise(input2);
            assertEquals(result2, expected2);

            // with capital letter
            String input3 = "NEW /E:run /D:10.3 /T:00:40:10 /Date:15-03-2024";
            String expected3 = Constant.RUN;
            String result3 = Handler.checkTypeOfExercise(input3);
            assertEquals(result3, expected3);

            String input4 = "NEW /E:gym /N:4";
            String expected4 = Constant.GYM;
            String result4 = Handler.checkTypeOfExercise(input4);
            assertEquals(result4, expected4);

            // exercises in capital letter
            String input5 = "NEW /E:RUN /D:10.3 /T:00:40:10 /Date:15-03-2024";
            String expected5 = Constant.RUN;
            String result5 = Handler.checkTypeOfExercise(input5);
            assertEquals(result5, expected5);

            String input6 = "NEW /E:GYM /N:4";
            String expected6 = Constant.GYM;
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
        String input2 = "new /e:wrong /d:10.3 /t:00:40:10 /date:15-03-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Handler.checkTypeOfExercise(input2);
        });

        // with invalid exercise type
        String input3 = "new /e:gymm /d:10.3 /t:00:40:10 /date:15-03-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Handler.checkTypeOfExercise(input3);
        });

        // with invalid format
        String input4 = "new /e-gymm /d-10.3 /t:00:40:10 /date:15-03-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Handler.checkTypeOfExercise(input4);
        });

        // with wrong slash
        String input5 = "new \\e:run \\d:30:10 \\t:00:20:10 \\date:15-03-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Handler.checkTypeOfExercise(input5);
        });

         */


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
        assertThrows(CustomExceptions.InsufficientInput.class, () -> {
            Handler.checkTypeOfExercise(input2);
        });

        // without time and date
        String input3 = "new /e:run /d:10.3";
        assertThrows(CustomExceptions.InsufficientInput.class, () -> {
            Handler.checkTypeOfExercise(input3);
        });

        // without the date
        String input4 = "new /e:run /d:30:10 /t:00:20:10";
        assertThrows(CustomExceptions.InsufficientInput.class, () -> {
            Handler.checkTypeOfExercise(input4);
        });
    }
}
