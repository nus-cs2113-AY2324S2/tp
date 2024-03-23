package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

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
}
