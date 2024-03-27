package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelperTest {

    private final Helper helperTest = new Helper();

    @Test
    // check number of prompts in the helper's command list
    public void helperCompletenessTest(){
        assertEquals(helperTest.getCommandsCount(), 7);
    }
}
