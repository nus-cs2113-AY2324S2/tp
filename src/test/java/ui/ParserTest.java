package ui;

import exception.JobSelectException;
import exception.NameInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    @Test
    void parseName_validInput_returnsName() throws NameInputException {
        assertEquals("John", Parser.parseName("John"));
    }

    @Test
    void parseName_invalidInput_throwsException() {
        assertThrows(NameInputException.class, () -> Parser.parseName("John123"));
    }

    @Test
    void parseName_nameTooLong_throwsException() {
        assertThrows(NameInputException.class, () -> Parser.parseName("JohnJohnJohnJohnJohn"));
    }

    @Test
    void parseCareer_validInput_returnsCareer() throws JobSelectException {
        assertEquals("Robotics", Parser.parseCareer("Robotics"));
    }

    @Test
    void parseCareer_invalidInput_throwsException() {
        assertThrows(JobSelectException.class, () -> Parser.parseCareer("Robot"));
    }
}
