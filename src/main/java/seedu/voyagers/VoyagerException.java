package seedu.voyagers;
/**
 * Generic exception class for Voyagers.
 */
public class VoyagerException extends Exception {

    public VoyagerException(String message) {
        super(message);
    }

    @Override
    public String toString(){
        return "OOPS!!! " + super.getMessage();
    }
    public void printExceptionMessage() {
        System.out.println("An error occurred");
    }
}
