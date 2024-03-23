package fitness;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FitnessMotivatorTest {

    private FitnessMotivator fitnessMotivator;

    @BeforeEach
    public void setUp() {
        fitnessMotivator = new FitnessMotivator();
    }

    @Test
    public void printExercises_getExercises_success() {
        String output = fitnessMotivator.getExercises();
        assertTrue(output.contains("Arms"));
        assertTrue(output.contains("Chest"));
        assertTrue(output.contains("Abs"));
        assertTrue(output.contains("Back"));
        assertTrue(output.contains("Legs"));
        assertTrue(output.contains("sets"));
        assertTrue(output.contains("reps"));
    }
}
