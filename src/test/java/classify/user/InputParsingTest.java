package classify.user;

import classify.student.Student;

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
        String [] commands = new String[2];
        commands[0] = "bye";
        InputParsing.parseUserCommand(commands, masterStudentList, new Scanner(System.in));

        System.setOut(System.out);

        String printedOutput = outputStream.toString().trim();
        String expectedOutput = "Hope you've had a productive day. See you again! Goodbye!" +
                System.lineSeparator() + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        assertEquals(expectedOutput.trim(), printedOutput);
    }

    //@@author blackmirag3
    @Test
    public void testUnknownCommand() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ArrayList<Student> students = new ArrayList<>();
        String [] commands = new String[2];
        commands[0] = "yeet";
        InputParsing.parseUserCommand(commands, students, new Scanner(System.in));
        System.setOut(System.out);
        String printedOutput = outputStream.toString().trim();
        String expectedOutput = "No such command, type \"help\" to view all commands" +
                System.lineSeparator() + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        assertEquals(expectedOutput.trim(), printedOutput);
    }

    //@@author alalal47
    @Test
    public void testHelpCommand() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ArrayList<Student> masterStudentList = new ArrayList<>();
        String [] commands = new String[2];
        commands[0] = "help";
        InputParsing.parseUserCommand(commands, masterStudentList, new Scanner(System.in));

        System.setOut(System.out);

        String printedOutput = outputStream.toString().trim();
        String expectedOutput =
                "add                         Adds a student to the student list, expects a name" +
                                             ", grade and lessons attended," +
                                             " can be used directly with a name e.g. add [name]"
                                             + System.lineSeparator() +
                "edit                        Edits a students details, expects a name" +
                                             ", can be used directly with a name e.g. edit [name]"
                                             + System.lineSeparator() +
                "view                        Views a students details, expects a name" +
                                             ", can be used directly with a name e.g. add [name]"
                                             + System.lineSeparator() +
                "delete                      Deletes a student from the student list, expects a name" +
                                             ", can be used directly with a name e.g. add [name]"
                                             + System.lineSeparator() +
                "list                        Displays the list of all students"
                                             + System.lineSeparator() +
                "bye                         Exits Classify"
                                             + System.lineSeparator() +
                "help                        Prints this help message" +
                System.lineSeparator() + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        assertEquals(expectedOutput.trim(), printedOutput);
    }
}
