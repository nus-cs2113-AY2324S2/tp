package ui;

import utility.Constant;
import utility.CustomExceptions;
import utility.Filters;
import workouts.Gym;
import workouts.GymStation;
import workouts.Run;
import workouts.Workout;
import workouts.WorkoutList;
import java.util.ArrayList;

public class Output {

    /**
     * Prints a horizontal line.
     */
    private static void printLine() {
        System.out.println(Constant.PARTITION_LINE);
    }

    /**
     * Prints the help message.
     */
    public static void printHelp() {
        printLine();
        System.out.println("Commands List:");
        System.out.println();
        System.out.println("list - prints out the List");
        System.out.println("help - procures command list");
        System.out.println("exit - terminates the bot");
        printLine();
        System.out.println("bmi - calculates BMI");
        System.out.println("weight - save current weight");
        System.out.println("height - save current height");
        printLine();
        System.out.println("bmi format: bmi *parameter*");
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
                + "( format: " + Constant.STATION_GYM_FORMAT + ")");
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
        return String.format(Constant.PRINT_RUN_FORMAT_WITH_INDEX, index, currentWorkout);
    }

    /**
     * Prints the text header when adding a new Run.
     * @param newRun The new Run object added.
     */
    public static void printAddRun(Run newRun) {
        printLine();
        System.out.println(Constant.ADD_RUN);
        System.out.println(Constant.RUN_HEADER);
        System.out.println(newRun);
        printLine();
    }

    /**
     * Prints the text header when adding a new Gym.
     * @param gym The new Gym object added.
     */
    public static void printAddGym(Gym gym){
        printLine();
        System.out.println(Constant.ADD_GYM);
        printGymStats(gym);
        printLine();
    }

    /**
     * Prints the latest Run object added.
     */
    public static void printLatestRun() {
        try {
            printLine();
            Workout latestRun = WorkoutList.getLatestRun();
            String latestRunString = getFormattedRunWithIndex(WorkoutList.getRunSize(), latestRun);
            System.out.println(Constant.RUN_HEADER_WITH_INDEX_FORMAT);
            System.out.println(latestRunString);

        } catch (CustomExceptions.OutOfBounds e) {
            System.out.println(e.getMessage());
        } finally {
            printLine();
        }
    }

    /**
     * Prints all the Run objects added to the list.
     *
     * @throws CustomExceptions.OutOfBounds If index is out of bounds.
     * @throws CustomExceptions.InvalidInput If user input is invalid.
     */
    private static void printRunHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {

        ArrayList<? extends Workout> workoutList = WorkoutList.getWorkouts(Constant.RUN);
        System.out.println(Constant.RUN_HEADER_WITH_INDEX_FORMAT);

        for (int i = 0; i < workoutList.size(); i++) {
            int index = i + 1;
            Workout currentWorkout = workoutList.get(i);
            String output = getFormattedRunWithIndex(index, currentWorkout);
            System.out.println(output);
        }
    }

    /**
     * For justin to do.
     */
    private static void printExerciseHistory() {

    }

    /**
     * Prints all the stations within a specified Gym object.
     * @param gym The Gym object containing the GymStation objects to be printed.
     */
    private static void printGymStats(Gym gym){
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
        ArrayList<? extends Workout> workoutList = WorkoutList.getWorkouts(Constant.GYM);
        for (int i = 0; i < workoutList.size(); i++) {
            int index = i+1;
            Gym currentWorkout = (Gym) workoutList.get(i);
            System.out.println("Gym Session " + index + currentWorkout);
            printGymStats(currentWorkout);
            if(i != workoutList.size()-1){
                printLine();
            }
        }
    }

    /**
     * Handler function to print all the Workout objects added to the list.
     *
     * @param filter String used to determine if all Run, Gym or Workout objects are printed.
     */
    public static void printHistory(String filter) {
        try {
            printLine();
            Filters parsedFilter = Filters.valueOf(filter.toUpperCase());
            switch (parsedFilter) {
            case ALL:
                printExerciseHistory();
                break;
            case RUN:
                printRunHistory();
                break;
            case GYM:
                printGymHistory();
                break;
            default:
                throw new CustomExceptions.InvalidInput(Constant.INVALID_PRINT_HISTORY_FILTER);
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
    public static void printException(Exception e, String message){
        System.err.println("Exception Caught!\n" + message + "\n\n" + e.getMessage());
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
            System.out.println(Constant.SUCCESSFUL_LOAD);
        } else {
            System.out.println(Constant.MISSING_FILE);
        }
        printLine();
    }
}
