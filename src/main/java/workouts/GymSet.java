package workouts;

import utility.Constant;

public class GymSet {
    int weight;
    int repititions;

    /**
     * GymSet contains the weight and reps done for 1 set (from a station)
     * @param weight
     * @param repititions
     */
    public GymSet(int weight, int repititions){
        this.weight = weight;
        this.repititions = repititions;
    }

    @Override
    public String toString() {
        return String.format(Constant.GYM_SET_FORMAT, this.repititions, this.weight);
    }
}

