package supertracker;

public class TrackerException extends Exception {
    protected String errorMessage;

    public TrackerException(String errorMessage) {
        assert !errorMessage.isEmpty();
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
