package exceptions;

import parser.CommandType;
import common.Messages;

public class CommandFormatException extends Exception{
    public CommandFormatException(CommandType command){

        switch (command) {

        case ADD:
            System.out.println(Messages.INVALID_ADD_FORMAT);
            break;
        case DEL:
            System.out.println(Messages.INVALID_DELETE_FORMAT);
            break;
        case EDIT:
            System.out.println(Messages.INVALID_EDIT_FORMAT);
            break;
        case SELL:
            System.out.println(Messages.INVALID_SELL_FORMAT);
            break;
        case HELP:
            System.out.println(Messages.HELP);
            break;
        default:
            System.out.println(Messages.INVALID_COMMAND);
            break;
        }

    }

    public CommandFormatException(String error) {
        switch (error) {

        case "SELL_PRICE":
            System.out.println(Messages.INVALID_SELL_PRICE);
            break;
        default:
            System.out.println("Error Detected");
        }



    }
}
