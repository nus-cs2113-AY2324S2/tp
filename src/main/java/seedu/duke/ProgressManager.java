package seedu.duke;

import java.util.logging.Logger;
import java.util.logging.Level;

public class ProgressManager {

    private ResultsList sessionResults;

    private Logger logger = Logger.getLogger("ProgressManagerLogger");

    public ProgressManager(ResultsList sessionResults) {
        this.sessionResults = sessionResults;
    }

    public ResultsList clearProgress() {
        logger.log(Level.INFO, "Clearing session progress.");
        sessionResults.clearResults();
        return sessionResults;
    }
}
