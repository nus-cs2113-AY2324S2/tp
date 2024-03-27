package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelperTest {

    private static final Helper helperTest = new Helper();

    @Test
    // check number of prompts in the helper's command list
    public void getCommandsCount_correctNumOfCommandsInList_expect7(){
        assertEquals(7, helperTest.getCommandsCount());
    }

    @Test
    void generateRandomNumber_noTopicToChooseFrom_expect0() {
        int upperLimit = 1;
        assertEquals(0, helperTest.generateRandomNumber(upperLimit));
    }
    @Test
    void generateRandomNumber_onlyOneTopicToChooseFrom_expect1() {
        int upperLimit = 2;
        assertEquals(1, helperTest.generateRandomNumber(upperLimit));
    }
}
