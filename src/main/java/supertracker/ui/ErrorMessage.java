package supertracker.ui;

public class ErrorMessage {

    public static final String INVALID_UPDATE_FORMAT = "Invalid update command format!";
    public static final String EMPTY_PARAM_INPUT = "Parameters cannot be left empty!";
    public static final String INVALID_DELETE_FORMAT = "Invalid delete command format!";
    public static final String INVALID_LIST_FORMAT = "Invalid list command format!";
    public static final String INVALID_NEW_ITEM_FORMAT = "Invalid new command format!";
    public static final String ITEM_NOT_IN_LIST_UPDATE = " does not exist in inventory. Unable to update its values. =(";

    public static final String ITEM_NOT_IN_LIST_DELETE = " does not exist in inventory. Unable to delete something that does not exist. =(";
    public static final String ITEM_IN_LIST_NEW = " already exists in inventory. Use the update command instead.";


    public static final String INVALID_NUMBER_FORMAT = "Invalid values for price/quantity";

    public static final String QUANTITY_TOO_SMALL = "Quantity should be more than 0";

    public static final String PRICE_TOO_SMALL = "Price should be more than 0";
}
