package health;

import utility.Parser;
import utility.CustomExceptions;
import utility.ErrorConstant;
import utility.HealthConstant;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Represents a Period object to track user's menstrual cycle.
 */
public class Period extends Health {
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected long length;

    //@@author syj02
    /**
     * Constructs a Period object with the given start and end dates in string format.
     *
     * @param stringStartDate A string representing the start date of the period
     * @param stringEndDate   A string representing the end date of the period
     */
    public Period(String stringStartDate, String stringEndDate) {
        this.startDate = Parser.parseDate(stringStartDate);
        this.endDate = Parser.parseDate(stringEndDate);
        this.length = calculatePeriodLength();
    }

    /**
     * Retrieves the start date of the period.
     *
     * @return The start date.
     * @throws AssertionError if the start date is null
     */
    public LocalDate getStartDate() {
        assert startDate != null : HealthConstant.START_DATE_CANNOT_BE_NULL;
        return startDate;
    }

    /**
     * Retrieves the end date of the period.
     *
     * @return The end date.
     * @throws AssertionError if the end date is null
     */
    public LocalDate getEndDate() {
        assert endDate != null : HealthConstant.END_DATE_CANNOT_BE_NULL;
        return endDate;
    }

    /**
     * Extracts the period information from the user input string.
     *
     * @param input A string consisting of period information
     * @return An array of strings containing the appropriate health command, start date, and end date
     * @throws CustomExceptions.InvalidInput if the input string does not contain the required parameters
     */
    public static String[] getPeriod(String input) throws CustomExceptions.InvalidInput {
        String[] results = new String[HealthConstant.PERIOD_CYCLE_PARAMETERS];

        if (!input.contains(HealthConstant.HEALTH_FLAG)
                | !input.contains(HealthConstant.START_FLAG)
                || !input.contains(HealthConstant.END_FLAG)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.UNSPECIFIED_PARAMETER_ERROR);
        }

        int indexH = input.indexOf(HealthConstant.HEALTH_FLAG);
        int indexStart = input.indexOf(HealthConstant.START_FLAG);
        int indexEnd = input.indexOf(HealthConstant.END_FLAG);

        String command = input.substring(indexH + HealthConstant.PERIOD_CYCLE_H_OFFSET, indexStart).trim();
        String startSubstring = input.substring(indexStart + HealthConstant.PERIOD_CYCLE_START_OFFSET, indexEnd).trim();
        String endSubstring = input.substring(indexEnd + HealthConstant.PERIOD_CYCLE_END_OFFSET).trim();

        if (command.isEmpty() || startSubstring.isEmpty() || endSubstring.isEmpty()) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.UNSPECIFIED_PARAMETER_ERROR);
        }

        results[0] = command;
        results[1] = startSubstring;
        results[2] = endSubstring;

        return results;
    }

    /**
     * Calculates the length of the period in days.
     *
     * @return The length of the period.
     */
    public long calculatePeriodLength() {
        assert startDate.isBefore(endDate) : HealthConstant.PERIOD_START_MUST_BE_BEFORE_END;
        // Add 1 to include both start and end dates
        return ChronoUnit.DAYS.between(startDate,endDate) + 1;
    }

    /**
     * Retrieves the string representation of a Period object.
     *
     * @return A formatted string representing a Period object.
     */
    @Override
    public String toString() {
        String startDateString = startDate.toString();
        String endDateString = endDate.toString();
        return String.format(HealthConstant.PRINT_PERIOD_FORMAT, startDateString, endDateString, length);
    }
}
