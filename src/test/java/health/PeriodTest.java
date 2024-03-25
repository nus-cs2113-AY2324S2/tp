package health;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.CustomExceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class PeriodTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
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
                + period.endDate
                + System.lineSeparator()
                + "Period Length: "
                + period.length
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
                + secondPeriod.endDate
                + System.lineSeparator()
                + "Period Length: "
                + secondPeriod.length
                + " days"
                + System.lineSeparator();

        HealthList.showLatestPeriod();
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of the showPeriodHistory function and whether it prints
     * all Period objects added.
     */
    @Test
    void showPeriodHistory_printCorrectPeriodHistory() {
        Period firstPeriod = new Period("10-04-2024", "16-04-2024");
        Period secondPeriod = new Period("09-05-2024", "16-05-2024");

        HealthList.addPeriod(firstPeriod);
        HealthList.addPeriod(secondPeriod);

        String expected = "Period Start: "
                + firstPeriod.getStartDate()
                + " Period End: "
                + firstPeriod.endDate
                + System.lineSeparator()
                + "Period Length: "
                + firstPeriod.length
                + " days"
                + System.lineSeparator()
                + "Period Start: "
                + secondPeriod.getStartDate()
                + " Period End: "
                + secondPeriod.endDate
                + System.lineSeparator()
                + "Period Length: "
                + secondPeriod.length
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
    }
}
