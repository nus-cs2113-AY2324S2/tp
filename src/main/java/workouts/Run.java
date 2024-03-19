package workouts;
import java.time.LocalDate;

import ui.Handler;
import utility.Parser;
import utility.Constant;
import utility.CustomExceptions;
public class Run extends Workout{
    protected Integer[] times;
    protected double distance;
    protected LocalDate date = null;
    protected String pace;
    protected boolean isHourPresent;

    // overloaded constructor for optional date parameter
    public Run(String stringTime, String stringDistance) throws CustomExceptions.InvalidInput {
        times = parseTime(stringTime);
        distance = Double.parseDouble(stringDistance);
        pace = calculatePace();
        WorkoutList.addRun(this);
    }

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

        String[] results = new String[4]; // Constant.RUN_PARAMETERS = 4


        if (!input.contains("/e:") || !input.contains("/d:") || !input.contains("/t:")) {
            throw new CustomExceptions.InvalidInput(Constant.UNSPECIFIED_PARAMETER);
        }

        results[Constant.SUBSTRING_COMMAND] = Handler.extractSubstringFromSpecificIndex(input, "/e:"); // Command
        results[Constant.SUBSTRING_DISTANCE] = Handler.extractSubstringFromSpecificIndex(input, "/d:"); // Distance
        results[Constant.SUBSTRING_TIME] = Handler.extractSubstringFromSpecificIndex(input, "/t:"); // Time
        results[Constant.SUBSTRING_DATE] = Handler.extractSubstringFromSpecificIndex(input, "/date:"); // Date

        // Assert and validate the extracted values

        assert !results[Constant.SUBSTRING_COMMAND].isEmpty() : "Command should not be empty";
        assert !results[Constant.SUBSTRING_DISTANCE].isEmpty() : "Distance should not be empty";
        assert results[Constant.SUBSTRING_DISTANCE].matches("\\d+(\\.\\d+)?") : "Distance should be a valid numeric " +
                "value (assuming KM)";
        assert !results[Constant.SUBSTRING_TIME].isEmpty() : "Time should not be empty";
        assert results[Constant.SUBSTRING_TIME].matches("\\d{2}:\\d{2}:\\d{2}") : "Time should be in the format " +
                "HH:MM:SS";

        return results;
    }

    /**
     * Returns string format of time taken for run.
     * @return
     */
    public String getTimes()  {
        if (isHourPresent) {
            return times[0] + ":" + times[1] + ":" + times[2];
        } else {
            return times[0] + ":" + times[1];
        }
    }

    public double getDistance() {
        return distance;
    }

    public String getPace() {
        return pace;
    }

    /**
     * Method parses the time format in either hh:mm:ss or mm:ss.
     * Sets {@code isHourPresent} variable to true if hours have been specified.
     * Otherwise, set to false.
     * @param inputTime String variable representing time taken in either hh:mm:ss or mm:ss format
     * @return A list of integers representing the hours (if present), minutes and seconds.
     */
    public Integer[] parseTime(String inputTime) throws CustomExceptions.InvalidInput {
        String[] stringTimeParts = inputTime.split(":");
        int inputLength = stringTimeParts.length;
        Integer[] integerTimes = new Integer[inputLength];

        if (inputLength == Constant.MAX_RUNTIME_ARRAY_LENGTH) {
            this.isHourPresent = true;
            integerTimes[0] = Integer.parseInt(stringTimeParts[0]);
            integerTimes[1] = Integer.parseInt(stringTimeParts[1]);
            integerTimes[2] = Integer.parseInt(stringTimeParts[2]);
        } else if (inputLength == Constant.MIN_RUNTIME_ARRAY_LENGTH) {
            this.isHourPresent = false;
            integerTimes[0] = Integer.parseInt(stringTimeParts[0]);
            integerTimes[1] = Integer.parseInt(stringTimeParts[1]);
        } else {
            throw new CustomExceptions.InvalidInput(Constant.INVALID_RUN_TIME);
        }
        return integerTimes;
    }

    /**
     * Method checks if hour has been specified, then returns total seconds.
     * @return The total number of seconds in the run.
     */
    public int calculateTotalSeconds() {
        int totalSeconds;

        if (this.isHourPresent) {
            totalSeconds = this.times[0] * 3600 + this.times[1] * 60  + this.times[2];
        } else {
            totalSeconds = this.times[0] * 60 + this.times[1];
        }
        return totalSeconds;
    }

    /**
     * Method calculates the pace of the run.
     * @return
     */
    public String calculatePace() {
        int totalSeconds = calculateTotalSeconds();
        double paceInDecimal = ((double) totalSeconds / this.distance) / 60;

        int minutes = (int) paceInDecimal;
        double remainingSeconds = paceInDecimal - minutes;
        int seconds = (int) Math.round(remainingSeconds * 60);
        return String.format("%d:%02d/km", minutes, seconds);
    }

    /**
     * Method overrides the Workout toString() for specific run formatting
     * run  mm:ss  distance  pace
     * e.g. run  30:10   60:3   30:01/km
     */
    @Override
    public String toString() {
        String printedDate;
        if (date != null){
            printedDate = date.toString();
        } else{
            printedDate = Constant.NO_DATE_SPECIFIED;
        }
        return String.format(Constant.RUN_FORMAT, Constant.RUN, getTimes(), getDistance(), getPace(), printedDate);
    }



}
