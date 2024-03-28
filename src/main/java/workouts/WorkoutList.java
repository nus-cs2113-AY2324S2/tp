package workouts;

import storage.LogFile;
import utility.CustomExceptions;
import utility.ErrorConstant;
import utility.WorkoutConstant;

import java.util.ArrayList;

/**
 * Represents the WorkoutList object.
 */
public class WorkoutList extends ArrayList<Workout> {
    private static final ArrayList<Workout> workouts = new ArrayList<>();
    private static final ArrayList<Run> runs = new ArrayList<>();
    private static final ArrayList<Gym> gyms = new ArrayList<>();


    /**
     * Adds a workout to the list of workouts.
     *
     * @param workout Workout object to be added.
     */
    private static void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    /**
     * Adds a run to the list of runs and workouts.
     *
     * @param run Run object to be added.
     */
    public static void addRun(Run run) {
        runs.add(run);
        addWorkout(run);
    }

    /**
     * Adds a gym to the list of gyms and workouts.
     *
     * @param gym Gym object to be added.
     */
    public static void addGym(Gym gym) {
        gyms.add(gym);
        addWorkout(gym);
    }

    /**
     * Returns a list of workouts based on the filter.
     *
     * @param filter can be "all", "run" or "gym".
     *               "all" returns all workouts.
     *               "run" returns only runs.
     *               "gym" returns only gym workouts.
     * @return ArrayList of workouts.
     */
    public static ArrayList<? extends Workout> getWorkouts(String filter)
            throws CustomExceptions.OutOfBounds,
            CustomExceptions.InvalidInput {

        filter = filter.toLowerCase();

        if(!filter.equals(WorkoutConstant.ALL) && !filter.equals(WorkoutConstant.RUN)
                && !filter.equals(WorkoutConstant.GYM)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_HISTORY_FILTER_ERROR);
        }
        if(filter.equals(WorkoutConstant.RUN) && runs.isEmpty()){
            throw new CustomExceptions.OutOfBounds(ErrorConstant.HISTORY_RUN_EMPTY_ERROR);
        }
        if(filter.equals(WorkoutConstant.ALL) && workouts.isEmpty()){
            throw new CustomExceptions.OutOfBounds(ErrorConstant.HISTORY_WORKOUTS_EMPTY_ERROR);
        }
        if(filter.equals(WorkoutConstant.GYM) && gyms.isEmpty()){
            throw new CustomExceptions.OutOfBounds(ErrorConstant.HISTORY_GYM_EMPTY_ERROR);
        }

        if(filter.equals(WorkoutConstant.RUN)){
            return runs;
        } else if (filter.equals(WorkoutConstant.GYM)) {
            return gyms;
        } else {
            return workouts;
        }

    }

    /**
     * Returns latest run.
     *
     * @return The latest Run object added.
     * @throws CustomExceptions.OutOfBounds If no runs are found in the list.
     */
    public static Run getLatestRun() throws CustomExceptions.OutOfBounds {
        if (runs.isEmpty()) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.HISTORY_RUN_EMPTY_ERROR);
        }
        return runs.get(runs.size() - 1);
    }

    public static Gym getLatestGym() throws CustomExceptions.OutOfBounds {
        if (gyms.isEmpty()) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.HISTORY_GYM_EMPTY_ERROR);
        }
        return gyms.get(gyms.size() - 1);
    }

    /**
     * Returns the number of runs in the list.
     *
     * @return The number of runs.
     */
    public static int getRunSize() {
        return runs.size();
    }

    /**
     * Returns the number of gyms in the list.
     *
     * @return The number of gyms.
     */
    public static int getGymSize() {
        return gyms.size();
    }

    /**
     * Deletes Gym object based on index.
     * @param index Index of the Gym object to be deleted.
     */
    public static void deleteGym(int index) throws CustomExceptions.OutOfBounds {
        assert !gyms.isEmpty() : "Gym list is empty.";
        if (index < 0 || index >= gyms.size()) {
            throw new CustomExceptions.OutOfBounds("Invalid index to delete!");
        }
        Gym deletedGym = gyms.get(index);
        System.out.println("Removed Gym entry with " +
                deletedGym.stations.size() +
                " stations.");
        workouts.remove(deletedGym);
        gyms.remove(index);
        LogFile.writeLog("Removed gym with index: " + index, false);
    }

    /**
     * Deletes Run object based on index.
     * @param index Index of the Run object to be deleted.
     */
    public static void deleteRun(int index) throws CustomExceptions.OutOfBounds {
        assert !runs.isEmpty() : "Run list is empty.";
        if (index < 0 || index >= runs.size()) {
            throw new CustomExceptions.OutOfBounds("Invalid index to delete!");
        }
        Run deletedRun = runs.get(index);
        System.out.println("Removed Run entry with " +
                deletedRun.distance +
                "km at " +
                deletedRun.getPace() +
                ".");
        workouts.remove(deletedRun);
        runs.remove(index);
        LogFile.writeLog("Removed run with index: " + index, false);
    }

    /**
     * Clears the workouts, runs and gyms ArrayLists.
     */
    public static void clearWorkoutsRunGym() {
        workouts.clear();
        runs.clear();
        gyms.clear();
    }
}
