package meditracker.exception;

/**
 * Exception to be thrown when duplicate argument found by ArgumentParser
 */
public class DuplicateArgumentFoundException extends Exception {
    public DuplicateArgumentFoundException(String errorContext) {
        super(errorContext);
    }
}
