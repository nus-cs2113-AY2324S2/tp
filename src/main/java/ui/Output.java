package ui;

import utility.*;
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
        System.out.println("workout /e:run /d:DISTANCE /t:TIME [/date:DATE] - Add a new run");
        System.out.println("workout /e:gym /n:NUMBER_OF_STATIONS [/date:DATE] - Add a new gym workout");
        System.out.println("health /h:bmi /height:HEIGHT /weight:WEIGHT /date:DATE - Add new BMI data");
        System.out.println("health /h:period /start:START_DATE /end:END_DATE - Add new period data");
        System.out.println("health /h:appointment /date:DATE /time:TIME /description:DESCRIPTION" +
                " - Add new appointment data");
        System.out.println("history /item:[run/gym/bmi/period] - " +
                "Shows history of runs/gyms/bmi records/periods tracked/appointment records");
        System.out.println("latest /item:[run/gym/bmi/period] - " +
                "Shows latest entry of runs/gyms/bmi records/periods tracked/appointment records");
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
                + ". (Format: " + WorkoutConstant.STATION_GYM_FORMAT + ")");
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
        return String.format(WorkoutConstant.PRINT_RUN_FORMAT_WITH_INDEX, index, currentWorkout);
    }

    /**
     * Prints the text header when adding a new Run.
     * @param newRun The new Run object added.
     */
    public static void printAddRun(Run newRun) {
        printLine();
        System.out.println(WorkoutConstant.ADD_RUN);
        System.out.println(WorkoutConstant.RUN_HEADER);
        System.out.println(newRun);
        printLine();
    }

    /**
     * Prints the text header when adding a new Gym.
     * @param gym The new Gym object added.
     */
    public static void printAddGym(Gym gym) {
        printLine();
        System.out.println(WorkoutConstant.ADD_GYM);
        printGymStats(gym);
        printLine();
    }

    /**
     * Prints all Workout objects (Run and Gym) based on the time it was added.
     * The list is sorted in descending order. (Latest one first)
     */
    protected static void printWorkoutHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {
        printLine();
        System.out.println("Showing all workouts (runs and gyms):");
        System.out.println(String.format(WorkoutConstant.HISTORY_ALL_HEADER_FORMAT, "Index",
                "Type", "Date" , "Distance" , "Duration" , "Pace", "Station", "Sets" , "Reps" , "Weights"));

        ArrayList<? extends Workout> workoutList = WorkoutList.getWorkouts(WorkoutConstant.ALL);
        for(int i = 0; i < workoutList.size(); i++){
            Workout workout = workoutList.get(i);
            if (workout instanceof Run) {
                Run run = (Run) workout;
                System.out.println(String.format("%-6s\t%s", (i + 1),run.getFormatForAllHistory()));
            } else {
                Gym gym = (Gym) workout;
                for(int j = 0; j < gym.getStations().size(); j++){

                    if(j == 0){
                        System.out.println(String.format("%-6d\t%s" , (i+1), gym.getFormatForAllHistoryFirst()));
                    }
                    else{
                        System.out.println(String.format("%-6s\t%s", "", gym.getFormatForAllHistorySubsequent()));
                    }
                }

            }
        }
        printLine();
    }

    /**
     * Prints all the Run objects added to the list.
     *
     * @throws CustomExceptions.OutOfBounds If index is out of bounds.
     * @throws CustomExceptions.InvalidInput If user input is invalid.
     */
    protected static void printRunHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {
        printLine();
        System.out.println("Your run history:");
        ArrayList<? extends Workout> workoutList = WorkoutList.getWorkouts(WorkoutConstant.RUN);
        System.out.println(WorkoutConstant.RUN_HEADER_WITH_INDEX_FORMAT);

        for (int i = 0; i < workoutList.size(); i++) {
            int index = i + 1;
            Workout currentWorkout = workoutList.get(i);
            String output = getFormattedRunWithIndex(index, currentWorkout);
            System.out.println(output);
        }
        printLine();
    }

    /**
     * Prints all the stations within a specified Gym object.
     * @param gym The Gym object containing the GymStation objects to be printed.
     */
    protected static void printGymStats(Gym gym) {
        ArrayList<GymStation> allStations = gym.getStations();
        for (int i = 0; i < allStations.size(); i++){
            System.out.println(String.format("Station %d %s", i+1, allStations.get(i).toString()));
        }
    }

    /**
     * Prints all the information for all Gym objects within the list.
     *
     * @throws CustomExceptions.OutOfBounds If index is out of bounds.
     * @throws CustomExceptions.InvalidInput If user input is invalid.
     */
    protected static void printGymHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {
        printLine();
        System.out.println("Your gym history:");
        ArrayList<? extends Workout> workoutList = WorkoutList.getWorkouts(WorkoutConstant.GYM);
        for (int i = 0; i < workoutList.size(); i++) {
            int index = i + 1;
            Gym currentWorkout = (Gym) workoutList.get(i);
            System.out.println("Gym Session " + index + currentWorkout);
            printGymStats(currentWorkout);
            if(i != workoutList.size() - 1){
                printLine();
            }
        }
        printLine();
    }

    /**
     * Prints all Bmi objects recorded.
     *
     * @throws CustomExceptions.OutOfBounds If there is access to a Bmi object that does not exist.
     * @throws CustomExceptions.InvalidInput If there is invalid input.
     */
    protected static void printBmiHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {
        printLine();
        System.out.println("Your BMI history:");
        HealthList.showBmiHistory();
        printLine();
    }

    /**
     * Prints all Period objects recorded.
     *
     * @throws CustomExceptions.OutOfBounds If there is access to a Period object that does not exist.
     * @throws CustomExceptions.InvalidInput If there is invalid input.
     */
    protected static void printPeriodHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {
        printLine();
        System.out.println("Your Period history:");
        HealthList.showPeriodHistory();
        printLine();
    }

    /**
     * Prints the latest Run recorded.
     */
    protected static void printLatestRun() {
        printLine();
        try {

            Workout latestRun = WorkoutList.getLatestRun();
            String latestRunString = getFormattedRunWithIndex(WorkoutList.getRunSize(), latestRun);
            System.out.println("Your latest run:");
            System.out.println(WorkoutConstant.RUN_HEADER_WITH_INDEX_FORMAT);
            System.out.println(latestRunString);

        } catch (CustomExceptions.OutOfBounds e) {
            System.out.println(e.getMessage());
        }
        printLine();
    }

    /**
     * Prints the latest Gym recorded.
     */
    protected static void printLatestGym() {
        printLine();
        try {
            Gym latestGym = WorkoutList.getLatestGym();
            int index = WorkoutList.getGymSize();
            System.out.println("Your latest gym:");
            System.out.println("Gym Session " + index + latestGym);
            printGymStats(latestGym);
        } catch (CustomExceptions.OutOfBounds e) {
            System.out.println(e.getMessage());
        }
        printLine();
    }

    /**
     * Prints the latest BMI entry recorded.
     */
    protected static void printLatestBmi() {
        printLine();
        HealthList.showCurrentBmi();
        printLine();
    }

    /**
     * Prints the latest Period entry recorded.
     */
    protected static void printLatestPeriod() {
        printLine();
        HealthList.showLatestPeriod();
        printLine();
    }

    /**
     * Handler function to print the latest entry of Run, Gym, Period, or BMI objects recorded.
     *
     * @param filter String used to determine the latest Run, Gym, Period, or BMI objects is to be printed.
     */
    public static void printLatest(String filter) {
        try {
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
                throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_HISTORY_FILTER_ERROR);
            }
        } catch (CustomExceptions.InvalidInput e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handler function to print all entries of Run, Gym, Period, or BMI objects recorded.
     *
     * @param filter String used to determine if all Run, Gym, Period, or BMI objects are to be printed.
     */
    public static void printHistory(String filter) {
        try {
            Filters parsedFilter = Filters.valueOf(filter.toUpperCase());
            switch (parsedFilter) {
            case WORKOUTS:
                printWorkoutHistory();
                break;
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
                break;
            }
        } catch (CustomExceptions.OutOfBounds | CustomExceptions.InvalidInput e) {
            Output.printException(e.getMessage());
        }
    }

    /**
     * Prints a specified message and the exception error message.
     * @param message The custom message to be printed.
     */
    public static void printException(String message) {
        System.err.println("Exception Caught!" + System.lineSeparator() + message);
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
    public static void printGreeting(int status, String name) {
        if (status == 0) {
            System.out.println("Welcome back, Captain " + name);
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
