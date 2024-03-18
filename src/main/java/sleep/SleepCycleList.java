package sleep;

import ui.Ui;

import java.util.ArrayList;

public class SleepCycleList {

    private ArrayList<SleepCycle> sleepCycleList;
    private double totalHrsSlept;
    private int numberOfCycles;

    public SleepCycleList() {
        this.sleepCycleList = new ArrayList<>();
        this.totalHrsSlept = 0;
        this.numberOfCycles = 0;
    }

    public void addSleepCycle(SleepCycle sleepCycle) {
        sleepCycleList.add(sleepCycle);
        totalHrsSlept += sleepCycle.getHoursSlept();
        numberOfCycles += 1;
        Ui.printMessageWithSepNewLine("--- SleepCycle for " + sleepCycle.getDateOfSleep() + " has been added ---");
    }

    public void listSleepCycles() {
        String sleepListMessage = "Total hrs slept: " + totalHrsSlept + System.lineSeparator();
        for (int i = 0; i < numberOfCycles - 1; i++) {
            sleepListMessage += (i + 1) + ". " + sleepCycleList.get(i) + System.lineSeparator();
        }
        sleepListMessage += numberOfCycles + ". " + sleepCycleList.get(numberOfCycles - 1);
        Ui.printMessageWithSepNewLine(sleepListMessage);
    }

    public int getNumberOfCycles() {
        return numberOfCycles;
    }

    public double getTotalHrsSlept() {
        return totalHrsSlept;
    }
}
