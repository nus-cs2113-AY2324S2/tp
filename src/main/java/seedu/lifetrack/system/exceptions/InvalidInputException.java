package seedu.lifetrack.system.exceptions;
import seedu.lifetrack.calories.calorielist.CalorieList;

import java.util.logging.Level;
import java.util.logging.Logger;
public class InvalidInputException extends Exception {

    public final String heythere = "";
    private static Logger logr = Logger.getLogger(CalorieList.class.getName());
    public InvalidInputException(){
        super("\t Please ensure that you have keyed in the correct format!");
        logr.setLevel(Level.SEVERE);
        logr.log(Level.WARNING,"\t Please ensure that you have keyed in the correct format!");
    }

    public InvalidInputException(String exception) {
        super(exception);
    }
}
