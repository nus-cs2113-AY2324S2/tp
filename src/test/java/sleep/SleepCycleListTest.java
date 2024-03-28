package sleep;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepCycleListTest {
    private SleepCycleList sleepCycleList;

    @BeforeEach
    public void setUp() {
        sleepCycleList = new SleepCycleList();
    }

    @Test
    public void addSleepCycle_addCycle_success() {
        LocalDate date;
        date = LocalDate.parse("25/01/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy") );
        SleepCycle cycle = new SleepCycle(2, date);
        sleepCycleList.addSleepCycle(cycle, false);
        assertEquals(1, sleepCycleList.getNumberOfCycles());
    }

    @Test
    public void addTwoSleepCycle_getTotalHoursSlept_success() {
        LocalDate date1;
        LocalDate date2;
        date1 = LocalDate.parse("25/01/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        date2 = LocalDate.parse("26/01/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        SleepCycle cycle = new SleepCycle(2, date1);
        sleepCycleList.addSleepCycle(cycle, false);
        cycle = new SleepCycle(3, date2);
        sleepCycleList.addSleepCycle(cycle,false);
        assertEquals(5, sleepCycleList.getTotalHrsSlept());
    }
}
