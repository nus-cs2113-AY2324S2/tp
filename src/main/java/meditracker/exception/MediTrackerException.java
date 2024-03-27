package meditracker.exception;

/**
 * The MediTrackerException class represents an exception specific to the MediTracker application.
 * It extends the Exception class.
 */
public class MediTrackerException extends Exception {

    /**
     * Constructs a new MediTrackerException with the specified error context.
     *
     * @param errorContext The error context describing the exception.
     */
    public MediTrackerException(String errorContext) {
        super(errorContext);
    }
}
