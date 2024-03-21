package health;

import utility.HealthConstant;

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
        assert bmi != null : HealthConstant.BMI_CANNOT_BE_NULL;
        bmis.add(bmi);
    }

    /**
     * Prints the most recently added Bmi object from bmis list.
     *
     * @throws AssertionError If bmis list is empty.
     */
    public static void showCurrentBmi() {
        assert !bmis.isEmpty() : HealthConstant.BMI_LIST_EMPTY;
        int currentIndex = bmis.size();
        System.out.println(bmis.get(currentIndex - 1));
    }

    /**
     * Prints all the BMI entries recorded.
     */
    public static void showBmiHistory() {
        assert !bmis.isEmpty() : HealthConstant.BMI_LIST_EMPTY;
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
        assert period != null : HealthConstant.PERIOD_CANNOT_BE_NULL;
        periods.add(period);
    }

    /**
     * Prints the latest period object added.
     */
    public static void showLatestPeriod() {
        assert !periods.isEmpty() : HealthConstant.PERIOD_LIST_EMPTY;
        int currentIndex = periods.size();
        System.out.println(periods.get(currentIndex - 1));
    }


    /**
     * Prints all Period entries tracked.
     */
    public static void showPeriodHistory() {
        assert !periods.isEmpty() : HealthConstant.PERIOD_LIST_EMPTY;
        for (Period period : periods) {
            System.out.println(period);
        }
    }

    /**
     * Clears the Bmis and Periods array lists.
     */
    public static void clearBmisAndPeriods() {
        periods.clear();
        bmis.clear();
    }
}
