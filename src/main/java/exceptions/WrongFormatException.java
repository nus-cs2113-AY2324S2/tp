package exceptions;

/**
 * Represents the exception thrown when the command does not follow the proper format.
 */
public class WrongFormatException extends GitException {
    /**
     * Constructs WrongFormatException.
     */
    public WrongFormatException(String command) {
        message = printWrongFormatFix(command);
    }

    /**
     * Creates a message that reminds the user of the proper command format.
     */
    public String printWrongFormatFix(String command) {
        StringBuilder message = new StringBuilder();
        message.append("Command is in the wrong format, type \"help\" for more information.");
        message.append(System.lineSeparator());
        if (command.equals("date")) {
            message.append("exp needs 'd/'");
        } else if (command.equals("amt")) {
            message.append("amt needs 'a/'");
        }

        return message.toString();
    }
}
