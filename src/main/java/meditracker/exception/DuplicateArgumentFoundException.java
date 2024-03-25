package meditracker.exception;

public class DuplicateArgumentFoundException extends Throwable {
    public DuplicateArgumentFoundException(String errorContext) {
        super(errorContext);
    }
}
