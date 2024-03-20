package exceptions;

/**
 * Represents the exception thrown when the command is invalid.
 */
public class InvalidCommandException extends GitException {
    /**
     * Constructs InvalidCommandException.
     */
    public InvalidCommandException() {
        message = "Unknown command. Type 'help' for a list of commands.";
    }

}
