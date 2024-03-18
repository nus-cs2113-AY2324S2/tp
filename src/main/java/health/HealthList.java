package health;

import utility.Constant;
import utility.CustomExceptions;
import workouts.Workout;

import java.util.ArrayList;

public class HealthList extends ArrayList<Health> {
    private static final ArrayList<Health> bmis = new ArrayList<>();

    public static void addBmi(Bmi bmi) {
        bmis.add(bmi);
    }

    public static void showCurrentBmi() {
        int currentIndex = bmis.size();
        System.out.println(bmis.get(currentIndex - 1));
    }

    public static void showBmiHistory() {
        for (Health bmi : bmis) {
            System.out.println(bmi);
        }
    }
}
