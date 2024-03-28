package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

class TimetableTest {
    @Test
    public void testAddUserTask() {
        Timetable timetable = new Timetable();
        Task task = new Task("Study","2024-03-18", "09:00", "11:00","f");

        timetable.addUserTask("Tuesday", task);

        timetable.printTasksOfTheDay("Tuesday");
        assertEquals(1, timetable.getWeeklyTasks().get("Tuesday").size());
        assertEquals(task, timetable.getWeeklyTasks().get("Tuesday").get(0));
    }
    @Test
    public void testCompareTimetables() {
        Timetable timetable1 = new Timetable();
        Timetable timetable2 = new Timetable();
        Task task1 = new Task("Study","2024-03-18", "09:00", "11:00","f");
        Task task2 = new Task("Study","2024-03-18", "07:00", "10:00","f");
        timetable1.addUserTask("Monday", task1);
        timetable2.addUserTask("Monday", task2);

        Timetable.compareTimetable(timetable1, timetable2);
    }
    @Test
    public void testChangeFlexibleTaskTiming_CorrectInput() {
        Timetable timetable = new Timetable();
        Task flexibleTask = new Task("lec", "monday", "09:00", "11:00", "f");
        timetable.addUserTask("monday", flexibleTask);
        try {
            timetable.changeFlexibleTaskTiming("monday", 0, LocalTime.of(11, 0),
                    LocalTime.of(12, 0));
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    @Test
    public void testChangeFlexibleTaskTiming_InvalidIndex(){
        Timetable timetable = new Timetable();
        try{
            timetable.changeFlexibleTaskTiming("monday", 0, LocalTime.of(11, 0),
                    LocalTime.of(12, 0));
            fail("Expected IndexOutOfBoundsException");
        } catch(IndexOutOfBoundsException e){
            assertEquals("Invalid index", e.getMessage());
        }
    }
    @Test
    public void testChangeFlexibleTaskTiming_NonFlexibleTask() {
        Timetable timetable = new Timetable();
        Task nonFlexibleTask = new Task("lec", "monday", "09:00", "11:00", "c");
        timetable.addUserTask("monday", nonFlexibleTask);
        try {
            timetable.changeFlexibleTaskTiming("monday", 0, LocalTime.of(11, 0), LocalTime.of(12, 0));
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Task on monday at index 1 is not flexible, " +
                    "timings cannot be changed.", e.getMessage());
        }
    }
    @Test
    public void testChangeTaskType_ValidInput() {
        Timetable timetable = new Timetable();
        Task task = new Task("lec", "monday", "09:00", "11:00", "f");
        timetable.addUserTask("monday", task);
        try {
            timetable.changeTaskType("monday", 0, "c");
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    @Test
    public void testChangeTaskType_InvalidIndex() {
        Timetable timetable = new Timetable();
        try {
            timetable.changeTaskType("monday", 0, "c");
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Invalid index", e.getMessage());
        }
    }
}
