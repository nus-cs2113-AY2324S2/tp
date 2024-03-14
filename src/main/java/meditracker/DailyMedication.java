package meditracker;

/**
 * Stores name and the status of daily medication (taken or not)
 */
public class DailyMedication {
    private final String name;
    private boolean isTaken;

    /**
     * Constructs DailyMedication with medication name and status of daily medication (taken or not)
     *
     * @param name Name of the medication to be taken
     */
    public DailyMedication(String name) {
        this.name = name;
        this.isTaken = false;
    }

    public String getName() {
        return name;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void take() {
        isTaken = true;
    }

    public void untake() {
        isTaken = false;
    }
}
