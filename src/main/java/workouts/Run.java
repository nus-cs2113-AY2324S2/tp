package workouts;
import java.time.LocalDate;
import utility.Constant;
import utility.CustomExceptions;
public class Run extends Workout{
    protected Integer[] times;
    protected double distance;
    protected LocalDate date = null;
    protected String pace;
    protected boolean isHourPresent;

    // overloaded constructor for optional date parameter
    public Run(String stringTime, String stringDistance) {
        times = parseTime(stringTime);
        distance = Double.parseDouble(stringDistance);
        pace = calculatePace();
        WorkoutList.addRun(this);
    }

    public Run(String stringTime, String stringDistance, String stringDate) {
        times = parseTime(stringTime);
        distance = Double.parseDouble(stringDistance);
        date = parseDate(stringDate);
        pace = calculatePace();
        WorkoutList.addRun(this);
    }

    /**
     * Returns string format of time taken for run.
     * @return
     */
    public String getTimes() throws CustomExceptions {

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
    public Integer[] parseTime(String inputTime)  {
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
            System.out.println("Incorrect time format!");
            return null;
        }
        return integerTimes;
    }

    /**
     * Method checks if hour has been specified, then returns total seconds.
     * @return The total number of seconds in the run.
     */
    public int calculateTotalSeconds(){
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
        try {
            return String.format(Constant.RUN_FORMAT, Constant.RUN, getTimes(), getDistance(), getPace(), printedDate);
        } catch (CustomExceptions e) {
            throw new RuntimeException(e);
        }
    }



}
