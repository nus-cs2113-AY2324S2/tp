package sleep;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepCycleListTest {
    private SleepCycleList sleepCycleList;

    @BeforeEach
    public void setUp() { sleepCycleList = new SleepCycleList();
    }

    @Test
    public void addSleepCycle_addCycle_success() {
        SleepCycle cycle = new SleepCycle(2, "25/01/22");
        sleepCycleList.addSleepCycle(cycle);
        assertEquals(1, sleepCycleList.getNumberOfCycles());
    }

    @Test
    public void addTwoSleepCycle_getTotalHoursSlept_success() {
        SleepCycle cycle = new SleepCycle(2, "25/01/22");
        sleepCycleList.addSleepCycle(cycle);
        cycle = new SleepCycle(3, "26/01/22");
        sleepCycleList.addSleepCycle(cycle);
        assertEquals(5, sleepCycleList.getTotalHrsSlept());
    }
}
