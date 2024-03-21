package ui;

import utility.UiConstant;
import utility.CustomExceptions;
import utility.Filters;
import workouts.Gym;
import workouts.GymStation;
import workouts.Run;
import workouts.Workout;
import workouts.WorkoutList;
import health.HealthList;
import java.util.ArrayList;

public class Output {

    /**
     * Prints a horizontal line.
     */
    public static void printLine() {
        System.out.println(UiConstant.PARTITION_LINE);
    }

    /**
     * Prints the help message.
     */
    public static void printHelp() {
        printLine();
        System.out.println("Commands List:");
        System.out.println();
        System.out.println("new /e:run /d:DISTANCE /t:TIME [/date:DATE] - Add a new run");
        System.out.println("new /e:gym /n:NUMBER_OF_STATIONS [/date:DATE] - Add a new gym workout");
        System.out.println("health /h:bmi /height:HEIGHT /weight:WEIGHT /date:DATE - Add new BMI data");
        System.out.println("health /h:period /start:START_DATE /end:END_DATE - Add new period data");
        System.out.println("history /view:[run/gym/bmi/period] - " +
                "Show history of runs/gyms/bmi records/periods tracked");
        System.out.println("latest /view:[run/gym/bmi/period] - " +
                "Show history of runs/gyms/bmi records/periods tracked");
        System.out.println("help - Show this help message");
        System.out.println("exit - Exit the program");
        printLine();
    }

    /**
     * Prints an ASCII Art depicting the word 'PulsePilot'.
     */
    public static void printArt() {
        System.out.println(" _              _");
        System.out.println("|_)    |  _  _ |_) o  |  _ _|_");
        System.out.println("|  |_| | _> (/_|   |  | (_) |_");
    }

    /**
     * Prints the gym station prompt.
     * @param stationNumber Integer representing the current gym station index.
     */
    public static void printGymStationPrompt(int stationNumber) {
        printLine();
        System.out.println("Please enter the details of station "
                + stationNumber
                + ". (Format: " + UiConstant.STATION_GYM_FORMAT + ")");
        printLine();
    }


    /**
     * Returns the formatted string for printing runs.
     *
     * @param index The index of the run.
     * @param currentWorkout The current Workout object within the list.
     * @return A string
     */
    private static String getFormattedRunWithIndex(int index, Workout currentWorkout) {
        return String.format(UiConstant.PRINT_RUN_FORMAT_WITH_INDEX, index, currentWorkout);
    }

    /**
     * Prints the text header when adding a new Run.
     * @param newRun The new Run object added.
     */
    public static void printAddRun(Run newRun) {
        printLine();
        System.out.println(UiConstant.ADD_RUN);
        System.out.println(UiConstant.RUN_HEADER);
        System.out.println(newRun);
        printLine();
    }

    /**
     * Prints the text header when adding a new Gym.
     * @param gym The new Gym object added.
     */
    public static void printAddGym(Gym gym) {
        printLine();
        System.out.println(UiConstant.ADD_GYM);
        printGymStats(gym);
        printLine();
    }

    /**
     * Prints all the Run objects added to the list.
     *
     * @throws CustomExceptions.OutOfBounds If index is out of bounds.
     * @throws CustomExceptions.InvalidInput If user input is invalid.
     */
    private static void printRunHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {

        ArrayList<? extends Workout> workoutList = WorkoutList.getWorkouts(UiConstant.RUN);
        System.out.println(UiConstant.RUN_HEADER_WITH_INDEX_FORMAT);

        for (int i = 0; i < workoutList.size(); i++) {
            int index = i + 1;
            Workout currentWorkout = workoutList.get(i);
            String output = getFormattedRunWithIndex(index, currentWorkout);
            System.out.println(output);
        }
    }

    /**
     * Prints all the stations within a specified Gym object.
     * @param gym The Gym object containing the GymStation objects to be printed.
     */
    private static void printGymStats(Gym gym) {
        ArrayList<GymStation> allStations = gym.getStations();
        for (GymStation station: allStations){
            System.out.println(station);
        }
    }

    /**
     * Prints all the information for all Gym objects within the list.
     *
     * @throws CustomExceptions.OutOfBounds If index is out of bounds.
     * @throws CustomExceptions.InvalidInput If user input is invalid.
     */
    private static void printGymHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {
        ArrayList<? extends Workout> workoutList = WorkoutList.getWorkouts(UiConstant.GYM);
        for (int i = 0; i < workoutList.size(); i++) {
            int index = i + 1;
            Gym currentWorkout = (Gym) workoutList.get(i);
            System.out.println("Gym Session " + index + currentWorkout);
            printGymStats(currentWorkout);
            if(i != workoutList.size() - 1){
                printLine();
            }
        }
    }

    /**
     * Prints all Bmi objects recorded.
     *
     * @throws CustomExceptions.OutOfBounds If there is access to a Bmi object that does not exist.
     * @throws CustomExceptions.InvalidInput If there is invalid input.
     */
    private static void printBmiHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {
        HealthList.showBmiHistory();
    }

    /**
     * Prints all Period objects recorded.
     *
     * @throws CustomExceptions.OutOfBounds If there is access to a Period object that does not exist.
     * @throws CustomExceptions.InvalidInput If there is invalid input.
     */
    private static void printPeriodHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {
        HealthList.showPeriodHistory();
    }

    /**
     * Prints the latest Run recorded.
     */
    public static void printLatestRun() {
        try {
            Workout latestRun = WorkoutList.getLatestRun();
            String latestRunString = getFormattedRunWithIndex(WorkoutList.getRunSize(), latestRun);
            System.out.println(UiConstant.RUN_HEADER_WITH_INDEX_FORMAT);
            System.out.println(latestRunString);

        } catch (CustomExceptions.OutOfBounds e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the latest Gym recorded.
     */
    public static void printLatestGym() {
        try {
            Gym latestGym = WorkoutList.getLatestGym();
            int index = WorkoutList.getGymSize();
            System.out.println("Gym Session " + index + latestGym);
            printGymStats(latestGym);
        } catch (CustomExceptions.OutOfBounds e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the latest BMI entry recorded.
     */
    public static void printLatestBmi() {
        HealthList.showCurrentBmi();
    }

    /**
     * Prints the latest Period entry recorded.
     */
    public static void printLatestPeriod() {
        HealthList.showLatestPeriod();
    }

    /**
     * Handler function to print the latest entry of Run, Gym, Period, or BMI objects recorded.
     *
     * @param filter String used to determine the latest Run, Gym, Period, or BMI objects is to be printed.
     */
    public static void printLatest(String filter) {
        try {
            printLine();
            System.out.println("Your latest " + filter + UiConstant.SPLIT_BY_COLON);
            Filters parsedFilter = Filters.valueOf(filter.toUpperCase());
            switch (parsedFilter) {
            case RUN:
                printLatestRun();
                break;

            case GYM:
                printLatestGym();
                break;

            case BMI:
                printLatestBmi();
                break;

            case PERIOD:
                printLatestPeriod();
                break;

            default:
                throw new CustomExceptions.InvalidInput(UiConstant.INVALID_FILTER);
            }
        } catch (CustomExceptions.InvalidInput e) {
            System.out.println(e.getMessage());
        } finally {
            printLine();
        }
    }

    /**
     * Handler function to print all entries of Run, Gym, Period, or BMI objects recorded.
     *
     * @param filter String used to determine if all Run, Gym, Period, or BMI objects are to be printed.
     */
    public static void printHistory(String filter) {
        try {
            printLine();
            System.out.println("Your " + filter + " history" + UiConstant.SPLIT_BY_COLON);
            Filters parsedFilter = Filters.valueOf(filter.toUpperCase());
            switch (parsedFilter) {
            case RUN:
                printRunHistory();
                break;

            case GYM:
                printGymHistory();
                break;

            case BMI:
                printBmiHistory();
                break;

            case PERIOD:
                printPeriodHistory();
                break;

            default:
                throw new CustomExceptions.InvalidInput(UiConstant.INVALID_FILTER);
            }
        } catch (CustomExceptions.OutOfBounds | CustomExceptions.InvalidInput e) {
            System.out.println(e.getMessage());
        } finally {
            printLine();
        }
    }

    /**
     * Prints a specified message and the exception error message.
     * @param e Represents the Exception caught.
     * @param message The custom message to be printed.
     */
    public static void printException(Exception e, String message) {
        System.err.println("Exception Caught!" +
                System.lineSeparator() +
                message +
                System.lineSeparator() +
                System.lineSeparator() +
                e.getMessage());
        printLine();
    }


    /**
     * Prints the welcome banner for PulsePilot.
     */
    public static void printWelcomeBanner() {
        printLine();
        printArt();
        System.out.println("Engaging orbital thrusters...");
        System.out.println("PulsePilot on standby");
        printLine();
    }

    /**
     * Checks whether storage file is present, and prints corresponding message.
     * @param status Integer representing whether the storage file has been loaded. If set to 0, file is present. Else,
     *                file is not present.
     */
    public static void printGreeting(int status) {
        if (status == 0) {
            System.out.println("Welcome back, Captain");
            System.out.println(UiConstant.SUCCESSFUL_LOAD);
        } else {
            System.out.println(UiConstant.MISSING_FILE);
        }
        printLine();
    }

    /**
     * Prints the goodbye message for PulsePilot.
     */
    public static void printGoodbyeMessage() {
        printLine();
        System.out.println("PulsePilot successful touchdown");
        System.out.println("See you soon, Captain!");
        printLine();
    }
}
