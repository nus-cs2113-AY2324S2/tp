package meditracker;

/**
 * Stores name and the status of daily medication (taken or not)
 */
public class DailyMedication {
    private String name;
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

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        String takenIcon = isTaken ? "[X]" : "[ ]"; // X identify medication as taken
        return String.format("%s %s", takenIcon, name);
    }
}
