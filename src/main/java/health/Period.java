package health;

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
    /**
     * The start date of period i.e. the first day of period flow which is also the first day of cycle.
     */
    protected LocalDate startDate;
    /**
     * The end date of period i.e. the last day of period flow.
     */
    protected LocalDate endPeriodDate;
    /**
     * The number of days between the first day and last day of period flow.
     */
    protected long periodLength;
    /**
     * The number of days between the first day and last day of the period cycle.
     */
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

        int startIndexForPrediction = size - HealthConstant.MIN_SIZE_FOR_PREDICTION;
        assert startIndexForPrediction >= 0 : ErrorConstant.START_INDEX_NEGATIVE_ERROR;

        int endIndexForPrediction = size - HealthConstant.LAST_CYCLE_INDEX_OFFSET;
        assert endIndexForPrediction >= startIndexForPrediction : ErrorConstant.END_INDEX_GREATER_THAN_START_ERROR;

        for (int i = startIndexForPrediction; i <= endIndexForPrediction; i++) {
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
