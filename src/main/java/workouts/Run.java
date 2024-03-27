package workouts;

import java.time.LocalDate;

import ui.Handler;
import utility.CustomExceptions;
import utility.Parser;
import utility.ErrorConstant;
import utility.UiConstant;
import utility.WorkoutConstant;


/**
 * Represents a Run object.
 */
public class Run extends Workout {
    protected Integer[] times;
    protected double distance;
    protected LocalDate date = null;
    protected String pace;
    protected boolean isHourPresent;

    /**
     * Constructs a new Run object with the time and distance from user input.
     *
     * @param stringTime     The time taken for the run.
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
     * @param stringTime     The time taken for the run.
     * @param stringDistance The distance of the run.
     * @param stringDate     The date of the run.
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

        String[] results = new String[WorkoutConstant.NUMBER_OF_RUN_PARAMETERS];


        if (!input.contains("/e:") || !input.contains("/d:") || !input.contains("/t:")) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.UNSPECIFIED_PARAMETER_ERROR);
        }
        // Command
        results[WorkoutConstant.SUBSTRING_COMMAND] = Handler.extractSubstringFromSpecificIndex(input, "/e:");
        // Distance
        results[WorkoutConstant.SUBSTRING_DISTANCE] = Handler.extractSubstringFromSpecificIndex(input, "/d:");
        // Time
        results[WorkoutConstant.SUBSTRING_TIME] = Handler.extractSubstringFromSpecificIndex(input, "/t:");
        // Date
        results[WorkoutConstant.SUBSTRING_DATE] = Handler.extractSubstringFromSpecificIndex(input, "/date:");


        assert !results[WorkoutConstant.SUBSTRING_COMMAND].isEmpty() : "Command should not be empty";
        assert !results[WorkoutConstant.SUBSTRING_DISTANCE].isEmpty() : "Distance should not be empty";
        assert results[WorkoutConstant.SUBSTRING_DISTANCE].matches("\\d+(\\.\\d+)?") :
                "Distance should be a valid numeric " + "value (assuming KM)";
        assert !results[WorkoutConstant.SUBSTRING_TIME].isEmpty() : "Time should not be empty";

        return results;
    }

    public static Run addRun (String[] runDetails) throws CustomExceptions.InvalidInput {
        Run newRun;
        if (runDetails[WorkoutConstant.SUBSTRING_DATE].isEmpty()) {
            newRun = new Run(
                    runDetails[WorkoutConstant.SUBSTRING_TIME],
                    runDetails[WorkoutConstant.SUBSTRING_DISTANCE]);
        } else {
            newRun = new Run(
                    runDetails[WorkoutConstant.SUBSTRING_TIME],
                    runDetails[WorkoutConstant.SUBSTRING_DISTANCE],
                    runDetails[WorkoutConstant.SUBSTRING_DATE]);
        }
        return newRun;
    }

    /**
     * Returns string format of time taken for run.
     *
     * @return Formatted string of the time for the run.
     */
    public String getTimes() {
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
            throw new CustomExceptions.InvalidInput(WorkoutConstant.INVALID_RUN_TIME);
        }
        return integerTimes;
    }

    /**
     * Method checks if Run values is valid
     * Returns {@code true} if {@code runDistance} and {@code runTime} parameters are valid.
     * Valid only if {@code runDistance} is a positive double / not blank / and is digit.
     * {@code runTime} is not blank.
     * {@code runDate} is not blank.
     * Otherwise, throw {@code CustomExceptions.InvalidInput}  or {@code CustomExceptions.InsufficientInput}
     *
     * @param runDistance String representing the distance of the run
     * @param runTime     String representing the time taken for the run
     * @param runDate     String representing the date of the run
     * @return {@code true} if all parameters are valid.
     */
    public static boolean checkIfRunIsValid(String runDistance, String runTime, String runDate)
            throws CustomExceptions.InvalidInput, CustomExceptions.InsufficientInput {

        // Check to make sure there is sufficient input
        if (runDistance.isBlank()) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.RUN_DISTANCE_EMPTY_ERROR);
        }

        if (runTime.isBlank()) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.RUN_TIME_EMPTY_ERROR);
        }

        // Check to see if distance is a double
        try {
            double value = Double.parseDouble(runDistance);
            if (value <= 0) {
                throw new CustomExceptions.InvalidInput(ErrorConstant.RUN_DISTANCE_POSITIVE_ERROR);
            }
        } catch (NumberFormatException e) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.RUN_DISTANCE_DOUBLE_ERROR);
        }

        // Check to see if time is valid
        checkIfTimeIsValid(runTime);

        // Check to see if date is valid
        if (!runDate.isBlank()) {
            Parser.validateDateInput(runDate);
        }
        return true;
    }

    public static void checkIfTimeIsValid(String inputTime) throws CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {
        String[] stringTimeParts = inputTime.split(UiConstant.SPLIT_BY_COLON);
        int inputLength = stringTimeParts.length;
        int hours = 0;
        int minute;
        int seconds;

        // if it is neither in MM:SS nor HH:MM:SS format
        if (inputLength != UiConstant.MAX_RUNTIME_ARRAY_LENGTH && inputLength != UiConstant.MIN_RUNTIME_ARRAY_LENGTH) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.RUN_TIME_INVALID_FORMAT_ERROR);
        }

        // Check if the value provided is an integer
        try {
            if (inputLength == UiConstant.MAX_RUNTIME_ARRAY_LENGTH) {
                hours = Integer.parseInt(stringTimeParts[0]);
                minute = Integer.parseInt(stringTimeParts[1]);
                seconds = Integer.parseInt(stringTimeParts[2]);
            } else {
                minute = Integer.parseInt(stringTimeParts[0]);
                seconds = Integer.parseInt(stringTimeParts[1]);
            }
        } catch (NumberFormatException e) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.RUN_TIME_INTEGER_ERROR);
        }

        // Check if hour is within the range
        if (hours < 0 || hours > 23) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.RUN_TIME_HOURS_RANGE_ERROR);
        }

        // Check if minute is within the range
        if (minute < 0 || minute > 59) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.RUN_TIME_MINUTES_RANGE_ERROR);
        }

        // Check if seconds is within the range
        if (seconds < 0 || seconds > 59) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.RUN_TIME_SECONDS_RANGE_ERROR);
        }

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
        if (date != null) {
            printedDate = date.toString();
        } else {
            printedDate = ErrorConstant.NO_DATE_SPECIFIED_ERROR;
        }
        return String.format(WorkoutConstant.RUN_FORMAT, WorkoutConstant.RUN,
                getTimes(), getDistance(), getPace(), printedDate);
    }
}
