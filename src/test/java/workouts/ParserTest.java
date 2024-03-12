package workouts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import ui.Output;
import utility.Constant;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void printHistory_runsOnly_expectAllRunsPrinted() {
        Run r1 = new Run("40:10", "10.3", "15/03/2024");
        Run r2 = new Run("01:59:10", "15.3");
        String expected = Constant.PARTITION_LINE + "\n" +
                "Index\t\tType\tTime\t\tDistance\tPace\t\tDate\n" +
                "1.\t\t\trun \t40:10\t\t10.3\t\t3:54/km\t\t2024-03-15\n" +
                "2.\t\t\trun \t1:59:10\t\t15.3\t\t7:47/km\t\tNA\n" +
                Constant.PARTITION_LINE + "\n";
        expected = expected.replaceAll("\\n|\\r\\n", System.lineSeparator());
        Output.printHistory("run");
        assertEquals(expected, outContent.toString());
        cleanup();

    }

    @Test
    void printLatestRun_oneRun_expectOneRunPrinted() {
        new Run("40:10", "10.3");
        String expected = Constant.PARTITION_LINE + "\n" +
                "Index\t\tType\tTime\t\tDistance\tPace\t\tDate\n" +
                "1.\t\t\trun \t40:10\t\t10.3\t\t3:54/km\t\tNA\n" +
                Constant.PARTITION_LINE + "\n";
        expected = expected.replaceAll("\\n|\\r\\n", System.lineSeparator());
        Output.printLatestRun();
        assertEquals(expected, outContent.toString());
        cleanup();
    }
}
