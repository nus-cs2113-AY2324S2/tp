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
        System.out.println("Commands List:" + "\n");
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

    private static void printExerciseHeader(){
        System.out.println("Index\t\tType\tTime\t\tDistance\tPace\t\tDate");
    }
    public static void printAddRun(Workout newRun){
        printLine();
        System.out.println("Successfully added the following run");
        printExerciseHeader();
        System.out.println(newRun);
        printLine();
    }
    public static void printLatestRun(){
        printLine();
        try{
            Workout latestRun = WorkoutList.getLatestRun();
            printExerciseHeader();
            System.out.println(WorkoutList.getRunSize() + ".\t\t\t" + latestRun);
        } catch (CustomExceptions.OutOfBounds e){
            System.out.println(e.getMessage());
        }
        printLine();
    }

    public static void printHistory(String filter) {

        printLine();
        try{

            ArrayList<Workout> workoutList = WorkoutList.getWorkouts(filter);
            printExerciseHeader();
            for (int i = 0; i < workoutList.size(); i++){
                System.out.println((i + 1) + ".\t\t\t" + workoutList.get(i));
            }

        } catch (CustomExceptions.OutOfBounds | CustomExceptions.InvalidInput  e ){
            System.out.println(e.getMessage());
        }
        printLine();
    }
}
