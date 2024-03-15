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
        Health.height = 1.75; // 175 cm
        Health.weight = 70.0; // 70 kg
        String expected = "Your BMI is 22.86"
                + System.lineSeparator()
                + "Great! You're within normal range."
                + System.lineSeparator();

        // Act
        Bmi.calculateBmi();

        // Assert
        assertEquals(expected, outContent.toString());
    }

    @Test
    void printBMICategory_underweight_printsCorrectCategory() {
        // Arrange
        String expected = "You're underweight." + System.lineSeparator();

        // Act
        Bmi.printBmiCategory(17.5);

        // Assert
        assertEquals(expected, outContent.toString());
    }

    @Test
    void printBMICategory_normal_printsCorrectCategory() {
        // Arrange
        String expected = "Great! You're within normal range." + System.lineSeparator();

        // Act
        Bmi.printBmiCategory(22.0);

        // Assert
        assertEquals(expected, outContent.toString());
    }

    @Test
    void printBMICategory_overweight_printsCorrectCategory() {
        // Arrange
        String expected = "You're overweight." + System.lineSeparator();

        // Act
        Bmi.printBmiCategory(27.0);

        // Assert
        assertEquals(expected, outContent.toString());
    }

    @Test
    void printBMICategory_obese_printsCorrectCategory() {
        // Arrange
        String expected = "You're obese." + System.lineSeparator();

        // Act
        Bmi.printBmiCategory(32.0);

        // Assert
        assertEquals(expected, outContent.toString());
    }

    @Test
    void printBMICategory_severelyObese_printsCorrectCategory() {
        // Arrange
        String expected = "You're severely obese." + System.lineSeparator();

        // Act
        Bmi.printBmiCategory(40.0);

        // Assert
        assertEquals(expected, outContent.toString());
    }
}
