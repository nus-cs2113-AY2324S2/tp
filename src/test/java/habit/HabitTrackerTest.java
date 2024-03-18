package habit;

import exceptions.HabitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HabitTrackerTest {
    private HabitTracker habitTracker;
    @BeforeEach
    public void setUp() {
        habitTracker = new HabitTracker();
    }

    @Test
    public void addHabit_addTwoHabits_success() {
        Habit habitOne = new Habit("Complete my homework");
        Habit habitTwo = new Habit("Brush my teeth");
        habitTracker.addHabit(habitOne);
        habitTracker.addHabit(habitTwo);
        assertEquals(2, HabitTracker.getNumberOfHabits());
    }

    @Test
    public void updateHabitCount_habitCountTwo_success() throws HabitException {
        Habit habitThree = new Habit("Complete my homework");
        habitTracker.addHabit(habitThree);
        habitTracker.updateHabitCount("3", "2");
        assertEquals(2, habitThree.getHabitCount());

    }
}
