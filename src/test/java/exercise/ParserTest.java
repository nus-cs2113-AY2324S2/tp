package exercise;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    private static void cleanup() {
        WorkoutList.clearWorkoutsAndRun();
        outContent.reset();
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    @Test
    void printHistory_Runsonly_expectAllRunsPrinted() {
        new Run("00:40:10", "10.3");
        new Run("00:59:10", "15.3");
        String expected = "Index\t\tType\tTime\t\tDistance\tPace\n" +
                "0\t\t\trun \t0:40\t\t10.3\t\t3:54/km\n" +
                "1\t\t\trun \t0:59\t\t15.3\t\t3:52/km\n\n";
        Parser.printHistory("all");
        assertEquals(expected, outContent.toString());
        cleanup();

    }

    @Test
    void printLatestRun_oneRun_expectAllRunsPrinted() {
        new Run("00:40:10", "10.3");
        String expected = "Type\tTime\t\tDistance\tPace\n" +
                "run \t0:40\t\t10.3\t\t3:54/km\n\n";
        Parser.printLatestRun();
        assertEquals(expected, outContent.toString());
        cleanup();
    }
}