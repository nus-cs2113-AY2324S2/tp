package seedu.duke.ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;

    @BeforeEach
    public void setUpInput() {
        // This is the input we want to simulate for a particular test case.
        String simulatedUserInput = "test input\n";
        testIn = new ByteArrayInputStream(simulatedUserInput.getBytes());
        System.setIn(testIn);
    }

    @AfterEach
    public void restoreSystemInput() {
        System.setIn(systemIn);
    }

    @Test
    public void readUserInput_returnsCorrectly() {
        Ui ui = new Ui();
        String userInput = ui.readUserInput();
        assertEquals("test input", userInput, "The method should return the input provided by the user.");
    }
}
