package exception;
public class StockMasterException extends Exception {
    protected String description;
    protected String type;

    public StockMasterException(String description, String type) {
        this.description = description;
        this.type = type;
    }

    public StockMasterException() {
        this.description = null;
        this.type = null;
    }

    public void printException() {
        switch (this.type) {
        case "EMPTY":
            if (description.equals("description")) {
                System.out.println("The item name cannot be left blank.");
            }
            break;
        case "OUT_OF_BOUNDS":
            System.out.println("The index you're accessing is out of bounds.");
            break;
        default:
            System.out.println("Unrecognised error.");
            break;
        }
    }
}
