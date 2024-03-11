package exercise;
import java.time.LocalDate;

public class Run extends Workout{
    protected static final Integer MAX_TIME_ARRAY_LENGTH = 3;
    protected static final Integer MIN_TIME_ARRAY_LENGTH = 2;
    protected Integer[] times;
    protected double distance;
    protected static LocalDate date = null;
    protected String pace;

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

    public String getTimes() {
        if (times.length == MIN_TIME_ARRAY_LENGTH) {
            // hh:mm:ss
            return times[0] + ":" + times[1] + ":" + times[2];
        }
        else if (times.length == MAX_TIME_ARRAY_LENGTH) {
            // mm:ss
            return times[0] + ":" + times[1];
        }
        System.err.println("Invalid time format");
        return null;
    }

    public double getDistance() {
        return distance;
    }

    public String getPace() {
        return pace;
    }

    public static Integer[] parseTime(String inputTime) {
        String[] stringTimeParts = inputTime.split(":");
        int inputLength = stringTimeParts.length;
        Integer[] integerTimes = new Integer[MAX_TIME_ARRAY_LENGTH];

        if (inputLength == MAX_TIME_ARRAY_LENGTH) {
            integerTimes[0] = Integer.parseInt(stringTimeParts[0]);
            integerTimes[1] = Integer.parseInt(stringTimeParts[1]);
            integerTimes[2] = Integer.parseInt(stringTimeParts[2]);
        }
        else if (inputLength == MIN_TIME_ARRAY_LENGTH) {
            integerTimes[0] = Integer.parseInt(stringTimeParts[0]);
            integerTimes[1] = Integer.parseInt(stringTimeParts[1]);
        }
        else {
            System.err.println("Incorrect time format!");
            return null;
        }
        return integerTimes;
    }

    public int calculateTotalSeconds(){
        int timeLength = times.length;
        int totalSeconds;

        if (timeLength == MAX_TIME_ARRAY_LENGTH) {
            totalSeconds = times[0] * 3600 + times[1] * 60  + times[2];
        }

        else if (timeLength == MIN_TIME_ARRAY_LENGTH) {
            totalSeconds = times[0] * 60 + times[1];
        }
        else {
            System.err.println("Incorrect time format!");
            return 0;
        }
        return totalSeconds;
    }

    public String calculatePace() {
        int totalSeconds = calculateTotalSeconds();
        double paceInDecimal = ((double) totalSeconds / distance) / 60;

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

        String runString = "run \t";
        runString += getTimes() + "\t\t" + getDistance() + "\t\t" + getPace();
        return  runString;
    }

}
