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
    public static void printLine() {
        System.out.println(Constant.PARTITION_LINE);
    }

    /**
     * Prints the help message.
     */
    public static void printHelp() {
        printLine();
        System.out.println("Commands List:");
        System.out.println();
        System.out.println("new /e:run /d:DATE /t:TIME /l:DURATION - Add a new run");
        System.out.println("new /e:gym /n:NUMBER_OF_STATIONS - Add a new gym workout");
        System.out.println("health /t:bmi /w:WEIGHT /h:HEIGHT /d:DATE - Add new BMI data");
        System.out.println("health /t:period /s:START_DATE /e:END_DATE - Add new period data");
        System.out.println("history /e:all - Show history of all exercises");
        System.out.println("history /e:run - Show history of runs");
        System.out.println("history /e:gym - Show history of gym workouts");
        System.out.println("latest - Show the latest run");
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

    public static void printGymStationPrompt(int stationNumber) {
        printLine();

        System.out.println("Please enter the details of station "
                + stationNumber
                + "( format: " + Constant.STATION_GYM_FORMAT + ")");
        printLine();
    }


    private static String getFormattedRunWithIndex(int index, Workout currentWorkout) {
        return String.format(Constant.PRINT_RUN_FORMAT_WITH_INDEX, index, currentWorkout);
    }

    public static void printAddRun(Run newRun) {
        printLine();
        System.out.println(Constant.ADD_RUN);
        System.out.println(Constant.RUN_HEADER);
        System.out.println(newRun);
        printLine();
    }

    public static void printAddGym(Gym gym) {
        printLine();
        System.out.println(Constant.ADD_GYM);
        printGymStats(gym);
        printLine();
    }

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

    private static void printExerciseHistory() {

    }

    private static void printGymStats(Gym gym) {
        ArrayList<GymStation> allStations = gym.getStations();
        for (GymStation station: allStations){
            System.out.println(station);
        }
    }

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
    public static void printException(Exception e, String message) {
        System.err.println("Exception Caught!\n" + message + "\n\n" + e.getMessage());
        printLine();
    }

    public static void printWelcomeBanner() {
        printLine();
        printArt();
        System.out.println("Engaging orbital thrusters...");
        System.out.println("PulsePilot on standby");
        printLine();
    }

    public static void printGreeting(int status) {
        if (status == 0) {
            System.out.println("Welcome back, Captain");
            System.out.println(Constant.SUCCESSFUL_LOAD);
        } else {
            System.out.println(Constant.MISSING_FILE);
        }
        printLine();
    }

    public static void printGoodbyeMessage() {
        System.out.println("PulsePilot successful touchdown");
        System.out.println("See you soon, Captain!");
        printLine();
    }
}
