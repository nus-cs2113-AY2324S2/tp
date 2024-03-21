package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.Parser.parseIntoNameAndArgumentString;

public class ParserTest {
    @Test
    public void testParseIntoNameAndArgumentString() {
        String userInput0 = "bye";
        String userInput1 = "shoot";
        String userInput2 = "shoot 1";

        assertEquals(1, parseIntoNameAndArgumentString(userInput0).length);
        assertEquals(1, parseIntoNameAndArgumentString(userInput1).length);
        assertEquals(2, parseIntoNameAndArgumentString(userInput2).length);
    }
}
