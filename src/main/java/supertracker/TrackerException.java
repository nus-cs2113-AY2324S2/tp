package supertracker;

import java.util.logging.Level;

public class TrackerException extends Exception {
    protected String errorMessage;

    public TrackerException(String errorMessage) {
        assert !errorMessage.isEmpty();
        this.errorMessage = errorMessage;
        SuperTracker.logger.log(Level.INFO, "Error while passing input: " + errorMessage, this);
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
