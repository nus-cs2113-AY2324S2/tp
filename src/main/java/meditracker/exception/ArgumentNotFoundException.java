package meditracker.exception;

/**
 * Exception to be thrown when required argument not found by ArgumentParser
 */
public class ArgumentNotFoundException extends Exception {
    public ArgumentNotFoundException(String errorContext) {
        super(errorContext);
    }
}
