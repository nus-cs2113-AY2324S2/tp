package supertracker.ui;

public class ErrorMessage {

    public static final String INVALID_UPDATE_FORMAT = "Invalid update command format!";
    public static final String EMPTY_PARAM_INPUT = "Parameters cannot be left empty!";
    public static final String INVALID_DELETE_FORMAT = "Invalid delete command format!";
    public static final String INVALID_LIST_FORMAT = "Invalid list command format!";
    public static final String INVALID_NEW_ITEM_FORMAT = "Invalid new command format!";
    public static final String INVALID_ADD_FORMAT = "Invalid add command format!";
    public static final String INVALID_REMOVE_FORMAT = "Invalid remove command format!";
    public static final String ITEM_NOT_IN_LIST_UPDATE =
            " does not exist in inventory. Unable to update its values. =(";
    public static final String ITEM_NOT_IN_LIST_DELETE =
            " does not exist in inventory. Unable to delete something that does not exist. =(";
    public static final String ITEM_NOT_IN_LIST_ADD=
            " does not exist in inventory. Unable to increase its quantity. =(";
    public static final String ITEM_NOT_IN_LIST_REMOVE=
            " does not exist in inventory. Unable to decrease its quantity. =(";
    public static final String ITEM_NOT_IN_LIST_FIND=
            " Search came up empty, your item is not in our inventory. =(";
    public static final String ITEM_IN_LIST_NEW = " already exists in inventory. Use the update command instead.";
    public static final String INVALID_NUMBER_FORMAT = "Invalid values for price/quantity";
    public static final String QUANTITY_TOO_SMALL = "Quantity should be more than 0";
    public static final String PRICE_TOO_SMALL = "Price should be more than 0";
    public static final String FILE_HANDLER_ERROR = "Error setting up file handler";
    public static final String INVALID_FIND_FORMAT =
            "Please ensure the name of the item you are looking for is correct";
}
