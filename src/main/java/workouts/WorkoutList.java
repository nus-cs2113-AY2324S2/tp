package workouts;

import utility.Constant;
import utility.CustomExceptions;

import java.util.ArrayList;

public class WorkoutList extends ArrayList<Workout> {
    private static final ArrayList<Workout> workouts = new ArrayList<>();
    private static final ArrayList<Workout> runs = new ArrayList<>();

    /**
     * Adds a workout to the list of workouts whenever addRun is called
     * @param workout Workout object
     */
    private static void addWorkout(Workout workout){
        workouts.add(workout);
    }

    /**
     * Adds a run to the list of runs and to the main workout list too
     * @param run Run object
     */
    protected static void addRun(Run run){
        runs.add(run);
        addWorkout(run);
    }

    /**
     * Returns a list of workouts based on the filter
     * @param filter can be "all", "run" or "gym"
     *               "all" returns all workouts
     *               "run" returns only runs
     *               "gym" returns only gym workouts
     * @return ArrayList of workouts
     */
    public static ArrayList<Workout> getWorkouts(String filter) throws CustomExceptions.OutOfBounds {
        if (filter.equals("run")) {
            if (runs.isEmpty()){
                throw new CustomExceptions.OutOfBounds(Constant.NO_RUNS_FOUND);
            }
            return runs;
        } else {
            if (workouts.isEmpty()){
                throw new CustomExceptions.OutOfBounds(Constant.EMPTY_HISTORY);
            }
            return workouts;
        }
    }


    public static Workout getSpecificRun(int index){
        return runs.get(index);
    }

    public static Workout getSpecificWorkout(int index){
        return workouts.get(index);
    }


    public static Workout getLatestWorkout(){
        return workouts.get(workouts.size()-1);
    }

    public static Workout getLatestRun() throws CustomExceptions.OutOfBounds {
        if (runs.isEmpty()) {
            throw new CustomExceptions.OutOfBounds(Constant.NO_RUNS_FOUND);
        }
        return runs.get(runs.size() -1 );
    }

    public static int getSize(){
        return runs.size();
    }


    /**
     * Used for ParserTest
     */
    public static void clearWorkoutsAndRun(){
        workouts.clear();
        runs.clear();
    }
}
