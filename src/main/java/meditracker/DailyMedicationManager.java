package meditracker;

import meditracker.ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of DailyMedication and CRUD-operations (Create, Read, Update, Delete)
 * @see DailyMedication
 */
public class DailyMedicationManager {
    private final List<DailyMedication> dailyMedications;
    private Ui ui;

    /**
     * Constructs DailyMedicationManager with a list of DailyMedication
     * @see DailyMedication
     */
    public DailyMedicationManager() {
        dailyMedications = new ArrayList<>();
        ui = new Ui();

        // TODO: Fetch today list from storage.
        //       If does not exist or old list,
        //       populate from MedicationManager
        // TODO: Add @see Storage when implemented
    }

    /**
     * Constructs DailyMedicationManager with lines imported from the textfile
     * @param lines lines of String read from each row in the textfile
     */
    public DailyMedicationManager(List<String> lines) {
        dailyMedications = new ArrayList<>();
        for(String line : lines) {
            DailyMedication dailyMedication = parseImportedLine(line);
            addDailyMedication(dailyMedication);
        }
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
     * Gets the DailyMedication object from the dailyMedications list.
     * Also converts the index to 0-based indexing before being used.
     *
     * @param listIndex Index of the dailyMedications list to update (1-based indexing)
     * @return DailyMedication object at the corresponding index (0-based indexing)
     * @throws IndexOutOfBoundsException Out of range index specified
     */
    public DailyMedication getDailyMedication(int listIndex) throws IndexOutOfBoundsException {
        listIndex--; // Decremented to 0-base indexing
        return dailyMedications.get(listIndex);
    }

    /**
     * Fetches the corresponding DailyMedication and set the medication to taken
     *
     * @param listIndex Index of the dailyMedications list to update (1-based indexing)
     * @see DailyMedication#take()
     */
    public void takeDailyMedication(int listIndex) {
        DailyMedication dailyMedication = getDailyMedication(listIndex);
        dailyMedication.take();
    }

    /**
     * Fetches the corresponding DailyMedication and set the medication to not taken
     *
     * @param listIndex Index of the dailyMedications list to update (1-based indexing)
     * @see DailyMedication#untake()
     */
    public void untakeDailyMedication(int listIndex) {
        DailyMedication dailyMedication = getDailyMedication(listIndex);
        dailyMedication.untake();
    }

    public void printMedications() {
        ui.printMedsList(dailyMedications, "Daily Medications");
    }

    /**
     * Separates each row by the separator and add into the DailyMedicationManager
     * @param line each line read from the textfile
     * @return dailyMedication object to add into the DailyMedicationManager
     */
    private DailyMedication parseImportedLine(String line) {
        String[] fields = line.split("\\|");
        boolean isTaken = Boolean.parseBoolean(fields[0].trim());
        DailyMedication dailyMedication = new DailyMedication(fields[1].trim());
        if (isTaken) {
            dailyMedication.take();
        } else {
            dailyMedication.untake();
        }
        return dailyMedication;
    }
}
