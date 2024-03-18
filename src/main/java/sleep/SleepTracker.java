package sleep;

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
