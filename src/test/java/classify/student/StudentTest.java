package classify.student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.fail;

public class StudentTest {
    
    @Test
    public void student_name_constructor_test() {
        assertEquals("James", new Student("James").getName());
    }

    @Test 
    public void student_details_constructor_test() {

        Student jack = new Student("jack");
        Student jack2 = new Student(jack.name, jack.details);
        assertEquals(jack, jack2);
    }
}
