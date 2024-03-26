package supertracker.ui;

public class ErrorMessage {

    public static final String INVALID_UPDATE_FORMAT = "Invalid update command format!";
    public static final String EMPTY_PARAM_INPUT = "Parameters cannot be left empty!";
    public static final String INVALID_DELETE_FORMAT = "Invalid delete command format!";
    public static final String INVALID_LIST_FORMAT = "Invalid list command format!";
    public static final String INVALID_NEW_ITEM_FORMAT = "Invalid new command format!";
    public static final String INVALID_ADD_FORMAT = "Invalid add command format!";
    public static final String INVALID_REMOVE_FORMAT = "Invalid remove command format!";
    public static final String INVALID_REPORT_FORMAT = "Invalid report command format!";
    public static final String ITEM_NOT_IN_LIST_UPDATE =
            " does not exist in inventory. Unable to update its values. =(";
    public static final String ITEM_NOT_IN_LIST_DELETE =
            " does not exist in inventory. Unable to delete something that does not exist. =(";
    public static final String ITEM_NOT_IN_LIST_ADD =
            " does not exist in inventory. Unable to increase its quantity. =(";
    public static final String ITEM_NOT_IN_LIST_REMOVE =
            " does not exist in inventory. Unable to decrease its quantity. =(";
    public static final String ITEM_IN_LIST_NEW = " already exists in inventory. Use the update command instead.";
    public static final String INVALID_NUMBER_FORMAT = "Invalid values for price/quantity";
    public static final String INVALID_DATE_FORMAT = "Invalid date. Follow \"dd/mm/yyyy\" format";
    public static final String QUANTITY_TOO_SMALL = "Quantity should be more than equal to 0";
    public static final String PRICE_TOO_SMALL = "Price should be more than 0";
    public static final String FILE_HANDLER_ERROR = "Error setting up file handler";
    public static final String INVALID_FIND_FORMAT =
            "Please ensure the name of the item you are looking for is correct";
    public static final String FILE_SAVE_ERROR = "Oops! Unable to save data due to an I/O error!";
    public static final String FILE_LOAD_ERROR = "Oops! Unable to load your previous data due to an I/O error!";
    public static final String FILE_CORRUPTED_ERROR =
            "Oops! Unable to load some of your previous data as the data in the save file has been corrupted!";
}
