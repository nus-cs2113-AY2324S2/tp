package exceptions.commands;

import exceptions.GitException;

/**
 * Represents the exception thrown when the format is correct, but parameter input is empty.
 */
public class IncompleteParameterException extends GitException {
    /**
     * Constructs IncompleteParameterException.
     */
    public IncompleteParameterException(String parameter) {
        message = parameter + " cannot be empty!";
    }
}
