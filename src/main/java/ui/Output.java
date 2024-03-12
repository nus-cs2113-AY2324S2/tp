package ui;

import utility.Constant;
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
            System.out.println(WorkoutList.getSize() + ".\t\t\t" + latestRun);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(Constant.NO_RUNS_FOUND);
        }
        printLine();
    }

    /**
     * Prints all workouts in the workout list
     * @param filter can be "all", "run" or "gym"
     *               "all" prints all workouts
     *               "run" prints only runs (yet to be implemented)
     *               "gym" prints only gym workouts (yet to be implemented)
     * Output is printed to the console in the format:
     *               Type	Time		Distance	Pace
     *               Run	00:10:10	10.3		0:58/km
     *               Run	30:10:10	60.3		0:30/km
     */
    public static void printHistory(String filter){
        printLine();
        try{
            ArrayList<Workout> workoutList = WorkoutList.getWorkouts(filter);
            printExerciseHeader();
            for (int i = 0; i < workoutList.size(); i++){
                System.out.println((i + 1) + ".\t\t\t" + WorkoutList.getWorkouts(filter).get(i));
            }

        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println(Constant.EMPTY_HISTORY);
        }
        printLine();




    }

}
