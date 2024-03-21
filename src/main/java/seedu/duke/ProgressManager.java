package seedu.duke;


public class ProgressManager {

    private ResultsList sessionResults;

    public ProgressManager(ResultsList sessionResults) {
        this.sessionResults = sessionResults;
    }

    public void clearProgress() {
        sessionResults.clearResults();
    }
}
