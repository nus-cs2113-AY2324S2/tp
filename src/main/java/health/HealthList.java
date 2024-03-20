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

    public static void showBmiHistory() {
        for (Bmi bmi : bmis) {
            System.out.println(bmi);
        }
    }

    public static void addPeriod(Period period) {
        periods.add(period);
    }

    public static void showLatestPeriod() {
        int currentIndex = periods.size();
        System.out.println(periods.get(currentIndex - 1));
    }

    public static void showPeriodHistory() {
        for (Period period : periods) {
            System.out.println(period);
        }
    }
}
