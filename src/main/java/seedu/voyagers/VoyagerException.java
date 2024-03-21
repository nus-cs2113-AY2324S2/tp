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

    public static class ParserDefinitions {
        public static final String[] ADDMAINTRIP = {"add", "main", "trip"};
        public static final String[] ADDSUBTRIP = {"add", "sub", "trip"};
        public static final String[] ADDEXPENSE = {"add", "expense"};
        public static final String[] DELETEMAINTRIP = {"delete", "main", "trip"};

    }
}
