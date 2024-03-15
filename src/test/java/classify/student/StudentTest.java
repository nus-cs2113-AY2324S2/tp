package classify.student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.fail;

public class StudentTest {
    
    @Test
    public void studentNameConstructorTest() {
        assertEquals("James", new Student("James").getName());
    }

    // @Test 
    // public void studentDetailsConstructorTest() {

    //     Student jack = new Student("jack");
    //     Student jack2 = new Student(jack.name, jack.details);
    //     assertEquals(jack, jack2);
    // }
}
