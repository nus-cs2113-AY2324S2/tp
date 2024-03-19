package ui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import utility.Constant;
import utility.CustomExceptions;
import workouts.Gym;
import workouts.Run;
import workouts.WorkoutList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OutputTest {

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
    void printHistory_runsOnly_expectAllRunsPrinted() throws CustomExceptions.InvalidInput {
        new Run("40:10", "10.3", "15/03/2024");
        new Run("01:59:10", "15.3");
        String expected = Constant.PARTITION_LINE + "\n" +
                "Index\t\tType\tTime\t\tDistance\tPace\t\tDate\n" +
                "1.\t\t\trun \t40:10\t\t10.3\t\t3:54/km\t\t2024-03-15\n" +
                "2.\t\t\trun \t1:59:10\t\t15.3\t\t7:47/km\t\tNA\n" +
                Constant.PARTITION_LINE + "\n";
        expected = expected.replaceAll("\\n|\\r\\n", System.lineSeparator());
        Output.printHistory(Constant.RUN);
        assertEquals(expected, outContent.toString());
        cleanup();

    }

    @Test
    void printHistory_invalidHistoryFilter_throwError() {
        String input = "invalidfilter";
        String expected = Constant.PARTITION_LINE + "\n" +
                "\u001B[31mError: Invalid filter! Filter is only 'all', 'run' or 'gym'\u001B[0m" + "\n" +
                Constant.PARTITION_LINE + "\n";
        expected = expected.replaceAll("\\n|\\r\\n", System.lineSeparator());

        assertThrows(IllegalArgumentException.class, () -> {
            Output.printHistory(input);
        });
        cleanup();

    }

    @Test
    void printLatestRun_oneRun_expectOneRunPrinted() throws CustomExceptions.InvalidInput {
        // Test Setup
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

    @Test
    void printLatestRun_noRun_expectNoRunMessage() {
        // Test Setup

        String expected = Constant.PARTITION_LINE + "\n" +
                "\u001B[31mError: No runs found! You need to add a run first!\u001B[0m\n" +
                Constant.PARTITION_LINE + "\n";
        expected = expected.replaceAll("\\n|\\r\\n", System.lineSeparator());
        Output.printLatestRun();
        assertEquals(expected, outContent.toString());
        cleanup();
    }

    @Test
    void printGymHistory_correctInput_expectPrintGymHistory(){
        try{
            Gym gym1 = new Gym();
            gym1.addStation("Bench Press", 4, 10, 50);
            gym1.addStation("Shoulder Press", 20, 4, 10);

            Gym gym2 = new Gym();
            gym2.addStation("Squat Press", 4, 10, 50);
            gym2.addStation("Lat Press", 20, 4, 10);

            String expected = Constant.PARTITION_LINE + "\n" +
                    "Gym Session 1\n" +
                    String.format(Constant.GYM_STATION_FORMAT, "Bench Press") +
                    String.format(Constant.INDIVIDUAL_GYM_STATION_FORMAT, 10, "50 reps at 4 KG") +
                    "\n" +
                    String.format(Constant.GYM_STATION_FORMAT, "Shoulder Press") +
                    String.format(Constant.INDIVIDUAL_GYM_STATION_FORMAT, 4, "10 reps at 20 KG") +
                    "\n" + Constant.PARTITION_LINE + "\n" +
                    "Gym Session 2\n" +
                    String.format(Constant.GYM_STATION_FORMAT, "Squat Press") +
                    String.format(Constant.INDIVIDUAL_GYM_STATION_FORMAT, 10, "50 reps at 4 KG") +
                    "\n" +
                    String.format(Constant.GYM_STATION_FORMAT, "Lat Press") +
                    String.format(Constant.INDIVIDUAL_GYM_STATION_FORMAT, 4, "10 reps at 20 KG") +
                    "\n" + Constant.PARTITION_LINE + "\n";

            Output.printHistory(Constant.GYM);
            expected = expected.replaceAll("\\n|\\r\\n", System.lineSeparator());
            assertEquals(expected, outContent.toString());
            cleanup();

        }  catch (CustomExceptions.InvalidInput e) {
            System.out.println(e.getMessage());
        }
    }
}
