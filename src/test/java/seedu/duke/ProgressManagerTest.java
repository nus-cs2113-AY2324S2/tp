package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;
import java.util.logging.Level;

public class ProgressManagerTest {
    ResultsList sessionResults = new ResultsList();

    static private Logger logger = Logger.getLogger("ProgressManagerTestLogger");

    void createResultList() {

        logger.log(Level.INFO, "Creating dummy session results.");

        final int NUM_OF_RESULTS = 3;
        Results[] results = new Results[NUM_OF_RESULTS];

        for(int i = 0; i < NUM_OF_RESULTS; i++) {
            results[i].increaseNumberOfQuestions();
            sessionResults.addResults(results[i]);
        }
    }


    @Test
    public void clearProgress_threeResults_emptyResultsList() {

        createResultList();

        logger.log(Level.INFO, "Testing progress manager.");
        ProgressManager pm = new ProgressManager(sessionResults);
        pm.clearProgress();
        assertEquals(0, sessionResults.getNumOfResults());
        int numOfResults = sessionResults.getNumOfResults();
        assert numOfResults == 0 : "Number of results should be 0.";
    }
}
