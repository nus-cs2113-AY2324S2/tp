package ui;

import org.junit.jupiter.api.AfterEach;
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
import health.Bmi;
import health.Period;
import health.HealthList;

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

    @AfterEach
    public void cleanup() {
        WorkoutList.clearWorkoutsAndRun();
        HealthList.clearBmisAndPeriods();
        outContent.reset();
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    @Test
    void printHistory_runsOnly_expectAllRunsPrinted() throws CustomExceptions.InvalidInput {
        new Run("40:10", "10.3", "15-03-2024");
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
        expected.replaceAll("\\n|\\r\\n", System.lineSeparator());

        assertThrows(IllegalArgumentException.class, () -> Output.printHistory(input));
        cleanup();

    }

    @Test
    void printLatestRun_oneRun_expectOneRunPrinted() throws CustomExceptions.InvalidInput {
        new Run("40:10", "10.3");
        String expected = "Index\t\tType\tTime\t\tDistance\tPace\t\tDate\n" +
                "1.\t\t\trun \t40:10\t\t10.3\t\t3:54/km\t\tNA\n";
        expected = expected.replaceAll("\\n|\\r\\n", System.lineSeparator());
        Output.printLatestRun();
        assertEquals(expected, outContent.toString());
        cleanup();
    }

    @Test
    void printLatestRun_noRun_expectNoRunMessage() {
        String expected = "\u001B[31mError: No runs found! You need to add a run first!\u001B[0m\n";
        expected = expected.replaceAll("\\n|\\r\\n", System.lineSeparator());
        Output.printLatestRun();
        assertEquals(expected, outContent.toString());
        cleanup();
    }

    @Test
    void printLatestGym_twoGyms_expectOneGymPrinted() {
        try{
            Gym gym1 = new Gym();
            gym1.addStation("Bench Press", 4, 10, 50);
            gym1.addStation("Shoulder Press", 20, 4, 10);

            Gym gym2 = new Gym();
            gym2.addStation("Squat Press", 4, 10, 50);
            gym2.addStation("Lat Press", 20, 4, 10);

            String expected = "Gym Session 2\n" +
                    String.format(Constant.GYM_STATION_FORMAT, "Squat Press") +
                    String.format(Constant.INDIVIDUAL_GYM_STATION_FORMAT, 10, "50 reps at 4 KG") +
                    "\n" +
                    String.format(Constant.GYM_STATION_FORMAT, "Lat Press") +
                    String.format(Constant.INDIVIDUAL_GYM_STATION_FORMAT, 4, "10 reps at 20 KG") + "\n";
            Output.printLatestGym();
            expected = expected.replaceAll("\\n|\\r\\n", System.lineSeparator());
            assertEquals(expected, outContent.toString());


        }  catch (CustomExceptions.InvalidInput e) {
            System.out.println(e.getMessage());
        }
        cleanup();
    }

    @Test
    void printLatestGym_noGym_expectNoGymMessage() {
        String expected = "\u001B[31mError: No gyms found! You need to add a gym first!\u001B[0m\n";

        expected = expected.replaceAll("\\n|\\r\\n", System.lineSeparator());
        Output.printLatestGym();
        assertEquals(expected, outContent.toString());
        cleanup();
    }

    @Test void printLatestBmi_twoBmis_expectOneBmiPrinted() {
        Bmi firstBmi = new Bmi("1.75", "70.0", "18-03-2024");
        Bmi secondBmi = new Bmi("1.55", "55.0", "20-03-2024");
        HealthList.addBmi(firstBmi);
        HealthList.addBmi(secondBmi);
        Output.printLatestBmi();
        String expected = "2024-03-20" +
                System.lineSeparator()+
                "Your BMI is 22.89" +
                System.lineSeparator() +
                "Great! You're within normal range." +
                System.lineSeparator();
        assertEquals(expected, outContent.toString());
        cleanup();
    }

    @Test
    void printLatestBmi_noBmi_expectAssertionError() {
        assertThrows(AssertionError.class, Output::printLatestBmi);
        cleanup();
    }

    @Test
    void printLatestPeriod_twoPeriods_expectOneBmiPrinted() {
        Period firstPeriod = new Period("09-02-2023", "16-02-2023");
        Period secondPeriod = new Period("09-03-2023", "16-03-2023");
        HealthList.addPeriod(firstPeriod);
        HealthList.addPeriod(secondPeriod);
        Output.printLatestPeriod();
        String expected = "Period Start: 2023-03-09 Period End: 2023-03-16" +
                System.lineSeparator() +
                "Period Length: 8 days" +
                System.lineSeparator();

        assertEquals(expected, outContent.toString());
        cleanup();
    }


    @Test
    void printLatestPeriod_noPeriod_expectAssertionError() {
        assertThrows(AssertionError.class, Output::printLatestPeriod);
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
