package seedu.planus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlanGetterTest {
    @Test
    public void getPlan_wholePlan() throws Exception {
        Timetable timetable = new Timetable();
        assertEquals("Total MCs: 0" + System.lineSeparator(), PlanGetter.getPlan(timetable));

        timetable.addCourse(new Course("CG2111A", "Engineering Principles and Practice II", 1, 2));
        timetable.addCourse(new Course("MA1511", "Engineering Calculus", 2, 1, 1));
        timetable.addCourse(new Course("CG1111A", "Engineering Principles and Practice I", 1, 1));
        timetable.addCourse(new Course("CS2040C", "Data Structures and Algorithms", 2, 1));

        assertEquals("Year 1 Semester 1:" + System.lineSeparator() + "  MA1511 Engineering Calculus (MC: 2)"
                + System.lineSeparator() + "  CG1111A Engineering Principles and Practice I (MC: 4)"
                + System.lineSeparator() + "Term MCs: 6" + System.lineSeparator() + "-----------------------------"
                + System.lineSeparator() + "Year 1 Semester 2:" + System.lineSeparator()
                + "  CG2111A Engineering Principles and Practice II (MC: 4)" + System.lineSeparator()
                + "Term MCs: 4" + System.lineSeparator() + "-----------------------------" + System.lineSeparator()
                + "Year 2 Semester 1:" + System.lineSeparator() + "  CS2040C Data Structures and Algorithms (MC: 4)"
                + System.lineSeparator() + "Term MCs: 4" + System.lineSeparator() + "-----------------------------"
                + System.lineSeparator() + "Total MCs: 14" + System.lineSeparator(),
                PlanGetter.getPlan(timetable));
    }

    @Test
    public void getPlan_withYear() throws Exception {
        Timetable timetable = new Timetable();
        assertEquals("Year MCs: 0" + System.lineSeparator(), PlanGetter.getPlan(timetable, 1));

        timetable.addCourse(new Course("CG2111A", "Engineering Principles and Practice II", 1, 2));
        timetable.addCourse(new Course("MA1511", "Engineering Calculus", 2, 1, 1));
        timetable.addCourse(new Course("CG1111A", "Engineering Principles and Practice I", 1, 1));
        timetable.addCourse(new Course("CS2040C", "Data Structures and Algorithms", 2, 1));

        assertEquals("Year 1 Semester 1:" + System.lineSeparator() + "  MA1511 Engineering Calculus (MC: 2)"
                + System.lineSeparator() + "  CG1111A Engineering Principles and Practice I (MC: 4)"
                + System.lineSeparator() + "Term MCs: 6" + System.lineSeparator() + "-----------------------------"
                + System.lineSeparator() + "Year 1 Semester 2:" + System.lineSeparator()
                + "  CG2111A Engineering Principles and Practice II (MC: 4)" + System.lineSeparator()
                + "Term MCs: 4" + System.lineSeparator() + "-----------------------------" + System.lineSeparator()
                + "Year MCs: 10" + System.lineSeparator(), PlanGetter.getPlan(timetable, 1));

        assertEquals("Year MCs: 0" + System.lineSeparator(), PlanGetter.getPlan(timetable, 3));
    }

    @Test
    public void getPlan_withYearAndTerm() throws Exception {
        Timetable timetable = new Timetable();
        assertEquals("Term MCs: 0" + System.lineSeparator(), PlanGetter.getPlan(timetable, 1, 1));

        timetable.addCourse(new Course("CG2111A", "Engineering Principles and Practice II", 1, 2));
        timetable.addCourse(new Course("MA1511", "Engineering Calculus", 2, 1, 1));
        timetable.addCourse(new Course("CG1111A", "Engineering Principles and Practice I", 1, 1));
        timetable.addCourse(new Course("CS2040C", "Data Structures and Algorithms", 2, 1));

        assertEquals("Year 1 Semester 1:" + System.lineSeparator() + "  MA1511 Engineering Calculus (MC: 2)"
                + System.lineSeparator() + "  CG1111A Engineering Principles and Practice I (MC: 4)"
                + System.lineSeparator() + "Term MCs: 6" + System.lineSeparator(),
                PlanGetter.getPlan(timetable, 1, 1));

        assertEquals("Year 2 Semester 1:" + System.lineSeparator() + "  CS2040C Data Structures and Algorithms (MC: 4)"
                + System.lineSeparator() + "Term MCs: 4" + System.lineSeparator(),
                PlanGetter.getPlan(timetable, 2, 1));

        assertEquals("Term MCs: 0" + System.lineSeparator(), PlanGetter.getPlan(timetable, 1, 3));
    }
}
