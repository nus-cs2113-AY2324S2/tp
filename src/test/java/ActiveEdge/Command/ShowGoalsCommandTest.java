package ActiveEdge.Command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShowGoalsCommandTest {

    @Test
    void testExecute_ShowGoals() {
        // Assuming ShowGoalsCommand has a method `execute` that shows both calorie and water goals
        ShowGoalsCommand showGoalsCommand = new ShowGoalsCommand();
        String resultMessage = showGoalsCommand.execute();
        // Assuming the expected message format is "Calorie goal: [calorie goal], Water goal: [water goal] ml"
        assertEquals("Calorie goal: 2000, Water goal: 2500 ml", resultMessage);
    }
}
