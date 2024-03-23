package seedu.stockpal.commands;

import seedu.stockpal.data.product.Pid;


//@@author EdmundTangg
public class HistoryCommand extends Command {
    public static final String COMMAND_KEYWORD = "history";
    public static final String COMMAND_DESCRIPTION =
        "Checks the history of inflow outflow commands" +
        " of a particular PID";
    public static final String COMMAND_USAGE =
        "history PID";

    public static final String[] COMMAND_FLAGS = {
        "PID"
    };

    public static final String[] COMMAND_FLAG_DESCRIPTIONS = {
        "Product ID of product"
    };

    private final Pid pid;

    public HistoryCommand(Integer pidValue) {
        this.pid = new Pid(pidValue);
    }

/*  @Override
    public void execute(ProductList productList) throws StockPalException {


    }*/


}
