package health;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BmiTest {
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
    void calculateBMI_heightWeight_printsCorrectBMIAndCategory() {
        // Arrange
        Bmi bmi = new Bmi("1.75", "70.0");
        String expected = "Your BMI is 22.86"
                + System.lineSeparator()
                + "Great! You're within normal range."
                + System.lineSeparator();

        // Act
        System.out.println(bmi);

        // Assert
        assertEquals(expected, outContent.toString());
    }

    @Test
    void printBMICategory_underweight_printsCorrectCategory() {
        // Arrange
        String expected = "You're underweight." + System.lineSeparator();

        // Act
        System.out.println(Bmi.getBmiCategory(17.5));

        // Assert
        assertEquals(expected, outContent.toString());
    }

    @Test
    void printBMICategory_normal_printsCorrectCategory() {
        // Arrange
        String expected = "Great! You're within normal range." + System.lineSeparator();

        // Act
        System.out.println(Bmi.getBmiCategory(22.0));

        // Assert
        assertEquals(expected, outContent.toString());
    }

    @Test
    void printBMICategory_overweight_printsCorrectCategory() {
        // Arrange
        String expected = "You're overweight." + System.lineSeparator();

        // Act
        System.out.println(Bmi.getBmiCategory(27.0));

        // Assert
        assertEquals(expected, outContent.toString());
    }

    @Test
    void printBMICategory_obese_printsCorrectCategory() {
        // Arrange
        String expected = "You're obese." + System.lineSeparator();

        // Act
        System.out.println(Bmi.getBmiCategory(32.0));

        // Assert
        assertEquals(expected, outContent.toString());
    }

    @Test
    void printBMICategory_severelyObese_printsCorrectCategory() {
        // Arrange
        String expected = "You're severely obese." + System.lineSeparator();

        // Act
        System.out.println(Bmi.getBmiCategory(40.0));

        // Assert
        assertEquals(expected, outContent.toString());
    }

    @Test
    void showCurrentBmi_printsCorrectCurrentBmi() {
        // Arrange
        Bmi bmi = new Bmi("1.71", "60.5");

        HealthList.addBmi(bmi);

        String expected = "Your BMI is 20.69"
                + System.lineSeparator()
                + "Great! You're within normal range."
                + System.lineSeparator();

        // Act
        HealthList.showCurrentBmi();

        // Assert
        assertEquals(expected, outContent.toString());
    }
}
