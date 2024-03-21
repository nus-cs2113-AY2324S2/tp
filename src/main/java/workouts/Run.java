package workouts;
import java.time.LocalDate;

import ui.Handler;
import utility.Parser;
import utility.UiConstant;
import utility.CustomExceptions;

/**
 * Represents a Run object.
 */
public class Run extends Workout{
    protected Integer[] times;
    protected double distance;
    protected LocalDate date = null;
    protected String pace;
    protected boolean isHourPresent;

    /**
     * Constructs a new Run object with the time and distance from user input.
     *
     * @param stringTime The time taken for the run.
     * @param stringDistance The distance of the run.
     * @throws CustomExceptions.InvalidInput If there is invalid input.
     */
    public Run(String stringTime, String stringDistance) throws CustomExceptions.InvalidInput {
        times = parseTime(stringTime);
        distance = Double.parseDouble(stringDistance);
        pace = calculatePace();
        WorkoutList.addRun(this);
    }

    /**
     * Overloaded constructor that takes in time, distance and the optional date parameter from user input.
     *
     * @param stringTime The time taken for the run.
     * @param stringDistance The distance of the run.
     * @param stringDate The date of the run.
     * @throws CustomExceptions.InvalidInput If there is invalid input.
     */
    public Run(String stringTime, String stringDistance, String stringDate) throws CustomExceptions.InvalidInput {
        times = parseTime(stringTime);
        distance = Double.parseDouble(stringDistance);
        date = Parser.parseDate(stringDate);
        pace = calculatePace();
        WorkoutList.addRun(this);
    }

    /**
     * Parses a string containing run information, extracts the command, distance and end time before returning
     * an array of strings containing the information.
     *
     * @param input A string containing the Run information in the format "new /e:run /d:DISTANCE /t:TIME [/date:DATE]".
     * @return An array of strings containing the extracted command, distance, time taken and date(if given).
     */
    public static String[] getRun(String input) throws CustomExceptions.InvalidInput {

        String[] results = new String[UiConstant.NUMBER_OF_RUN_PARAMETERS];


        if (!input.contains("/e:") || !input.contains("/d:") || !input.contains("/t:")) {
            throw new CustomExceptions.InvalidInput(UiConstant.UNSPECIFIED_PARAMETER);
        }
        // Command
        results[UiConstant.SUBSTRING_COMMAND] = Handler.extractSubstringFromSpecificIndex(input, "/e:");
        // Distance
        results[UiConstant.SUBSTRING_DISTANCE] = Handler.extractSubstringFromSpecificIndex(input, "/d:");
        // Time
        results[UiConstant.SUBSTRING_TIME] = Handler.extractSubstringFromSpecificIndex(input, "/t:");
        // Date
        results[UiConstant.SUBSTRING_DATE] = Handler.extractSubstringFromSpecificIndex(input, "/date:");

        assert !results[UiConstant.SUBSTRING_COMMAND].isEmpty() : "Command should not be empty";
        assert !results[UiConstant.SUBSTRING_DISTANCE].isEmpty() : "Distance should not be empty";
        assert results[UiConstant.SUBSTRING_DISTANCE].matches("\\d+(\\.\\d+)?") :
                "Distance should be a valid numeric " + "value (assuming KM)";
        assert !results[UiConstant.SUBSTRING_TIME].isEmpty() : "Time should not be empty";
        assert results[UiConstant.SUBSTRING_TIME].matches("\\d{2}:\\d{2}:\\d{2}") : "Time should be " +
                "in the format " + "HH:MM:SS";

        return results;
    }

    /**
     * Returns string format of time taken for run.
     *
     * @return Formatted string of the time for the run.
     */
    public String getTimes()  {
        if (isHourPresent) {
            return times[0] + ":" + times[1] + ":" + times[2];
        } else {
            return times[0] + ":" + times[1];
        }
    }

    /**
     * Retrieves run distance.
     *
     * @return Run distance.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Retrieves run pace.
     *
     * @return Run pace.
     */
    public String getPace() {
        return pace;
    }

    /**
     * Method parses the time format in either hh:mm:ss or mm:ss.
     * Sets {@code isHourPresent} variable to true if hours have been specified.
     * Otherwise, set to false.
     *
     * @param inputTime String variable representing time taken in either hh:mm:ss or mm:ss format
     * @return A list of integers representing the hours (if present), minutes and seconds.
     */
    public Integer[] parseTime(String inputTime) throws CustomExceptions.InvalidInput {
        String[] stringTimeParts = inputTime.split(":");
        int inputLength = stringTimeParts.length;
        Integer[] integerTimes = new Integer[inputLength];

        if (inputLength == UiConstant.MAX_RUNTIME_ARRAY_LENGTH) {
            this.isHourPresent = true;
            integerTimes[0] = Integer.parseInt(stringTimeParts[0]);
            integerTimes[1] = Integer.parseInt(stringTimeParts[1]);
            integerTimes[2] = Integer.parseInt(stringTimeParts[2]);
        } else if (inputLength == UiConstant.MIN_RUNTIME_ARRAY_LENGTH) {
            this.isHourPresent = false;
            integerTimes[0] = Integer.parseInt(stringTimeParts[0]);
            integerTimes[1] = Integer.parseInt(stringTimeParts[1]);
        } else {
            throw new CustomExceptions.InvalidInput(UiConstant.INVALID_RUN_TIME);
        }
        return integerTimes;
    }

    /**
     * Method checks if hour has been specified, then returns total seconds.
     *
     * @return The total number of seconds in the run.
     */
    public int calculateTotalSeconds() {
        int totalSeconds;

        if (this.isHourPresent) {
            totalSeconds = this.times[0] * UiConstant.NUM_SECONDS_IN_HOUR
                    + this.times[1] * UiConstant.NUM_SECONDS_IN_MINUTE
                    + this.times[2];
        } else {
            totalSeconds = this.times[0] * 60 + this.times[1];
        }
        return totalSeconds;
    }

    /**
     * Method calculates the pace of the run, and formats it into M:SS/km.
     *
     * @return Formatted string the pace of the run.
     */
    public String calculatePace() {
        int totalSeconds = calculateTotalSeconds();
        double paceInDecimal = ((double) totalSeconds / this.distance) / UiConstant.NUM_SECONDS_IN_MINUTE;

        int minutes = (int) paceInDecimal;
        double remainingSeconds = paceInDecimal - minutes;
        int seconds = (int) Math.round(remainingSeconds * UiConstant.NUM_SECONDS_IN_MINUTE);
        return String.format("%d:%02d/km", minutes, seconds);
    }

    /**
     * Retrieves the string representation of a Run object.
     *
     * @return A formatted string representing a Run object.
     */
    @Override
    public String toString() {
        String printedDate;
        if (date != null){
            printedDate = date.toString();
        } else{
            printedDate = UiConstant.NO_DATE_SPECIFIED;
        }
        return String.format(UiConstant.RUN_FORMAT, UiConstant.RUN, getTimes(), getDistance(), getPace(), printedDate);
    }
}
