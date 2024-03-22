package exceptions;

import parser.CommandType;
import common.Messages;

public class CommandFormatException extends Exception{
    public CommandFormatException(CommandType command){

        switch (command) {

        case ADD:
            System.out.println(Messages.INVALID_ADD_FORMAT);
            break;
        case DELETE:
            System.out.println(Messages.INVALID_DELETE_FORMAT);
            break;
        case EDIT:
            System.out.println(Messages.INVALID_EDIT_FORMAT);
            break;
        case HELP:
            System.out.println(Messages.HELP);
            break;
        default:
            System.out.println(Messages.INVALID_COMMAND);
            break;
        }

    }
}
