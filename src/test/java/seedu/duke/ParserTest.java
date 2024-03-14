package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseName_validInput_returnsName() {
        assertEquals("John", Parser.parseName("John"));
    }

    @Test
    void parseName_invalidInput_returnsEmptyString() {
        assertEquals("", Parser.parseName("John123"));
    }

    @Test
    void parseName_nameTooLong_returnsEmptyString() {
        assertEquals("", Parser.parseName("JohnJohnJohnJohnJohn"));
    }

    @Test
    void parseCareer_validInput_returnsCareer() {
        assertEquals("Robotics", Parser.parseCareer("Robotics"));
    }
}