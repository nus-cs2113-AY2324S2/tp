package seedu.planus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CourseTest {
    @Test
    public void getYearAndTerm_termOneOrTwo_semester() {
        assertEquals("Year 1 Semester 1", new Course("CS1010", "", 4, 1, 1).getYearAndTerm());
        assertEquals("Year 1 Semester 2", new Course("CG2111A", "", 4, 1, 2).getYearAndTerm());
    }

    @Test
    public void getYearAndTerm_termThreeOrFour_specialTerm() {
        assertEquals("Year 2 Special Term 1", new Course("CS2040C", "", 4, 2, 3).getYearAndTerm());
        assertEquals("Year 3 Special Term 2", new Course("CS2040C", "", 4, 3, 4).getYearAndTerm());
    }

    @Test
    public void getDetails() {
        assertEquals("MA1511 Engineering Calculus (MC: 2)",
                new Course("MA1511", "Engineering Calculus", 2, 1, 1).getDetails());
    }

    @Test
    public void getModularCredit_notProvided_default4ModularCredit() {
        assertEquals(4, new Course("CS1010", "", 1, 1).getModularCredit());
    }
}
