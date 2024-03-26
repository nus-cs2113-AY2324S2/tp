package health;

import utility.CustomExceptions;
import utility.ErrorConstant;
import utility.HealthConstant;
import utility.Parser;
import utility.UiConstant;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Represents a Period object to track user's menstrual cycle.
 */
public class Period extends Health {
    protected LocalDate startDate;
    protected LocalDate endPeriodDate;
    protected LocalDate endCycleDate;
    protected long periodLength;
    protected long cycleLength;

    //@@author syj02
    /**
     * Constructs a Period object with the given start and end dates in string format.
     *
     * @param stringStartDate A string representing the start date of the period
     * @param stringEndDate   A string representing the end date of the period
     */
    public Period(String stringStartDate, String stringEndDate) {
        this.startDate = Parser.parseDate(stringStartDate);
        this.endPeriodDate = Parser.parseDate(stringEndDate);
        this.endCycleDate = null;
        this.periodLength = calculatePeriodLength();
        this.cycleLength = 0;

    }

    /**
     * Retrieves the start date of the period.
     *
     * @return The start date.
     * @throws AssertionError if the start date is null
     */
    public LocalDate getStartDate() {
        assert startDate != null : ErrorConstant.NULL_START_DATE_ERROR;
        return startDate;
    }

    /**
     * Retrieves the end date of the period.
     *
     * @return The end date.
     * @throws AssertionError if the end date is null
     */
    public LocalDate getEndDate() {
        assert endPeriodDate != null : ErrorConstant.NULL_END_DATE_ERROR;
        return endPeriodDate;
    }

    /**
     * Extracts the period information from the user input string.
     *
     * @param input A string consisting of period information
     * @return An array of strings containing the appropriate health command, start date, and end date
     * @throws CustomExceptions.InvalidInput if the input string does not contain the required parameters
     */
    public static String[] getPeriod(String input) throws CustomExceptions.InvalidInput {
        String[] results = new String[HealthConstant.PERIOD_PARAMETERS];

        if (!input.contains(HealthConstant.HEALTH_FLAG)
                | !input.contains(HealthConstant.START_FLAG)
                || !input.contains(HealthConstant.END_FLAG)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.UNSPECIFIED_PARAMETER_ERROR);
        }

        int indexH = input.indexOf(HealthConstant.HEALTH_FLAG);
        int indexStart = input.indexOf(HealthConstant.START_FLAG);
        int indexEnd = input.indexOf(HealthConstant.END_FLAG);

        String command = input.substring(indexH + HealthConstant.H_OFFSET, indexStart).trim();
        String startSubstring = input.substring(indexStart + HealthConstant.START_DATE_OFFSET, indexEnd).trim();
        String endSubstring = input.substring(indexEnd + HealthConstant.END_DATE_OFFSET).trim();

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
        assert startDate.isBefore(endPeriodDate) : ErrorConstant.PERIOD_END_BEFORE_START_ERROR;
        // Add 1 to include both start and end dates
        return ChronoUnit.DAYS.between(startDate,endPeriodDate) + 1;
    }

    /**
     * Sets the cycle length of the current period based on the start date of the next period.
     *
     * @param nextStartDate The start date of the next period.
     */
    public void setCycleLength(LocalDate nextStartDate) {
        this.cycleLength = ChronoUnit.DAYS.between(startDate, nextStartDate);
    }


    /**
     * Calculates the sum of the cycle lengths of the latest three menstrual cycles.
     *
     * @return The sum of the cycle lengths of the latest three menstrual cycles.
     */
    public long getLastThreeCycleLengths() {
        int size = HealthList.getPeriodSize();


        long sumOfCycleLengths = 0;
        for (int i = size - 4; i <= size - 2; i++) {
            sumOfCycleLengths += HealthList.getPeriod(i).cycleLength;
        }
        return sumOfCycleLengths;
    }


    /**
     * Predicts the start date of the next period based on the average cycle length.
     *
     * @return The predicted start date of the next period.
     */
    public LocalDate nextCyclePrediction() {
        long averageCycleLength = getLastThreeCycleLengths() / HealthConstant.LATEST_THREE_CYCLE_LENGTHS;
        return this.startDate.plusDays(averageCycleLength);
    }

    /**
     * Prints a message indicating the number of days until the predicted start date of the next period,
     * or how many days late the period is if the current date is after the predicted start date.
     *
     * @param nextPeriodStartDate The predicted start date of the next period.
     */
    public static void printNextCyclePrediction(LocalDate nextPeriodStartDate) {
        LocalDate today = LocalDate.now();
        long daysUntilNextPeriod = today.until(nextPeriodStartDate, ChronoUnit.DAYS);
        if (today.isBefore(nextPeriodStartDate)) {
            System.out.println(HealthConstant.PREDICTED_START_DATE_MESSAGE
                    + nextPeriodStartDate +
                    HealthConstant.COUNT_DAYS_MESSAGE
                    + daysUntilNextPeriod
                    + HealthConstant.DAYS_MESSAGE);
        }
        if (today.isAfter(nextPeriodStartDate)) {
            System.out.println(HealthConstant.PREDICTED_START_DATE_MESSAGE
                    + nextPeriodStartDate
                    + HealthConstant.PERIOD_IS_LATE
                    + -daysUntilNextPeriod
                    + HealthConstant.DAYS_MESSAGE);
        }
    }

    /**
     * Retrieves the string representation of a Period object.
     *
     * @return A formatted string representing a Period object.
     */
    @Override
    public String toString() {
        return String.format(HealthConstant.PRINT_PERIOD_FORMAT,
                getStartDate(),
                getEndDate(),
                this.periodLength)
                + (this.cycleLength > 0 ? System.lineSeparator()
                + String.format(HealthConstant.PRINT_CYCLE_FORMAT, this.cycleLength) : UiConstant.EMPTY_STRING);
    }
}
