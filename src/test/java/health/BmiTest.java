package health;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import utility.CustomExceptions;

class BmiTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void cleanup() {
        System.setOut(originalOut);
        HealthList.clearHealthLists();
        outContent.reset();
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
     * Test the behaviour of printing Bmi history.
     */
    @Test
    void showBmiHistory_twoBmiObjects_printsCorrectBmiHistory() {
        Bmi firstBmi = new Bmi("1.75", "80.0", "20-03-2024");
        Bmi secondBmi = new Bmi("1.80", "74.0", "21-03-2024");

        HealthList.addBmi(firstBmi);
        HealthList.addBmi(secondBmi);

        String expected = "2024-03-20"
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

    /**
     * Test deleting of bmi with valid list and valid index.
     * Expected behaviour is to have one bmi entry left in the list.
     *
     * @throws CustomExceptions.OutOfBounds If the index is invalid.
     */
    @Test
    void deleteBmi_properList_listOfSizeOne() throws CustomExceptions.OutOfBounds {
        Bmi firstBmi = new Bmi("1.75", "80.0", "20-03-2024");
        Bmi secondBmi = new Bmi("1.80", "74.0", "21-03-2024");
        HealthList.addBmi(firstBmi);
        HealthList.addBmi(secondBmi);

        int index = 1;
        HealthList.deleteBmi(index);
        assertEquals(1, HealthList.getBmisSize());
    }

    /**
     * Test deleting of bmi with empty list.
     * Expected behaviour is for an AssertionError to be thrown.
     */
    @Test
    void deleteBmi_emptyList_throwsAssertionError() {
        assertThrows(AssertionError.class, () ->
                HealthList.deleteBmi(0));
    }

    /**
     * Test deleting of bmi with invalid index.
     * Expected behaviour is for an OutOfBounds error to be thrown.
     */
    @Test
    void deleteBmi_properListInvalidIndex_throwOutOfBoundsForBmi() {
        Bmi firstBmi = new Bmi("1.75", "80.0", "20-03-2024");
        HealthList.addBmi(firstBmi);
        int invalidIndex = 5;
        assertThrows (CustomExceptions.OutOfBounds.class, () ->
                HealthList.deleteBmi(invalidIndex));
    }
}
