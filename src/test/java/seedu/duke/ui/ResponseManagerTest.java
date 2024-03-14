package seedu.duke.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseManagerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }


    @Test
    void printJobSelectionErrorMessage() {
        ResponseManager.printJobSelectionErrorMessage();
        assertEquals("Invalid job type." +
            " Please choose from Robotics, Semiconductor industry," +
            " Artificial intelligence.", outputStreamCaptor.toString().trim());
    }

    @Test
    void printChooseIndustryMessage() {
        String testIndustry = "Robotics";
        ResponseManager.printChooseIndustryMessage(testIndustry);
        assertEquals("You have chosen Robotics", outputStreamCaptor.toString().trim());
    }

    @Test
    void printInvalidNameMessage() {
        ResponseManager.printInvalidNameMessage();
        assertEquals("Please enter a valid name", outputStreamCaptor.toString().trim());
    }

    @Test
    void printNameTooLongMessage() {
        ResponseManager.printNameTooLongMessage();
        assertEquals("Name is too long", outputStreamCaptor.toString().trim());
    }
}
