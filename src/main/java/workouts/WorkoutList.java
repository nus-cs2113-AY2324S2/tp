package workouts;

import utility.Constant;
import utility.CustomExceptions;

import java.util.ArrayList;

public class WorkoutList extends ArrayList<Workout> {
    private static final ArrayList<Workout> workouts = new ArrayList<>();
    private static final ArrayList<Workout> runs = new ArrayList<>();

    /**
     * Adds a workout to the list of workouts whenever addRun is called
     *
     * @param workout Workout object
     */
    private static void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    /**
     * Adds a run to the list of runs and to the main workout list too
     *
     * @param run Run object
     */
    public static void addRun(Run run) {
        runs.add(run);
        addWorkout(run);
    }

    /**
     * Returns a list of workouts based on the filter
     *
     * @param filter can be "all", "run" or "gym"
     *               "all" returns all workouts
     *               "run" returns only runs
     *               "gym" returns only gym workouts
     * @return ArrayList of workouts
     */
    public static ArrayList<Workout> getWorkouts(String filter)
            throws CustomExceptions.OutOfBounds,
            CustomExceptions.InvalidInput {

        if(!filter.equals(Constant.ALL) && !filter.equals(Constant.RUN) && !filter.equals(Constant.GYM)) {
            throw new CustomExceptions.InvalidInput(Constant.INVALID_PRINT_HISTORY_FILTER);
        }

        if(filter.equals(Constant.RUN) && runs.isEmpty()){
            throw new CustomExceptions.OutOfBounds(Constant.NO_RUNS_FOUND);
        }
        if(filter.equals(Constant.ALL) && workouts.isEmpty()){
            throw new CustomExceptions.OutOfBounds(Constant.NO_HISTORY_FOUND);
        }

        if(filter.equals(Constant.RUN)){
            return runs;
        } else {
            return workouts;
        }

    }

    public static Run getLatestRun() throws CustomExceptions.OutOfBounds {
        if (runs.isEmpty()) {
            throw new CustomExceptions.OutOfBounds(Constant.NO_RUNS_FOUND);
        }
        return (Run) runs.get(runs.size() - 1);
    }

    public static int getRunSize() {
        return runs.size();
    }


    public static void clearWorkoutsAndRun() {
        workouts.clear();
        runs.clear();
    }
}
