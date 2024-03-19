package health;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import utility.Constant;
import utility.CustomExceptions;

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

    /**
     * Tests the behaviour of toString in Bmi class.
     */
    @Test
    void toString_heightWeight_printsCorrectBMIAndCategory() {
        // Arrange
        Bmi bmi = new Bmi("1.75", "70.0", "19-03-2024");
        String expected = "2024-03-19"
                + System.lineSeparator()
                + "Your BMI is 22.86"
                + System.lineSeparator()
                + "Great! You're within normal range."
                + System.lineSeparator();

        // Act
        System.out.println(bmi);

        // Assert
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of a BMI within underweight range being passed into printsCorrectCategory.
     */
    @Test
    void printBMICategory_underweight_printsCorrectCategory() {
        // Arrange
        String expected = "You're underweight." + System.lineSeparator();

        // Act
        System.out.println(Bmi.getBmiCategory(17.5));

        // Assert
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of a BMI within normal range being passed into printsCorrectCategory.
     */
    @Test
    void printBMICategory_normal_printsCorrectCategory() {
        // Arrange
        String expected = "Great! You're within normal range." + System.lineSeparator();

        // Act
        System.out.println(Bmi.getBmiCategory(22.0));

        // Assert
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of a BMI within overweight range being passed into printsCorrectCategory.
     */
    @Test
    void printBMICategory_overweight_printsCorrectCategory() {
        // Arrange
        String expected = "You're overweight." + System.lineSeparator();

        // Act
        System.out.println(Bmi.getBmiCategory(27.0));

        // Assert
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of a BMI within obese range being passed into printsCorrectCategory.
     */
    @Test
    void printBMICategory_obese_printsCorrectCategory() {
        // Arrange
        String expected = "You're obese." + System.lineSeparator();

        // Act
        System.out.println(Bmi.getBmiCategory(32.0));

        // Assert
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of a BMI within severely obese range being passed into printsCorrectCategory.
     */
    @Test
    void printBMICategory_severelyObese_printsCorrectCategory() {
        // Arrange
        String expected = "You're severely obese." + System.lineSeparator();

        // Act
        System.out.println(Bmi.getBmiCategory(40.0));

        // Assert
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of showCurrentBmi.
     */
    @Test
    void showCurrentBmi_bmiObject_printsCorrectCurrentBmi() {
        // Arrange
        Bmi bmi = new Bmi("1.75", "70.00", "19-03-2024");

        HealthList.addBmi(bmi);

        String expected = "2024-03-19"
                + System.lineSeparator()
                + "Your BMI is 22.86"
                + System.lineSeparator()
                + "Great! You're within normal range."
                + System.lineSeparator();

        // Act
        HealthList.showCurrentBmi();

        // Assert
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of a valid health command being passed into checkTypeOfHealth.
     */
    @Test
    void checkTypeOfHealth_returnCorrectHealthType() throws
            CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput{
        // Arrange
        String userInput = "/h:bmi /height:1.71 /weight:60.50 /date:19-03-2024";

        String expected = Constant.BMI;

        // Act
        String result = Health.checkTypeOfHealth(userInput);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the behaviour of an invalid health command being passed into checkTypeOfHealth.
     */
    @Test
    void checkTypeOfHealth_throwsInvalidInputExceptions() {
        // Arrange
        String userInput = "/h:run /height:1.71 /weight:60.50 /date:19-03-2024";

        // Act & Assert
        assertThrows(CustomExceptions.InvalidInput.class, () -> Bmi.checkTypeOfHealth(userInput));
    }

    /**
     * Tests the behaviour of a correctly formatted user input being passed into getBmi.
     */
    @Test
    void getBmi_correctInput_returnsCorrectBmiValues() throws CustomExceptions.InvalidInput {
        // Arrange
        String input = "/h:bmi /height:1.71 /weight:60.50 /date:19-03-2024";
        String[] expected = {"bmi", "1.71", "60.50", "19-03-2024"};

        // Act
        String[] result = Bmi.getBmi(input);

        // Assert
        assertArrayEquals(expected, result);
    }

    /**
     * Test the behaviour of a string with missing parameter being passed in for getBmi.
     */
    @Test
    void getBmi_wrongInput_throwsInvalidInputExceptions() {
        // Arrange
        String input = "/h:bmi /height:1.71 /date:19-03-2024";

        // Act & Assert
        assertThrows(CustomExceptions.InvalidInput.class, () -> Bmi.getBmi(input));
    }

    /**
     * Test the behaviour of printing Bmi history.
     */
    @Test
    void showBmiHistory_bmiObject_printsCorrectBmiHistory() {
        // Arrange
        Bmi firstBmi = new Bmi("1.75", "80.0", "20-03-2024");
        Bmi secondBmi = new Bmi("1.80", "74.0", "21-03-2024");

        HealthList.addBmi(firstBmi);
        HealthList.addBmi(secondBmi);

        String expected = "2024-03-19"
                + System.lineSeparator()
                + "Your BMI is 22.86"
                + System.lineSeparator()
                + "Great! You're within normal range."
                + System.lineSeparator()
                + "2024-03-20"
                + System.lineSeparator()
                + "Your BMI is 26.12"
                + System.lineSeparator()
                + "You're overweight."
                + System.lineSeparator()
                + "2024-03-21"
                + System.lineSeparator()
                + "Your BMI is 22.84"
                + System.lineSeparator()
                + "Great! You're within normal range."
                + System.lineSeparator();

        // Act
        HealthList.showBmiHistory();

        // Assert
        assertEquals(expected, outContent.toString());
    }
}
