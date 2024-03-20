package health;

import java.util.ArrayList;

/**
 * Represents the list of BMI objects stored.
 */
public class HealthList extends ArrayList<Health> {

    private static final ArrayList<Bmi> bmis = new ArrayList<>();
    private static final ArrayList<Period> periods = new ArrayList<>();

    /**
     * Adds a Bmi to the list of Bmis whenever addBmi is called.
     *
     * @param bmi Bmi object.
     * @throws AssertionError If Bmi object is null.
     */
    public static void addBmi(Bmi bmi) {
        assert bmi != null : "Bmi object cannot be null";
        bmis.add(bmi);
    }

    /**
     * Prints the most recently added Bmi object from bmis list.
     *
     * @throws AssertionError If bmis list is empty.
     */
    public static void showCurrentBmi() {
        assert !bmis.isEmpty() : "BMI List is empty";
        int currentIndex = bmis.size();
        System.out.println(bmis.get(currentIndex - 1));
    }

    /**
     * Prints all the BMI entries recorded.
     */
    public static void showBmiHistory() {
        assert !bmis.isEmpty() : "BMI List is empty";
        for (Bmi bmi : bmis) {
            System.out.println(bmi);
        }
    }

    /**
     * Adds a period to the ArrayList of periods.
     *
     * @param period Period object to be added
     */
    public static void addPeriod(Period period) {
        periods.add(period);
    }

    /**
     * Prints the latest period object added.
     */
    public static void showLatestPeriod() {
        assert !periods.isEmpty() : "Period List is empty";
        int currentIndex = periods.size();
        System.out.println(periods.get(currentIndex - 1));
    }


    /**
     * Prints all Period entries tracked.
     */
    public static void showPeriodHistory() {
        assert !periods.isEmpty() : "Period List is empty";
        for (Period period : periods) {
            System.out.println(period);
        }
    }

    /**
     * Clears the workouts, runs and gyms ArrayLists.
     */
    public static void clearBmisAndPeriods() {
        periods.clear();
        bmis.clear();
    }
}
