package workouts;

import utility.Constant;

/**
 * Represents a GymSet object.
 */
public class GymSet {
    int weight;
    int repititions;

    /**
     * Constructs a new GymSet object using the weight and reps for 1 set of a gym station.
     *
     * @param weight The weight done for the set.
     * @param repititions The number of reps done for the set.
     */
    public GymSet(int weight, int repititions){
        this.weight = weight;
        this.repititions = repititions;
    }

    /**
     * Retrieves a string representation of a GymSet object.
     *
     * @return A formatted string representing a GymSet, inclusive of the number of repititions and weight done.
     */
    @Override
    public String toString() {
        return String.format(Constant.GYM_SET_FORMAT, this.repititions, this.weight);
    }
}

