package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import utility.Constant;
import utility.CustomExceptions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

//import static org.junit.jupiter.api.Assertions.assertEquals;
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
        String input = "new /e:run /d:10.3"; // Missing /t parameter

        // Exercise and Verify
        assertThrows(CustomExceptions.InvalidInput.class, () -> Handler.getRun(input));
    }
}
