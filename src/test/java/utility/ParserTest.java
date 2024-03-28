package utility;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ParserTest {

    /**
     * Tests the behaviour of the parseDate function with a correctly formatted string.
     */
    @Test
    void parseDate_correctDateInput_returnDate() {
        LocalDate result = Parser.parseDate("08-03-2024");
        LocalDate expected = LocalDate.of(2024, 3, 8);
        assertEquals(expected, result);
    }

    /**
     * Tests the behaviour of the parseDate function with an incorrectly formatted string.
     */
    @Test
    void parseDate_incorrectDateInput_throwException () {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));
        String invalidDate = "2024-03-08";
        Parser.parseDate(invalidDate);
        String errorMessage = outputStream.toString().trim();
        assertTrue(errorMessage.contains("Error parsing date"));
        System.setErr(System.err);
    }

    @Test
    public void validateDateInput_validDate_noExceptionThrown() {
        String validDate = "09-11-2024";
        assertDoesNotThrow(() -> {
            Parser.validateDateInput(validDate);
        });
    }

    @Test
    public void validateDateInput_invalidDayFormat_throwInvalidInputException() {
        String invalidDate = "9-11-2024";

        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_invalidMonthFormat_throwInvalidInputException() {
        String invalidDate = "9-1-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_invalidYearFormat_throwInvalidInputException() {
        String invalidDate = "9-11-24";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_illegalDayNumber_throwInvalidInputException() {
        String invalidDate = "32-11-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_zeroDayNumber_throwInvalidInputException() {
        String invalidDate = "00-11-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_illegalMonthNumber_throwInvalidInputException() {
        String invalidDate = "09-13-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_zeroMonthNumber_throwInvalidInputException() {
        String invalidDate = "09-00-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_wrongDateDelimiter_throwInvalidInputException() {
        String invalidDate = "09/12/2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_invalidDateParameters_throwInvalidInputException() {
        String invalidDate = "09/12";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    //@@author j013n3
    /**
     * Tests the behaviour of a correctly formatted user input being passed into splitBmi.
     * Expects no exception to be thrown.
     */
    @Test
    void splitBmi_correctInput_returnsCorrectBmiValues() throws CustomExceptions.InvalidInput {
        String input = "/h:bmi /height:1.71 /weight:60.50 /date:19-03-2024";
        String[] expected = {"1.71", "60.50", "19-03-2024"};
        String[] result = Parser.splitBmiInput(input);
        assertArrayEquals(expected, result);
    }

    /**
     * Tests the behaviour of a string with missing parameter being passed into splitBmi.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void splitBmi_missingParameter_throwsInvalidInputException() {
        String input = "/h:bmi /height:1.71 /date:19-03-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.splitBmiInput(input));
    }
    //@@author

    /**
     * Tests the behaviour of 1 decimal point weight number being passed into splitBmi.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateBmi_oneDecimalPointWeight_throwsInvalidInputException() {
        String[] input = {"1.71", "70.0", "29-04-2024"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validateBmiInput(input));
    }

    /**
     * Tests the behaviour of 1 decimal point height number being passed into splitBmi.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateBmi_oneDecimalPointHeight_throwsInvalidInputException() {
        String[] input = {"1.7", "70.03", "29-04-2024"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validateBmiInput(input));
    }

    /**
     * Tests the behaviour of a date far in the future is passed into splitBmi.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateBmi_dateAfterToday_throwsInvalidInputException() {
        String[] input = {"1.70", "70.03", "28-03-2025"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validateBmiInput(input));
    }

    /**
     * Tests the behaviour of a correctly formatted string being passed into splitPeriod.
     * Expects no exception to be thrown.
     */
    @Test
    void splitPeriod_correctInput_noExceptionThrown() throws CustomExceptions.InvalidInput {
        String input = "/h:period /start:29-04-2023 /end:30-04-2023";
        String[] expected = {"29-04-2023", "30-04-2023"};
        String[] result = Parser.splitPeriodInput(input);
        assertArrayEquals(expected, result);
    }

    /**
     * Tests the behaviour of a string with a missing parameter being passed into splitPeriod.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void splitPeriod_missingParameter_throwsInvalidInputException() {
        String input = "/h:period /start:29-04-2023";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.splitPeriodInput(input));
    }

    /**
     * Tests the behaviour of correct parameters being passed into validatePeriod.
     * Expects no exception to be thrown.
     */
    @Test
    void validatePeriod_correctParameters_noExceptionThrown()  {
        String [] input = {"23-03-2024", "30-03-2024"};
        assertDoesNotThrow(() -> Parser.validatePeriodInput(input));
    }

    /**
     * Tests the behaviour of a string with an empty string being passed into validatePeriod.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validatePeriod_emptyParameter_throwsInvalidInputException() {
        String [] input = {"", "29-03-2024"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validatePeriodInput(input));
    }

    /**
     * Tests the behaviour of a string with invalid start date being passed into validatePeriod.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validatePeriod_invalidStartDate_throwsInvalidInputException() {
        String [] input = {"32-04-2024", "29-04-2024"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validatePeriodInput(input));
    }

    /**
     * Tests the behaviour of a string with invalid end date being passed into validatePeriod.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validatePeriod_invalidEndDate_throwsInvalidInputException() {
        String [] input = {"28-04-2024", "29-13-2024"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validatePeriodInput(input));
    }

    /**
     * Tests the behaviour of a start date far in the future being passed into validatePeriod.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validatePeriod_dateAfterToday_throwsInvalidInputException() {
        String [] input = {"28-04-2025", "29-13-2025"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validatePeriodInput(input));
    }

    /**
     * Tests the behaviour of an end date before the start date being passed into validatePeriod.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validatePeriod_endDateBeforeStartDate_throwsInvalidInputException() {
        String [] input = {"28-03-2024", "22-03-2024"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validatePeriodInput(input));
    }

    /**
     * Tests the behaviour of a correctly formatted string being passed into splitAppointment.
     * Expects no exception to be thrown.
     */
    @Test
    void splitAppointment_correctInput_noExceptionThrown() throws CustomExceptions.InvalidInput {
        String input = "/h:appointment /date:30-03-2024 /time:19:30 /description:test";
        String[] expected = {"30-03-2024", "19:30", "test"};
        String[] result = Parser.splitAppointmentDetails(input);
        assertArrayEquals(expected, result);
    }

    /**
     * Tests the behaviour of a correctly formatted string being passed into splitAppointment.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void splitAppointment_missingParameter_throwsInvalidInputException() {
        String input = "/h:appointment /date:30-03-2024 /description:test";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.splitAppointmentDetails(input));
    }

    /**
     * Tests the behaviour of correct parameters being passed into validateAppointment.
     * Expects no exception to be thrown.
     */
    @Test
    void validateAppointment_correctParameters_noExceptionThrown() {
        String[] input = {"29-04-2024", "19:30", "test description"};
        assertDoesNotThrow(() -> Parser.validateAppointmentDetails(input));
    }

    /**
     * Tests the behaviour of an empty string being passed into validateAppointment.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateAppointment_emptyParameters_throwsInvalidInputException() {
        String[] input = {"29-04-2024", "19:30", ""};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validateAppointmentDetails(input));
    }

    /**
     * Tests the behaviour of an incorrectly formatted date being passed into validateAppointment.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateAppointment_incorrectDateFormat_throwsInvalidInputException() {
        String[] input = {"32-04-2024", "19:30", "test description"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validateAppointmentDetails(input));
    }

    /**
     * Tests the behaviour of an incorrectly formatted time being passed into validateAppointment.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateAppointment_incorrectTimeFormat_throwsInvalidInputException() {
        String[] input = {"28-04-2024", "25:30", "test description"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validateAppointmentDetails(input));
    }

    /**
     * Tests the behaviour of a description being more than 100 characters being passed into validateAppointment.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateAppointment_descriptionTooLong_throwsInvalidInputException() {
        String[] input = {"28-04-2024", "22:30",
                          "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" +
                          "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validateAppointmentDetails(input));
    }

    /**
     * Tests the behaviour of a correctly formatted time string being passed into validateTimeInput.
     * Expects no exception to be thrown.
     */
    @Test
    void validateTimeInput_correctInput_noExceptionThrown() {
        String input = "23:50";
        assertDoesNotThrow(() -> Parser.validateTimeInput(input));
    }

    /**
     * Tests the behaviour of an incorrect time with the wrong delimiter being passed into validateTimeInput.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateTimeInput_invalidDelimiter_throwsInvalidInputException() {
        String input = "23-50";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validateTimeInput(input));
    }

    /**
     * Tests the behaviour of an incorrect time with invalid hours being passed into validateTimeInput.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateTimeInput_invalidHours_throwsInvalidInputException() {
        String input = "24:50";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validateTimeInput(input));
    }

    /**
     * Tests the behaviour of an incorrect time with invalid minutes being passed into validateTimeInput.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateTimeInput_invalidMinutes_throwsInvalidInputException() {
        String input = "21:60";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validateTimeInput(input));
    }

    /**
     * Tests the behaviour of an incorrect time with letters being passed into validateTimeInput.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateTimeInput_invalidTimeWithLetters_throwsInvalidInputException() {
        String input = "12:2a";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validateTimeInput(input));
    }


    /**
     * Tests the behaviour of an incorrect time with seconds included being passed into validateTimeInput.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateTimeInput_invalidTimeFormat_throwsInvalidInputException() {
        String input = "21:55:44";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Parser.validateTimeInput(input));
    }

    /**
     * Tests the behaviour of the extractSubstringFromSpecificIndex with a correct flag.
     * Expects the 'bmi' string to be extracted.
     */
    @Test
    void extractSubstringFromSpecificIndex_correctFlag_returnsCorrectSubstring() {
        String test = "/h:bmi";
        String testDelimiter = "/h:";
        String result = Parser.extractSubstringFromSpecificIndex(test, testDelimiter);
        String expected = "bmi";
        assertEquals(expected, result);
    }
}
