package ui;

import utility.Constant;
import utility.CustomExceptions;
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


    private static String getFormattedRunWithIndex(int index, Workout currentWorkout){
        return String.format(Constant.PRINT_RUN_FORMAT_WITH_INDEX, index, currentWorkout);
    }
    public static void printAddRun(Workout newRun){
        printLine();
        System.out.println(Constant.ADD_RUN);
        System.out.println(Constant.RUN_HEADER);
        System.out.println(newRun);
        printLine();
    }
    public static void printLatestRun(){
        try{
            printLine();
            Workout latestRun = WorkoutList.getLatestRun();
            String latestRunString = getFormattedRunWithIndex(WorkoutList.getRunSize(), latestRun);
            System.out.println(Constant.RUN_HEADER_WITH_INDEX_FORMAT);
            System.out.println(latestRunString);

        } catch (CustomExceptions.OutOfBounds e){
            System.out.println(e.getMessage());
        } finally {
            printLine();
        }
    }

    public static void printHistory(String filter) {

        try{
            printLine();
            ArrayList<Workout> workoutList = WorkoutList.getWorkouts(filter);
            System.out.println(Constant.RUN_HEADER_WITH_INDEX_FORMAT);

            for (int i = 0; i < workoutList.size(); i++){
                int index = i + 1;
                Workout currentWorkout = workoutList.get(i);
                String output = getFormattedRunWithIndex(index, currentWorkout);
                System.out.println(output);
            }

        } catch (CustomExceptions.OutOfBounds | CustomExceptions.InvalidInput  e ){
            System.out.println(e.getMessage());
        } finally {
            printLine();
        }
    }
}
