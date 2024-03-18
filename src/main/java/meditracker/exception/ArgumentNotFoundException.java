package meditracker.exception;

public class ArgumentNotFoundException extends Exception {
    public ArgumentNotFoundException(String errorContext) {
        super(errorContext);
    }
}
