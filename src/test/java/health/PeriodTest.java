package health;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        // Arrange
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
        // Arrange
        Period firstPeriod = new Period("10-04-2024", "16-04-2024");
        Period secondPeriod = new Period("09-05-2024", "16-05-2024");

        HealthList.addPeriod(firstPeriod);
        HealthList.addPeriod(secondPeriod);

        String expected = "Period Start: 2023-02-09 Period End: 2023-02-16"
                + System.lineSeparator()
                + "Period Length: 8 days"
                + System.lineSeparator()
                + "Period Start: 2023-03-09 Period End: 2023-03-16"
                + System.lineSeparator()
                + "Period Length: 8 days"
                + System.lineSeparator()
                + "Period Start: "
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
}
