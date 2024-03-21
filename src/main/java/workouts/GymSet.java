package workouts;

import utility.UiConstant;

/**
 * Represents a GymSet object.
 */
public class GymSet {
    int weight;
    int repetitions;

    /**
     * Constructs a new GymSet object using the weight and reps for 1 set of a gym station.
     *
     * @param weight The weight done for the set.
     * @param repetitions The number of reps done for the set.
     */
    public GymSet(int weight, int repetitions){
        this.weight = weight;
        this.repetitions = repetitions;
    }

    /**
     * Retrieves a string representation of a GymSet object.
     *
     * @return A formatted string representing a GymSet, inclusive of the number of repetitions and weight done.
     */
    @Override
    public String toString() {
        return String.format(UiConstant.GYM_SET_FORMAT, this.repetitions, this.weight);
    }
}

