package seedu.lifetrack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.lifetrack.calories.calorielist.CalorieList;
import seedu.lifetrack.ui.Ui;
import seedu.lifetrack.liquids.liquidlist.LiquidList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UITest {
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
    // Expect Empty String as function is exited
    public void handleUserInput_inputBye_printByeMessage() {
        CalorieList calorieList = new CalorieList();
        LiquidList liquidList = new LiquidList();
        String input = "bye";
        Ui.handleUserInput(input, calorieList, liquidList);
        assertEquals("", outContent.toString());
    }
}
