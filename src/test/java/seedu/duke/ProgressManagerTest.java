package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProgressManagerTest {
    ResultsList sessionResults = new ResultsList();

    void createResultList() {

        final int NUM_OF_RESULTS = 3;
        Results[] results = new Results[NUM_OF_RESULTS];

        for(int i = 0; i < NUM_OF_RESULTS; i++) {
            results[i].increaseNumberOfQuestions();
            sessionResults.addResult(results[i]);
        }
    }


    @Test
    public void testClearProgress() {

        createResultList();
        ProgressManager pm = new ProgressManager(sessionResults);
        pm.clearProgress();
        assertEquals(0, sessionResults.getNumOfResults());
    }
}
