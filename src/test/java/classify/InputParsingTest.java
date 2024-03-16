package classify;

import classify.student.Student;
import classify.student.StudentAttributes;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class InputParsingTest {

    //@@author tayponghee
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

    //@author blackmirag3
    @Test
    public void testUnknownCommand() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ArrayList<Student> students = new ArrayList<>();
        InputParsing.parseUserCommand("yeet", students, new Scanner(System.in));
        System.setOut(System.out);
        String printedOutput = outputStream.toString().trim();
        String expectedOutput = "Wrong Input! Please try again!" +
                System.lineSeparator() + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        assertEquals(expectedOutput.trim(), printedOutput);
    }
}
