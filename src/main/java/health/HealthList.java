package health;

import storage.LogFile;
import utility.CustomExceptions;
import utility.HealthConstant;
import java.util.ArrayList;

/**
 * Represents the list of BMI objects stored.
 */
public class HealthList extends ArrayList<Health> {
    static LogFile logFile = LogFile.getInstance();
    private static final ArrayList<Bmi> bmis = new ArrayList<>();
    private static final ArrayList<Period> periods = new ArrayList<>();

    //@@author j013n3
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

    //@@author syj02
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
     *
     * @throws AssertionError If bmis list is empty
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
     * @throws AssertionError If period object is null
     */
    public static void addPeriod(Period period) {
        assert period != null : HealthConstant.PERIOD_CANNOT_BE_NULL;
        periods.add(period);
    }

    /**
     * Prints the latest period object added.
     * @throws AssertionError If periods list is empty
     */
    public static void showLatestPeriod() {
        assert !periods.isEmpty() : HealthConstant.PERIOD_LIST_EMPTY;
        int currentIndex = periods.size();
        System.out.println(periods.get(currentIndex - 1));
    }

    //@@author j013n3
    /**
     * Prints all Period entries tracked.
     * @throws AssertionError If periods list is empty
     */
    public static void showPeriodHistory() {
        assert !periods.isEmpty() : HealthConstant.PERIOD_LIST_EMPTY;
        for (Period period : periods) {
            System.out.println(period);
        }
    }

    //@@author l5_z
    /**
     * Clears the Bmis and Periods array lists.
     * @throws AssertionError If periods and bmis lists are not empty
     */
    public static void clearBmisAndPeriods() {
        periods.clear();
        bmis.clear();
        assert bmis.isEmpty() : "Bmi list is not cleared.";
        assert periods.isEmpty() : "Period list is not cleared.";
    }
    //@@author rouvinerh


    /**
     * Retrieves size of periods list.
     *
     * @return Size of periods list.
     */
    public static int getPeriodsSize() {
        return periods.size();
    }

    /**
     * Retrieves size of bmis list.
     *
     * @return Size of bmis list.
     */
    public static int getBmisSize() {
        return bmis.size();
    }
    /**
     * Deletes Bmi object based on index.
     * @param index Index of the Bmi object to be deleted.
     */
    public static void deleteBmi(int index) throws CustomExceptions.OutOfBounds {
        assert !bmis.isEmpty() : "BMI list is empty.";
        if (index < 0 || index >= bmis.size()) {
            throw new CustomExceptions.OutOfBounds("Invalid index to delete!");
        }
        Bmi deletedBmi = bmis.get(index);
        System.out.println("Removed BMI entry of " +
                deletedBmi.bmiValue +
                "from " +
                deletedBmi.date);
        bmis.remove(index);
        LogFile.writeLog("Removed BMI with index: " + index, false);
    }

    /**
     * Deletes Bmi object based on index.
     * @param index Index of the Bmi object to be deleted.
     */
    public static void deletePeriod(int index) throws CustomExceptions.OutOfBounds {
        assert !periods.isEmpty() : "Period list is empty.";
        if (index < 0 || index >= periods.size()) {
            throw new CustomExceptions.OutOfBounds("Invalid index to delete!");
        }
        Period deletedPeriod = periods.get(index);
        System.out.println("Removed period entry with start date: " +
                deletedPeriod.startDate +
                "and end date: " +
                deletedPeriod.endDate);
        periods.remove(index);
        LogFile.writeLog("Removed period with index: " + index, false);
    }
}
