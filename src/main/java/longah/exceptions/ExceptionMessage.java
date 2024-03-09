package longah.exceptions;

public enum ExceptionMessage {
    UNABLE_TO_FIND_PERSON ("That person cannot be found.");

    private final String message;

    /**
     * Constructor for ExceptionMessage.
     * 
     * @param message The message to be printed when the exception is called.
     */
    ExceptionMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the message of the exception.
     * 
     * @return The message of the exception.
     */
    public String getMessage() {
        return message;
    }
}
