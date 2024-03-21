package health;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import utility.UiConstant;
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
        Bmi bmi = new Bmi("1.75", "70.0", "19-03-2024");
        String expected = "2024-03-19"
                + System.lineSeparator()
                + "Your BMI is 22.86"
                + System.lineSeparator()
                + "Great! You're within normal range."
                + System.lineSeparator();

        System.out.println(bmi);
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of a BMI within underweight range being passed into printsCorrectCategory.
     */
    @Test
    void printBMICategory_underweight_printsCorrectCategory() {
        String expected = "You're underweight." + System.lineSeparator();
        System.out.println(Bmi.getBmiCategory(17.5));
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of a BMI within normal range being passed into printsCorrectCategory.
     */
    @Test
    void printBMICategory_normal_printsCorrectCategory() {
        String expected = "Great! You're within normal range." + System.lineSeparator();
        System.out.println(Bmi.getBmiCategory(22.0));
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of a BMI within overweight range being passed into printsCorrectCategory.
     */
    @Test
    void printBMICategory_overweight_printsCorrectCategory() {
        String expected = "You're overweight." + System.lineSeparator();
        System.out.println(Bmi.getBmiCategory(27.0));
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of a BMI within obese range being passed into printsCorrectCategory.
     */
    @Test
    void printBMICategory_obese_printsCorrectCategory() {
        String expected = "You're obese." + System.lineSeparator();
        System.out.println(Bmi.getBmiCategory(32.0));
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of a BMI within severely obese range being passed into printsCorrectCategory.
     */
    @Test
    void printBMICategory_severelyObese_printsCorrectCategory() {
        String expected = "You're severely obese." + System.lineSeparator();
        System.out.println(Bmi.getBmiCategory(40.0));
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of showCurrentBmi.
     */
    @Test
    void showCurrentBmi_bmiObject_printsCorrectCurrentBmi() {
        Bmi bmi = new Bmi("1.75", "70.00", "19-03-2024");
        HealthList.addBmi(bmi);

        String expected = "2024-03-19"
                + System.lineSeparator()
                + "Your BMI is 22.86"
                + System.lineSeparator()
                + "Great! You're within normal range."
                + System.lineSeparator();
        HealthList.showCurrentBmi();
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests the behaviour of a valid health command being passed into checkTypeOfHealth.
     */
    @Test
    void checkTypeOfHealth_returnCorrectHealthType() throws
            CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput{
        String userInput = "/h:bmi /height:1.71 /weight:60.50 /date:19-03-2024";
        String expected = UiConstant.BMI;
        String result = Health.checkTypeOfHealth(userInput);
        assertEquals(expected, result);
    }

    /**
     * Tests the behaviour of an invalid health command being passed into checkTypeOfHealth.
     */
    @Test
    void checkTypeOfHealth_throwsInvalidInputExceptions() {
        String userInput = "/h:run /height:1.71 /weight:60.50 /date:19-03-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Bmi.checkTypeOfHealth(userInput));
    }

    /**
     * Tests the behaviour of a correctly formatted user input being passed into getBmi.
     */
    @Test
    void getBmi_correctInput_returnsCorrectBmiValues() throws CustomExceptions.InvalidInput {
        String input = "/h:bmi /height:1.71 /weight:60.50 /date:19-03-2024";
        String[] expected = {"bmi", "1.71", "60.50", "19-03-2024"};
        String[] result = Bmi.getBmi(input);
        assertArrayEquals(expected, result);
    }

    /**
     * Test the behaviour of a string with missing parameter being passed in for getBmi.
     */
    @Test
    void getBmi_wrongInput_throwsInvalidInputExceptions() {
        String input = "/h:bmi /height:1.71 /date:19-03-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Bmi.getBmi(input));
    }

    /**
     * Test the behaviour of printing Bmi history.
     */
    @Test
    void showBmiHistory_bmiObject_printsCorrectBmiHistory() {
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

        HealthList.showBmiHistory();
        assertEquals(expected, outContent.toString());
    }
}
