package seedu.planus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GradeTest {
    @Test
    public void grade_success() {
        assertEquals("C", new Grade("C").getLetterGrade());
    }

    @Test
    public void setNumberGrade_success() {
        assertEquals(4.50, new Grade("A-").getNumberGrade());
    }
}
