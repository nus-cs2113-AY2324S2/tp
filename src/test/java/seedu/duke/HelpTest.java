package seedu.duke;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class HelpTest {
    private static final String prompt =
                    "Welcome\n" +
                    "help: Access help menu.\n" +
                    "group <name>: Create or enter a group.\n" +
                    "member <name>: Add a member to the group.\n" +
                    "expense /amount <amount> /paid <paid_by> /user <user_1> /user <user_2> ...: Add an expense.\n" +
                    "list: List all expenses in the group.\n" +
                    "balance <user_name>: Show user's balance.\n";

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }
    @Test
    public void testPrint() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        printHelp();
        String output = baos.toString();
        assertEquals(prompt, output);
    }

    static void printHelp() {
        System.out.print(prompt);

    }
}