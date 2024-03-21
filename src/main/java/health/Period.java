package health;

import utility.UiConstant;
import utility.CustomExceptions;
import utility.Parser;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Represents a Period object to track user's menstrual cycle.
 */
public class Period extends Health {
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected long length;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Extracts the period information from the user input string.
     *
     * @param input A string consisting of period information
     * @return An array of strings containing the appropriate health command, start date, and end date
     * @throws CustomExceptions.InvalidInput if the input string does not contain the required parameters
     */
    public static String[] getPeriod(String input) throws CustomExceptions.InvalidInput {
        String[] results = new String[UiConstant.PERIOD_CYCLE_PARAMETERS];

        if (!input.contains(UiConstant.HEALTH_FLAG)
                | !input.contains(UiConstant.START_FLAG)
                || !input.contains(UiConstant.END_FLAG)) {
            throw new CustomExceptions.InvalidInput(UiConstant.MISSING_PARAMETERS);
        }

        int indexH = input.indexOf(UiConstant.HEALTH_FLAG);
        int indexStart = input.indexOf(UiConstant.START_FLAG);
        int indexEnd = input.indexOf(UiConstant.END_FLAG);

        String command = input.substring(indexH + UiConstant.PERIOD_CYCLE_H_OFFSET, indexStart).trim();
        String startSubstring = input.substring(indexStart + UiConstant.PERIOD_CYCLE_START_OFFSET, indexEnd).trim();
        String endSubstring = input.substring(indexEnd + UiConstant.PERIOD_CYCLE_END_OFFSET).trim();

        if (command.isEmpty() || startSubstring.isEmpty() || endSubstring.isEmpty()) {
            throw new CustomExceptions.InvalidInput(UiConstant.MISSING_PARAMETERS);
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
        return "Period Start: "
                + this.getStartDate()
                + " Period End: "
                + this.endDate
                + System.lineSeparator()
                + "Period Length: "
                + this.length
                + " days";
    }
}
