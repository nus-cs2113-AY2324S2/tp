package classify.classgroup;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ClassTest {
    public void classConstructorTest() {
        Class newClass = new Class(17,"J1 Physics");
        assertEquals(17, newClass.getAgeOfStudents());
        assertEquals("J1 Physics",newClass.getClassName());
    }
}
