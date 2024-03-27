package seedu.planus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GradeTest {
    @Test
    public void Grade() {
        assertEquals("C", new Grade("C").getLetterGrade());
    }

    @Test
    public void setNumberGrade() {
        assertEquals(4.50, new Grade("A-").getNumberGrade());
    }
}
