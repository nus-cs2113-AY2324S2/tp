package classify;

import classify.student.Student;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author tayponghee
public class InputParsingTest {

    @Test
    public void testByeCommand() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ArrayList<Student> masterStudentList = new ArrayList<>();
        InputParsing.parseUserCommand("bye", masterStudentList, new Scanner(System.in));

        System.setOut(System.out);

        String printedOutput = outputStream.toString().trim();
        String expectedOutput = "Hope you've had a productive day. See you again! Goodbye!" +
                System.lineSeparator() + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        assertEquals(expectedOutput.trim(), printedOutput);
    }
}
