package health;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utility.CustomExceptions;
import utility.HealthConstant;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

class PeriodTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void cleanup() {
        System.setOut(originalOut);
        HealthList.clearBmisAndPeriods();
        outContent.reset();
    }

    @AfterEach
    void cleanup() {
        HealthList.clearBmisAndPeriods();
    }

    /**
     * Tests the behaviour of toString in Period class.
     */
    @Test
    void calculatePeriodLength_printsCorrectPeriod() {
        Period period = new Period("09-03-2022", "16-03-2022");
        String expected = "Period Start: "
                + period.getStartDate()
                + " Period End: "
                + period.endPeriodDate
                + System.lineSeparator()
                + "Period Length: "
                + period.periodLength
                + " days"
                + System.lineSeparator();

        System.out.println(period);
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of the showLatestPeriod function and whether it prints
     * the last Period object added.
     */
    @Test
    void showLatestPeriod_printCorrectPeriod() {
        Period firstPeriod = new Period("09-02-2023", "16-02-2023");
        Period secondPeriod = new Period("09-03-2023", "16-03-2023");

        HealthList.addPeriod(firstPeriod);
        HealthList.addPeriod(secondPeriod);

        String expected = "Period Start: "
                + secondPeriod.getStartDate()
                + " Period End: "
                + secondPeriod.endPeriodDate
                + System.lineSeparator()
                + "Period Length: "
                + secondPeriod.periodLength
                + " days"
                + System.lineSeparator();

        HealthList.showLatestPeriod();
        assertEquals(expected, outContent.toString());
    }

    @Test

    void showPeriodHistory_twoInputs_printCorrectPeriodHistory() {
        Period firstPeriod = new Period("10-04-2023", "16-04-2023");
        Period secondPeriod = new Period("09-05-2023", "16-05-2023");
        HealthList.addPeriod(firstPeriod);
        HealthList.addPeriod(secondPeriod);

        String expected = "Period Start: "
                + firstPeriod.getStartDate()
                + " Period End: "
                + firstPeriod.endPeriodDate
                + System.lineSeparator()
                + "Period Length: "
                + firstPeriod.periodLength
                + " days"
                + System.lineSeparator()
                + "Cycle Length: "
                + firstPeriod.cycleLength
                + " days"
                + System.lineSeparator()
                + "Period Start: "
                + secondPeriod.getStartDate()
                + " Period End: "
                + secondPeriod.endPeriodDate
                + System.lineSeparator()
                + "Period Length: "
                + secondPeriod.periodLength
                + " days"
                + System.lineSeparator();

        HealthList.showPeriodHistory();
        assertEquals(expected, outContent.toString());
    }

    /**
     * Test deleting of periods with valid list and valid index.
     * Expected behaviour is to have one periods entry left in the list.
     *
     * @throws CustomExceptions.OutOfBounds If the index is invalid.
     */
    @Test
    void deletePeriod_properList_listOfSizeOne() throws CustomExceptions.OutOfBounds {
        Period firstPeriod = new Period("10-04-2024", "16-04-2024");
        Period secondPeriod = new Period("09-05-2024", "16-05-2024");
        HealthList.addPeriod(firstPeriod);
        HealthList.addPeriod(secondPeriod);;
        int index = 1;
        HealthList.deletePeriod(index);
        assertEquals(1, HealthList.getPeriodsSize());
    }

    /**
     * Test deleting of period with empty list.
     * Expected behaviour is for an AssertionError to be thrown.
     */
    @Test
    void deleteBmi_emptyList_throwsAssertionError() {
        assertThrows(AssertionError.class, () ->
                HealthList.deletePeriod(0));
    }

    /**
     * Test deleting of period with invalid index.
     * Expected behaviour is for an OutOfBounds error to be thrown.
     */
    @Test
    void deleteBmi_properListInvalidIndex_throwOutOfBoundsForBmi() {
        Period firstPeriod = new Period("10-04-2024", "16-04-2024");
        HealthList.addPeriod(firstPeriod);
        int invalidIndex = 5;
        assertThrows (CustomExceptions.OutOfBounds.class, () ->
                HealthList.deletePeriod(invalidIndex));
      
     /**
     * Tests the behaviour of the predictNextPeriodStartDate function and whether it prints
     * correct predicted start date.
     */
    @Test
    void predictNextPeriodStartDate_sufficientInputs_printCorrectPredictedDate() {
        Period firstPeriod = new Period("09-01-2024", "16-01-2024");
        Period secondPeriod = new Period("10-02-2024", "16-02-2024");
        Period thirdPeriod = new Period("09-03-2024", "14-03-2024");
        Period fourthPeriod = new Period("09-04-2024", "16-04-2024");

        HealthList.addPeriod(firstPeriod);
        HealthList.addPeriod(secondPeriod);
        HealthList.addPeriod(thirdPeriod);
        HealthList.addPeriod(fourthPeriod);

        long expectedCycleLength = (32 + 28 + 31) / HealthConstant.LATEST_THREE_CYCLE_LENGTHS;
        LocalDate expected = fourthPeriod.getStartDate().plusDays(expectedCycleLength);
        LocalDate result = HealthList.predictNextPeriodStartDate();
        assertEquals(expected, result);
    }

    /**
     * Tests the behaviour of the printNextCyclePrediction function and whether it prints
     * the predicted date with period is late message.
     */
    @Test
    void printNextCyclePrediction_afterToday_printPeriodIsLate() {
        LocalDate today = LocalDate.now();
        LocalDate predictedDate = today.minusDays(5);

        String expected = HealthConstant.PREDICTED_START_DATE_MESSAGE
                + predictedDate
                + HealthConstant.PERIOD_IS_LATE
                + "5"
                + HealthConstant.DAYS_MESSAGE
                + System.lineSeparator();

        Period.printNextCyclePrediction(predictedDate);
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of the printNextCyclePrediction function and whether it prints
     * the predicted date and the number of days to predicted date.
     */
    @Test
    void printNextCyclePrediction_beforeToday_printNumberOfDaysToPredictedDate() {
        LocalDate today = LocalDate.now();
        LocalDate predictedDate = today.plusDays(10);

        String expected = HealthConstant.PREDICTED_START_DATE_MESSAGE
                + predictedDate
                + ", in 10"
                + HealthConstant.DAYS_MESSAGE
                + System.lineSeparator();

        Period.printNextCyclePrediction(predictedDate);
        assertEquals(expected, outContent.toString());
    }
}
