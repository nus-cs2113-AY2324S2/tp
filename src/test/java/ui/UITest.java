package ui;

import org.junit.jupiter.api.Test;
import recipeio.InputParser;
import recipeio.ui.UI;

import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class UITest {

    /**
     * A helper function to simulate user-input process
     * @param data test data
     */
    private void getInput(String data){
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testUISayHi(){

        String expected = UI.SEPARATOR + System.lineSeparator() +
                "Welcome to Recipe.io!" + System.lineSeparator();
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(testOut);
        System.setOut(printStream);

        UI.sayHi();

        String actual = testOut.toString();

        assertEquals(actual, expected);
    }

    @Test
    public void testPrintMessage(){
        String message = "LMAO IT WORKS";

        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(testOut);
        System.setOut(printStream);

        String expected = UI.SEPARATOR + System.lineSeparator() +
                message + System.lineSeparator() +
                UI.SEPARATOR + System.lineSeparator();

        UI.printMessage(message);

        String actual = testOut.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testBye() {
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(testOut);
        System.setOut(printStream);

        String expected = UI.SEPARATOR + System.lineSeparator() +
                "See you again chef!" + System.lineSeparator() +
                UI.SEPARATOR + System.lineSeparator();

        UI.bye();

        String actual = testOut.toString();

        assertEquals(expected, actual);
    }

    @Test void testParseCommand() {
        String userInput = "delete 1";
        String command = InputParser.parseCommand(userInput);

        assertEquals("delete", command);
    }
}
