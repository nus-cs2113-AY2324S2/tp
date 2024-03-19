package health;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void calculatePeriodLength_printsCorrectPeriod() {
        // Arrange
        Period period = new Period("09/03/2022", "16/03/2022");
        String expected = "Period Start: "
                + period.getStartDate()
                + " Period End: "
                + period.endDate
                + System.lineSeparator()
                + "Period Length: "
                + period.length
                + " days"
                + System.lineSeparator();

        // Act
        System.out.println(period);

        // Assert
        assertEquals(expected, outContent.toString());
    }

    @Test
    void showLatestPeriod_printCorrectPeriod() {
        // Arrange
        Period firstPeriod = new Period("09/02/2023", "16/02/2023");
        Period secondPeriod = new Period("09/03/2023", "16/03/2023");

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

        // Act
        HealthList.showLatestPeriod();

        // Assert
        assertEquals(expected, outContent.toString());
    }
}
