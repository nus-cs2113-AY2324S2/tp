package exceptions.commands;

import exceptions.GitException;

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
        switch(command) {
        case ("exp"):
            message.append("exp needs 'd/'");
            break;
        case ("amt"):
            message.append("amt needs 'a/'");
            break;
        case ("use"):
            message.append("use needs 'a/'");
            break;
        default:
            // Do nothing
        }

        return message.toString();
    }
}
