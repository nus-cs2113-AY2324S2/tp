package seedu.duke;


import org.junit.jupiter.api.Test;
import gpa.GPAMain;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GPATest {
    @Test
    void testCalculateNewGPA_allAs() {
        GPAMain gpaMain = new GPAMain();
        double currentGPA = 4.0;
        int totalAccumulatedCredits = 120;
        int numOfModules = 4;
        int[] moduleCredits = {4, 4, 4, 2};
        String[] moduleGrades = {"A", "A", "A","A"};

        double expectedGPA = 4.10;
        double resultGPA = gpaMain.calculateNewGPA(currentGPA, totalAccumulatedCredits, numOfModules,
                moduleCredits, moduleGrades);

        assertEquals(expectedGPA, resultGPA, 0.01, "The GPA calculated for all A's did not match the expected value");
    }

}


