package exercise;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Parser {

    public static void printExerciseHeader(){
        System.out.println("Type\tTime\t\tDistance\tPace");
    }

    public static void printExerciseHeaderWithIndex(){
        System.out.println("Index\t\tType\tTime\t\tDistance\tPace");
    }

    private static void printNewLine() {
        System.out.print("\n");
    }

    public static void printExerciseHeaderWithDate(){
        System.out.println("Type\tDate\t\t\tTime\t\tDistance\tPace");
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

        Parser.printExerciseHeaderWithIndex();

        // Yet to implement - print total distance, time, and pace for all runs
        for (int i = 0; i < WorkoutList.getWorkouts(filter).size(); i++){
            System.out.println(i + "\t\t\t" + WorkoutList.getWorkouts(filter).get(i));
        }

        Parser.printNewLine();
    }



    public static void printSpecificWorkout(int index){
        Parser.printExerciseHeader();
        System.out.println(WorkoutList.getSpecificWorkout(index));

    }

    public static void printLatestWorkout(){
        Parser.printExerciseHeader();
        System.out.println(WorkoutList.getLatestWorkout());
    }

    public static void printLatestRun(){
        Parser.printExerciseHeader();
        System.out.println(WorkoutList.getLatestRun());
        Parser.printNewLine();

    }
}
