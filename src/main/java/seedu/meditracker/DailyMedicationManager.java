package seedu.meditracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of DailyMedication and CRUD-operations (Create, Read, Update, Delete)
 * @see DailyMedication
 */
public class DailyMedicationManager {
    private final List<DailyMedication> dailyMedications;

    /**
     * Constructs DailyMedicationManager with a list of DailyMedication
     * @see DailyMedication
     */
    public DailyMedicationManager() {
        dailyMedications = new ArrayList<>();

        // TODO: Fetch today list from storage.
        //       If does not exist or old list,
        //       populate from MedicationManager
        // TODO: Add @see Storage when implemented
    }

    /**
     * Adds a DailyMedication to the list of DailyMedication
     *
     * @param dailyMedication DailyMedication to be added to the list
     */
    public void addDailyMedication(DailyMedication dailyMedication) {
        dailyMedications.add(dailyMedication);
    }

    /**
     * Fetches the corresponding DailyMedication and set the medication to taken
     *
     * @param listIndex Index of the dailyMedications list to update
     * @see DailyMedication#take()
     */
    public void takeDailyMedication(int listIndex) {
        DailyMedication dailyMedication = dailyMedications.get(listIndex);
        dailyMedication.take();
    }

    /**
     * Fetches the corresponding DailyMedication and set the medication to not taken
     *
     * @param listIndex Index of the dailyMedications list to update
     * @see DailyMedication#untake()
     */
    public void untakeDailyMedication(int listIndex) {
        DailyMedication dailyMedication = dailyMedications.get(listIndex);
        dailyMedication.untake();
    }
}
