package workouts;

import java.util.ArrayList;

public class WorkoutList extends ArrayList<Workout> {
    private static final ArrayList<Workout> workouts = new ArrayList<Workout>();
    private static final ArrayList<Workout> runs = new ArrayList<Workout>();

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
    public static void addRun(Run run){
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
    public static ArrayList<Workout> getWorkouts(String filter) {
        if (filter.equals("run")) {
            return runs;
        } else {
            return workouts;
        }
    }

    /**
     * Returns a specific run based on the index
     * @param index index of the run
     * @return Run object
     */
    public static Workout getSpecificRun(int index){
        return runs.get(index);
    }
    /**
     * Returns a specific workout based on the index
     * @param index index of the workout
     * @return Workout object
     */
    public static Workout getSpecificWorkout(int index){
        return workouts.get(index);
    }

    /**
     * Returns the latest workout
     * @return Workout object
     */
    public static Workout getLatestWorkout(){
        return workouts.get(workouts.size()-1);
    }

    public static Workout getLatestRun(){
        return runs.get(runs.size()-1);
    }


    /**
     * Used for ParserTest
     */
    public static void clearWorkoutsAndRun(){
        workouts.clear();
        runs.clear();
    }
}
