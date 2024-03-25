package meditracker.exception;

public class DuplicateArgumentFoundException extends Exception {
    public DuplicateArgumentFoundException(String errorContext) {
        super(errorContext);
    }
}
