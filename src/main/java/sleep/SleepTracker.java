package sleep;

/**
 * Represents the interface for SleepTracker
 * The sleepCycleList contains all the tasks managed by Wellness360.
 */
public class SleepTracker {
    SleepCycleList sleepCycleList;

    public SleepTracker() {
        this.sleepCycleList = new SleepCycleList();
    }

    public void listSleepCycles() {
        sleepCycleList.listSleepCycles();
    }

    public void addSleepCycle(SleepCycle sleepCycleToAdd) {
        sleepCycleList.addSleepCycle(sleepCycleToAdd);
    }
}
